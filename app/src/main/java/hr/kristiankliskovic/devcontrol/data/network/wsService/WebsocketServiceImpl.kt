package hr.kristiankliskovic.devcontrol.data.network.wsService

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import hr.kristiankliskovic.devcontrol.data.network.HTTPSERVER
import hr.kristiankliskovic.devcontrol.data.network.model.*
import hr.kristiankliskovic.devcontrol.data.network.wsService.parser.deviceData.WSDataParser
import hr.kristiankliskovic.devcontrol.model.Device
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

class WebsocketServiceImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val wsDataParser: WSDataParser,
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

    private val deviceMessagesInternal: MutableStateFlow<Device?> = MutableStateFlow(null)
    override val deviceMessages: StateFlow<Device?> = deviceMessagesInternal.asStateFlow()

    private val deviceRemovedInternal: MutableStateFlow<Int> = MutableStateFlow(-1)
    override val deviceRemoved: StateFlow<Int> = deviceRemovedInternal.asStateFlow()

    override suspend fun connect(token: String) {
        authToken = token
        if (!connectedToWSSInternal.value) {
            while (true) {
                if (authToken == null) break;
                try {
                    httpClientForWS.webSocket(
                        method = HttpMethod.Get,
                        host = HTTPSERVER.wsServer,
                        port = 8000,
//                        path = "/"
                    ) {
                        currentWSSclient = this
                        connectedToWSSInternal.value = true
                        send(constructFirstMessage(authToken!!)) //!! is kinda okey here
                        while (true) {
                            val othersMessage = incoming.receive() as? Frame.Text
                            val message = othersMessage?.readText()
                            if (message != null) {
                                deserializeData(message)
                            }
                        }
                    }
                } catch (e: Throwable) {
                    connectedToWSSInternal.emit(false)
                }
            }
        }
    }

    override suspend fun disconnect() {
        authToken = null
        currentWSSclient?.close()
        currentWSSclient = null
        connectedToWSSInternal.emit(false)
    }

    private suspend fun deserializeData(data: String) {
        Log.i("deviceData", "here")
        lateinit var messageType: WSSReceivingMessageTypes
        try {
            messageType = wsDataParser.getWSSMessageType(data)
        } catch (e: Throwable) {
            Log.i("deviceData", "unknown message type - deserializeData- WSSService")
            return
        }
        when (messageType) {
            WSSReceivingMessageTypes.DeviceData -> {
                val parsed = wsDataParser.parseDeviceData(data)
                deviceMessagesInternal.value = parsed
            }
            WSSReceivingMessageTypes.UserMessage -> {
                val parsed = wsDataParser.parseUserMessages(data)
                when (parsed.logoutReason) {
                    0 -> userMessagesInternal.value = WssLogoutReason.DeletedUser
                    1 -> userMessagesInternal.value = WssLogoutReason.ChangedPassword
                    2 -> userMessagesInternal.value = WssLogoutReason.LogoutAll
                    3 -> userMessagesInternal.value = WssLogoutReason.LogoutMyself
                }
            }
            WSSReceivingMessageTypes.DeviceDeleted -> {
                val parsed = wsDataParser.parseDeviceDeletedMessage(data)
                deviceRemovedInternal.value = parsed
            }
            WSSReceivingMessageTypes.LostRights -> {
                val parsed = wsDataParser.parseLostRightsToDeviceMessage(data)
                deviceRemovedInternal.value = parsed
            }
        }
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

    override suspend fun resetDeviceMessages(){
        deviceMessagesInternal.emit(null)
    }
}
