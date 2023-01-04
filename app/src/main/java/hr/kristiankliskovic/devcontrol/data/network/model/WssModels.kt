package hr.kristiankliskovic.devcontrol.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class WssConnectUserMessageData(
    val authToken: String,
    val frontendType: Int = 2,  // AndroidApp = 2
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

@Serializable
data class WSSDeviceData(
    val id: Int,
    val deviceKey: String,
    val deviceName: String,
    val deviceFieldGroups: List<WSSGroup>,
    val deviceFieldComplexGroups: List<WSSComplexGroup>,
    val userAdminId: Int,
    val updateTimeStamp: Int,
)

@Serializable
data class WSSGroup(
    val id: Int,
    val fields: List<WSSBasicField>,
    val groupName: String,
)

@Serializable
data class WSSBasicField(
    val deviceId: Int,
    val groupId: Int,
    val id: Int,
    val fieldName: String,
    val readOnly: Boolean,

    val fieldType: String, //'numeric' | 'text' | 'button' | 'multipleChoice' | 'RGB',
//    val fieldValue: Any,
)

@Serializable
data class WSSComplexGroup(
    val id: Int,
    val groupName: String,
    val currentState: Int,
    val fieldGroupStates: List<WSSComplexGroupState>,
    val readOnly: Boolean,
)

@Serializable
data class WSSComplexGroupState(
    val id: Int,
    val stateName: String,
//    val fields: IDeviceFieldBasic[],
)
