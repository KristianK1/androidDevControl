package hr.kristiankliskovic.devcontrol.model

import android.util.Log
import com.google.gson.Gson

enum class ENumericTriggerType {
    Bigger,
    Smaller,
    Equal,
    Inbetween,
    NotInBetween,
}

enum class ETextTriggerType {
    StartsWith,
    EndsWith,
    Contains,
    IsEqualTo,
    IsNotEqualTo,
}

enum class EMCTriggerType {
    IsEqualTo,
    IsNotEqualTo,
}

enum class ERGBTriggerType_numeric {
    Bigger,
    Smaller,
    Equal,
    Inbetween,
    NotInBetween,
}

enum class ERGBTriggerType_context {
    R,
    G,
    B,
}

sealed class TriggerSettings()

data class INumericTrigger(
    val value: Float,
    val second_value: Float? = null,
    val type: ENumericTriggerType,
) : TriggerSettings()

data class ITextTrigger(
    val value: String,
    val type: ETextTriggerType,
) : TriggerSettings()

data class IMCTrigger(
    val value: Int,
    val type: EMCTriggerType,
) : TriggerSettings()

data class IRGBTrigger(
    val value: Int,
    val second_value: Int?,
    val type: ERGBTriggerType_numeric,
    val contextType: ERGBTriggerType_context,
) : TriggerSettings()

data class IBooleanTrigger(
    val value: Boolean,
) : TriggerSettings() {
    companion object {
        fun empty(): IBooleanTrigger {
            return IBooleanTrigger(
                value = false,
            )
        }
    }
}

enum class ETriggerSourceType {
    FieldInGroup,
    FieldInComplexGroup,
    TimeTrigger,
}

sealed class TriggerSourceData()

data class ITriggerSourceAdress_fieldInGroup(
    val deviceId: Int,
    val groupId: Int,
    val fieldId: Int,
) : TriggerSourceData() {
    companion object {
        fun empty(): ITriggerSourceAdress_fieldInGroup {
            return ITriggerSourceAdress_fieldInGroup(
                deviceId = -1,
                groupId = -1,
                fieldId = -1,
            )
        }
    }
}

data class ITriggerSourceAdress_fieldInComplexGroup(
    val deviceId: Int,
    val complexGroupId: Int,
    val stateId: Int,
    val fieldId: Int,
) : TriggerSourceData()

enum class ETriggerTimeType {
    Once,
    Daily,
    Weekly,
    // ChooseDaysInWeek,
    // Monthly,
    // Wearly,
}

data class ITriggerTimeSourceData(
    val type: ETriggerTimeType,
    val firstTimeStamp: String,
    val lastRunTimestamp: String,
) : TriggerSourceData()

sealed class TriggerResponse()

data class ITriggerEmailResponse(
    val emailSubject: String,
    val emailText: String,
) : TriggerResponse()

data class ITriggerMobileNotificationResponse(
    val notificationTitle: String,
    val notificationText: String,
) : TriggerResponse() {
    companion object {
        fun empty(): ITriggerMobileNotificationResponse {
            return ITriggerMobileNotificationResponse(
                notificationTitle = "",
                notificationText = "",
            )
        }
    }
}

data class ITriggerSettingValueResponse_fieldInGroup(
    val deviceId: Int,
    val groupId: Int,
    val fieldId: Int,
    val value: Any,
) : TriggerResponse() {
    companion object {
        fun empty(): ITriggerSourceAdress_fieldInGroup {
            return ITriggerSourceAdress_fieldInGroup(
                deviceId = -1,
                groupId = -1,
                fieldId = -1,
            )
        }
    }
}

data class ITriggerSettingsValueResponse_fieldInComplexGroup(
    val deviceId: Int,
    val complexGroupId: Int,
    val complexGroupState: Int,
    val fieldId: Int,
    val value: Any,
) : TriggerResponse()

enum class ETriggerResponseType {
    Email,
    MobileNotification,
    SettingValue_fieldInGroup,
    SettingValue_fieldInComplexGroup,
}

data class ITrigger(
    var id: Int,
    var name: String,
    var userId: Int,
    var sourceType: ETriggerSourceType,
    var sourceData: TriggerSourceData,
    var fieldType: String, // Replace this with a sealed class hierarchy in Kotlin
    var settings: TriggerSettings,
    var responseType: ETriggerResponseType,
    var responseSettings: TriggerResponse,
) {
    companion object {
        fun empty(): ITrigger {
            return ITrigger(
                id = -1,
                name = "",
                userId = -1,
                sourceType = ETriggerSourceType.FieldInGroup,
                sourceData = ITriggerSourceAdress_fieldInGroup.empty(),
                fieldType = "",
                settings = IBooleanTrigger.empty(),
                responseType = ETriggerResponseType.Email,
                responseSettings = ITriggerMobileNotificationResponse.empty()
            )
        }
    }
    fun print(){
        Log.i("triggerDataPrint_sourceData", Gson().toJson(this))
    }
}
