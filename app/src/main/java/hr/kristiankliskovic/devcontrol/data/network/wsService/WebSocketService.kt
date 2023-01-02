package hr.kristiankliskovic.devcontrol.data.network.wsService

import hr.kristiankliskovic.devcontrol.data.network.model.WssLogoutReason
import kotlinx.coroutines.flow.StateFlow

interface WebSocketService {
    val connectedToWSS: StateFlow<Boolean>

    val userMessages: StateFlow<WssLogoutReason?>
    val deviceMessages: StateFlow<String>

    suspend fun connect(token: String)
    suspend fun disconnect()
    suspend fun resetUserMessages()
}
