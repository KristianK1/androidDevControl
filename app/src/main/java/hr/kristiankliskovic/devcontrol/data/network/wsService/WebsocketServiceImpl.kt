package hr.kristiankliskovic.devcontrol.data.network.wsService

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import hr.kristiankliskovic.devcontrol.data.network.HTTPSERVER
import hr.kristiankliskovic.devcontrol.data.network.model.WssConnectUserMessage
import hr.kristiankliskovic.devcontrol.data.network.model.WssConnectUserMessageData
import hr.kristiankliskovic.devcontrol.data.network.model.WssLogoutReason
import hr.kristiankliskovic.devcontrol.data.network.model.WssLogoutReasonResponse
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

class WebsocketServiceImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val gson: Gson,
) : WebSocketService {
    private val httpClientForWS: HttpClient =
        HttpClient(CIO) {
            install(WebSockets) {
                pingInterval = 20_000
            }
        }
    var authToken: String? = null
    private var currentWSSclient: DefaultClientWebSocketSession? = null

    private val connectedToWSSInternal: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val connectedToWSS: StateFlow<Boolean> = connectedToWSSInternal.asStateFlow()

    private val userMessagesInternal: MutableStateFlow<WssLogoutReason?> =
        MutableStateFlow(null)
    override val userMessages: StateFlow<WssLogoutReason?> = userMessagesInternal.asStateFlow()

    private val deviceMessagesInternal: MutableStateFlow<String> = MutableStateFlow("")
    override val deviceMessages: StateFlow<String> = deviceMessagesInternal.asStateFlow()

    override suspend fun connect(token: String) {
        authToken = token
        if (!connectedToWSSInternal.value) {
            while (true) {
                Log.i("websocket", "qw1")
                if (authToken == null) break;
                Log.i("websocket", "qw2")
                try {
                    httpClientForWS.webSocket(
                        method = HttpMethod.Get,
                        host = HTTPSERVER.wsServer,
                        port = 8000,
//                    path = "/"
                    ) {
                        currentWSSclient = this
                        Log.i("websocket", "qw3")
                        connectedToWSSInternal.value = true
                        Log.i("websocket", "value_emit_${connectedToWSSInternal.value}")
                        send(constructFirstMessage(authToken!!)) //!! is kinda okey here
                        while (true) {
                            Log.i("websocket_message", "while");
                            val othersMessage = incoming.receive() as? Frame.Text
                            Log.i("websocket_message", "while1");
                            val message = othersMessage?.readText()
                            Log.i("websocket_message", "while2");
                            if (message != null) {
                                Log.i("websocket_message", message)
                                deserializeData(message)
                                Log.i("websocket_message", "after")
                            }
                            Log.i("websocket_message", "while3");
                        }
                    }
                } catch (e: Throwable) {
                    connectedToWSSInternal.emit(false)
                    Log.i("websocket",e.message!!)
                    Log.i("websocket", "value_emit_${connectedToWSSInternal.value}")
                    Log.i("websocket", "error")
                }
            }
            Log.i("websocket", "escaped while true")
        }
    }

    override suspend fun disconnect() {
        authToken = null
        currentWSSclient?.close()
        currentWSSclient = null
        connectedToWSSInternal.emit(false)
        Log.i("websocket", "disconnect function")
    }

    private suspend fun deserializeData(data: String) {
        try {
            Log.i("websocket_parser", "parsed1")
            val parsed = gson.fromJson(data, WssLogoutReasonResponse::class.java)
            Log.i("websocket_parser", "parsed2")
            when (parsed.logoutReason) {
                0 -> userMessagesInternal.value = WssLogoutReason.DeletedUser
                1 -> userMessagesInternal.value = WssLogoutReason.ChangedPassword
                2 -> userMessagesInternal.value = WssLogoutReason.LogoutAll
                3 -> userMessagesInternal.value = WssLogoutReason.LogoutMyself
            }
            authToken = null
            Log.i("websocket_parser", "parsed3_${parsed}")
            Log.i("websocket_parser", "parsed3_${userMessagesInternal.value}")
        } catch (e: JsonSyntaxException) {
            Log.i("websocket_parser", "not a user logout message")
        } catch (e: Throwable) {
            Log.i("websocket_parser", "error")
        }

        //another try for device messages or other
    }

    private fun constructFirstMessage(authToken: String): String {
        return gson.toJson(
            WssConnectUserMessage(
                data = WssConnectUserMessageData(
                    authToken = authToken
                )
            )
        )
    }

    override suspend fun resetUserMessages() {
        userMessagesInternal.emit(null)
    }
}
