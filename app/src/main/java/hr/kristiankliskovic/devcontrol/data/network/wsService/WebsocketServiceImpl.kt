package hr.kristiankliskovic.devcontrol.data.network.wsService

import android.util.Log
import androidx.compose.runtime.collectAsState
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import hr.kristiankliskovic.devcontrol.data.network.HTTPSERVER
import hr.kristiankliskovic.devcontrol.data.network.model.WssConnectUserMessage
import hr.kristiankliskovic.devcontrol.data.network.model.WssConnectUserMessageData
import hr.kristiankliskovic.devcontrol.data.network.model.WssLogoutReasonResponse
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.BitSet

class WebsocketServiceImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val client: HttpClient,
    private val gson: Gson,
) {
    private val httpClientForWS: HttpClient =
        HttpClient(CIO) {
            install(WebSockets) {
                pingInterval = 20_000
            }
        }

    private val connectedToWSSInternal: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val connectedToWSS: StateFlow<Boolean> = connectedToWSSInternal.asStateFlow()

    private val userMessagesInternal: MutableStateFlow<WssLogoutReasonResponse?> =
        MutableStateFlow(null)
    val userMessages: StateFlow<WssLogoutReasonResponse?> = userMessagesInternal.asStateFlow()

    private val deviceMessagesInternal: MutableStateFlow<String> = MutableStateFlow("")
    val deviceMessages: StateFlow<String> = deviceMessagesInternal.asStateFlow()

    suspend fun connect(authToken: String) {
        Log.i("websocket", "wsServer_connect_start")
        if (!connectedToWSS.value) {
            Log.i("websocket", "wsServer_connect_before_try")
            try {
                httpClientForWS.webSocket(
                    method = HttpMethod.Get,
                    host = HTTPSERVER.wsServer,
                    port = 8000,
//                    path = "/"
                ) {
                    connectedToWSSInternal.emit(true)
                    Log.i("websocket", "value_emit_${connectedToWSSInternal.value}")

                    val data = constructFirstMessage(authToken)
                    Log.i("websocket_firstMessage", data)

                    while (true) {
                        val othersMessage = incoming.receive() as? Frame.Text
                        val message = othersMessage?.readText()
                        if (message != null) {
                            Log.i("websocket", message)
                            deserializeData(message)
                        }
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
        try {
            val parsed = gson.fromJson(data, WssLogoutReasonResponse::class.java)
            userMessagesInternal.value = parsed
        } catch (e: JsonSyntaxException) {
            Log.i("websocket_parser", "not a user logout message")
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
}
