package hr.kristiankliskovic.devcontrol.data.network.wsService

import hr.kristiankliskovic.devcontrol.data.network.model.WssLogoutReasonResponse
import kotlinx.coroutines.flow.StateFlow

interface WebSocketService {
    val connectedToWSS: StateFlow<Boolean>
    val userMessages: StateFlow<WssLogoutReasonResponse?>
    val deviceMessages: StateFlow<String>

    suspend fun connect(authToken: String)
}
