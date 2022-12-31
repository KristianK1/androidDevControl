package hr.kristiankliskovic.devcontrol.data.network.wsService

import android.util.Log
import androidx.compose.runtime.collectAsState
import hr.kristiankliskovic.devcontrol.data.network.HTTPSERVER
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
) {
    private val httpClientForWS: HttpClient =
        HttpClient(CIO) {
            install(WebSockets) {
                pingInterval = 20_000
            }
        }

    private val connectedToWSSInternal: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val connectedToWSS: StateFlow<Boolean> = connectedToWSSInternal.asStateFlow()

    private val userMessagesInternal: MutableStateFlow<String> = MutableStateFlow("")
    val userMessages: StateFlow<String> = userMessagesInternal.asStateFlow()

    private val deviceMessagesInternal: MutableStateFlow<String> = MutableStateFlow("")
    val deviceMessages: StateFlow<String> = deviceMessagesInternal.asStateFlow()

    suspend fun connect() {
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
                    Log.i("websocket","value_emit_${connectedToWSSInternal.value}")
                    while (true) {
                        val othersMessage = incoming.receive() as? Frame.Text
                        val message = othersMessage?.readText()
                        if (message != null) {
                            Log.i("websocket", message)
                            if(message.contains("user")){
                                Log.i("websocket", "user_messages")
                                userMessagesInternal.emit(message)
                            }
                            else if(message.contains("device")){
                                deviceMessagesInternal.emit(message)
                            }
                        }
                    }
                }
            } catch (e: Throwable) {
//                connectedToWSSInternal.value = false
                connectedToWSSInternal.emit(false)
                Log.i("websocket","value_emit_${connectedToWSSInternal.value}")
                Log.i("websocket", "error")
            }
        }
    }
}
