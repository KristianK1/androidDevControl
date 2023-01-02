package hr.kristiankliskovic.devcontrol.data.network.wsService

import android.util.Log
import com.google.gson.Gson
import hr.kristiankliskovic.devcontrol.data.network.HTTPSERVER
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

    private val connectedToWSSInternal: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val connectedToWSS: StateFlow<Boolean> = connectedToWSSInternal.asStateFlow()

    private val userMessagesInternal: MutableStateFlow<WssLogoutReasonResponse?> =
        MutableStateFlow(null)
    override val userMessages: StateFlow<WssLogoutReasonResponse?> =
        userMessagesInternal.asStateFlow()

    private val deviceMessagesInternal: MutableStateFlow<String> = MutableStateFlow("")
    override val deviceMessages: StateFlow<String> = deviceMessagesInternal.asStateFlow()

    override suspend fun connect(authToken: String) {
        Log.i("websocket", "wsServer_connect_start")
        if (!connectedToWSSInternal.value) {
            Log.i("websocket", "wsServer_connect_before_try")
            try {
                httpClientForWS.webSocket(
                    method = HttpMethod.Get,
                    host = HTTPSERVER.wsServer,
                    port = 8000,
//                    path = "/"
                ) {
                    connectedToWSSInternal.value = true
                    Log.i("websocket", "value_emit_${connectedToWSSInternal.value}")

                    val data = constructFirstMessage(authToken)
                    Log.i("websocket_firstMessage", data)

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
//                connectedToWSSInternal.value = false
                connectedToWSSInternal.emit(false)
                Log.i("websocket", "value_emit_${connectedToWSSInternal.value}")
                Log.i("websocket", "error")
            }
        }
    }

    private fun deserializeData(data: String) {
//        try {
//            val parsed = gson.fromJson(data, WssLogoutReasonResponse::class.java)
//            userMessagesInternal.value = parsed
//        } catch (e: JsonSyntaxException) {
//            Log.i("websocket_parser", "not a user logout message")
//        } catch (e: Throwable){
//            Log.i("websocket_parser", "jebo te otac")
//        }
        //another try for device messages or other
    }

    private fun constructFirstMessage(authToken: String): String {
//        return gson.toJson(
//            WssConnectUserMessage(
//                data = WssConnectUserMessageData(
//                    authToken = authToken
//                )
//            )
//        )
        return "{\"data\":{\"authToken\":\"${authToken}\",\"frontendType\":2},\"messageType\":\"connectUser\"}"
    }
}
