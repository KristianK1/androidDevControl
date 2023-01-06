package hr.kristiankliskovic.devcontrol.data.network.model

import kotlinx.serialization.Serializable


@Serializable
data class WssConnectUserMessage(
    val data: WssConnectUserMessageData,
    val messageType: String = "connectUser",
)

@Serializable
data class WssConnectUserMessageData(
    val authToken: String,
    val frontendType: Int = 2,  // AndroidApp = 2
)


data class WssReceivingMessage(
    val messageType: String,
    val data: Any,
)

enum class WSSReceivingMessageTypes {
    DeviceData,
    UserMessage,
    DeviceDeleted,
    LostRights,
}

val WssReceivingMessageType_DeviceData = "deviceData"
val WssReceivingMessageType_UserMessage = "userMessage"
val WssReceivingMessageType_DeviceDeleted = "deviceDeleted"
val WssReceivingMessageType_lostRights = "lostRightsToDevice"

sealed class WSSReceivingMessageData()

@Serializable
data class WssLogoutReasonResponse(
    val logoutReason: Int,
) : WSSReceivingMessageData()

enum class WssLogoutReason {
    DeletedUser,
    ChangedPassword,
    LogoutAll,
    LogoutMyself,
}

data class WSSDeviceData(
    val id: Int,
    val deviceKey: String,
    val deviceName: String,
    val deviceFieldGroups: List<WSSGroup>,
    val deviceFieldComplexGroups: List<WSSComplexGroup>,
    val userAdminId: Int,
    val updateTimeStamp: Long,
    val isActive: Boolean,
) : WSSReceivingMessageData()

data class WSSGroup(
    val id: Int,
    val fields: List<WSSBasicFieldInGroup>,
    val groupName: String,
)

data class WSSBasicFieldInGroup(
    val deviceId: Int,
    val groupId: Int,
    val id: Int,
    val fieldName: String,
    val readOnly: Boolean,

    val fieldType: String, //'numeric' | 'text' | 'button' | 'multipleChoice' | 'RGB',
    val fieldValue: Any,
)

data class WSSBasicFieldInComplexGroup(
    val deviceId: Int,
    val groupId: Int,
    val id: Int,
    val fieldName: String,

    val fieldType: String, //'numeric' | 'text' | 'button' | 'multipleChoice' | 'RGB',
    val fieldValue: Any,
)

const val numericFieldLabel = "numeric"
const val textFieldLabel = "text"
const val buttonFieldLabel = "button"
const val multipleChoiceFieldLabel = "multipleChoice"
const val RGBFieldLabel = "RGB"

const val fieldDirectionInput = "input"
const val fieldDirectionOutput = "output"

//enum class FieldTypes{
//    numeric,
//    text,
//    button,
//    multipleChoice,
//    RGB
//}

data class WSSComplexGroup(
    val id: Int,
    val groupName: String,
    val currentState: Int,
    val fieldGroupStates: List<WSSComplexGroupState>,
    val readOnly: Boolean,
)

data class WSSComplexGroupState(
    val id: Int,
    val stateName: String,
    val fields: List<WSSBasicFieldInComplexGroup>,
)

data class WSSNumericField(
    val fieldValue: Float,
    // fieldControlType: 'slider' | 'upDownButtons',
    val minValue: Float,
    val maxValue: Float,
    val valueStep: Float,
    val fieldDirection: String,
)

data class WSSTextField(
    val fieldValue: String,
    val fieldDirection: String,
)

data class WSSButtonField(
    val fieldValue: Boolean,
    val fieldDirection: String,
)

data class WSSMultipleChoiceField(
    val fieldValue: Int,
    val values: List<String>,
    val fieldDirection: String,
)

data class WSSRGBField(
    val R: Int,
    val G: Int,
    val B: Int,
    val fieldDirection: String,
)
