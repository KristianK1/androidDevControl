package hr.kristiankliskovic.devcontrol.data.network.wsService

import android.util.Log
import hr.kristiankliskovic.devcontrol.data.network.HTTPSERVER
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class WebsocketServiceImpl(
    private val client: HttpClient,
    private val ioDispatcher: CoroutineDispatcher,
) {
//    private suspend fun DefaultClientWebSocketSession.inputMessages() {
//        while (true) {
//            for (message in incoming) {
//                message as? Frame.Text ?: continue
//                val receivedText = message.readText()
//                Log.i("websocket", receivedText)
//            }
//        }
//    }

    suspend fun connect() {
        val client = HttpClient(CIO) {
            install(WebSockets) {
                pingInterval = 20_000
            }
        }
        client.webSocket(
            method = HttpMethod.Get,
            host = HTTPSERVER.wsServer,
            port = 8000
        ) {
            while (true) {
                val othersMessage = incoming.receive() as? Frame.Text
                val message = othersMessage?.readText()
                if (message != null) {
                    Log.i("websocket", message)
                }
            }
        }
    }
}
