package hr.kristiankliskovic.devcontrol.model

import android.util.Log
import com.google.gson.Gson
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

enum class ENumericTriggerType {
    Bigger,
    Smaller,
    Equal,
    Between,
    NotBetween,
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

enum class ERGBTriggerType_numeric{
    Bigger,
    Smaller,
    Equal,
    Inbetween,
    NotInBetween
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
) : TriggerSettings()

enum class ETriggerSourceType {
    FieldInGroup,
    FieldInComplexGroup,
    TimeTrigger
}

@Serializable
sealed class TriggerSourceData

data class ITriggerSourceAdress_fieldInGroup(
    val deviceId: Int,
    val groupId: Int,
    val fieldId: Int,
) : TriggerSourceData()

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
    DaysInWeek,
}

data class ITriggerTimeSourceData(
    val type: ETriggerTimeType,
    val firstTimeStamp: String,
    val lastRunTimestamp: String?,
    val daysInWeek: List<Boolean>,
) : TriggerSourceData()

@Serializable
sealed class TriggerResponse()

data class ITriggerEmailResponse(
    val emailSubject: String,
    val emailText: String,
) : TriggerResponse()

data class ITriggerMobileNotificationResponse(
    val notificationTitle: String,
    val notificationText: String,
) : TriggerResponse()

data class ITriggerSettingValueResponse_fieldInGroup(
    val deviceId: Int,
    val groupId: Int,
    val fieldId: Int,
    val value: Any,
) : TriggerResponse()

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

@Serializable
data class ITrigger(
    var id: Int,
    var name: String,
    var userId: Int,
    var sourceType: ETriggerSourceType,
    var sourceData: TriggerSourceData,
    var fieldType: String? = null, // Replace this with a sealed class hierarchy in Kotlin
    @Contextual
    var settings: TriggerSettings? = null,
    var responseType: ETriggerResponseType,
    var responseSettings: TriggerResponse,
) {
    companion object {
        fun print() {
            Log.i("triggerDataPrint_sourceData", Gson().toJson(this))
        }
    }
}
