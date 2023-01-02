package hr.kristiankliskovic.devcontrol.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class WssConnectUserMessageData(
    val authToken: String,
    val frontendType: Int = 2  // AndroidApp = 2
)

@Serializable
data class WssConnectUserMessage(
    val data: WssConnectUserMessageData,
    val messageType: String = "connectUser",
)

@Serializable
data class WssLogoutReasonResponse(
    val logoutReason: Int,
)

enum class WssLogoutReason {
    DeletedUser,
    ChangedPassword,
    LogoutAll,
    LogoutMyself,
}
