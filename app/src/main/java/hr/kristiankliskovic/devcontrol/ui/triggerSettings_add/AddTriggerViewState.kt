package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import hr.kristiankliskovic.devcontrol.model.*
import java.util.*


data class AddTriggerViewState(
    var triggerName: String = "",

    var sourceType: MutableState<ETriggerSourceType> = mutableStateOf(ETriggerSourceType.FieldInGroup),
    var sourceAddress: MutableState<TriggerAddressViewState> = mutableStateOf(
        TriggerAddressViewState()),

    var timeTriggerType: MutableState<ETriggerTimeType> = mutableStateOf(ETriggerTimeType.Once),
    var timeSourceTime: MutableState<Int?> = mutableStateOf(null),
    var timeSourceDate: MutableState<Calendar?> = mutableStateOf(null),

    var sourceSettings: MutableState<TriggerSourceSettingsViewState?> = mutableStateOf(null),

    var responseType: MutableState<ETriggerResponseType> = mutableStateOf(ETriggerResponseType.SettingValue_fieldInGroup),
    var responseAddress: MutableState<TriggerAddressViewState> = mutableStateOf(
        TriggerAddressViewState()),

    var responseSettings: MutableState<TriggerResponseSettingsViewState?> = mutableStateOf(null),
    var notificationEmailViewState: MutableState<NotificationEmailViewState> = mutableStateOf(NotificationEmailViewState()),
)

data class DeviceEntityViewState(
    val id: Int,
    val name: String,
)

data class TriggerAddressViewState(
    var selectedDevice: MutableState<DeviceEntityViewState?> = mutableStateOf(null),
    var selectedGroup: MutableState<DeviceEntityViewState?> = mutableStateOf(null),
    var selectedState: MutableState<DeviceEntityViewState?> = mutableStateOf(null),
    var selectedField: MutableState<DeviceEntityViewState?> = mutableStateOf(null),

    var devicesChoices: MutableList<DeviceEntityViewState> = mutableListOf(),
    var groupsChoices: MutableList<DeviceEntityViewState> = mutableListOf(),
    var complexGroupStatesChoices: MutableList<DeviceEntityViewState> = mutableListOf(),
    var fieldChoices: MutableList<DeviceEntityViewState> = mutableListOf(),
)

sealed class TriggerSourceSettingsViewState

data class NumericTriggerSourceViewState(
    var prefix: String,
    var sufix: String,
    var minimum: Float,
    var maximum: Float,
    var step: Float,
    var value: MutableState<Float?> = mutableStateOf(null),
    var second_value: MutableState<Float?> = mutableStateOf(null),
    var type: MutableState<ENumericTriggerType>,
) : TriggerSourceSettingsViewState()

data class TextTriggerSourceViewState(
    var value: MutableState<String>,
    var type: MutableState<ETextTriggerType>,
) : TriggerSourceSettingsViewState()

data class MCTriggerSourceViewState(
    var values: List<String>,
    var value: MutableState<Int?> = mutableStateOf(null),
    var type: MutableState<EMCTriggerType>,
) : TriggerSourceSettingsViewState()

data class RGBTriggerSourceViewState(
    var value: MutableState<Int?> = mutableStateOf(null),
    var second_value: MutableState<Int?> = mutableStateOf(null),
    var type: MutableState<ERGBTriggerType_numeric>,
    var contextType: MutableState<ERGBTriggerType_context>,
) : TriggerSourceSettingsViewState()

data class BooleanTriggerSourceViewState(
    var value: MutableState<Boolean>,
) : TriggerSourceSettingsViewState()

sealed class TriggerResponseSettingsViewState

data class NumericTriggerResponseViewState(
    var prefix: String,
    var sufix: String,
    var minimum: Float,
    var maximum: Float,
    var step: Float,
    var value: MutableState<Float?> = mutableStateOf(null),
) : TriggerResponseSettingsViewState()

data class TextTriggerResponseViewState(
    var value: MutableState<String>,
) : TriggerResponseSettingsViewState()

data class MCTriggerResponseViewState(
    var values: List<String>,
    var value: MutableState<Int?> = mutableStateOf(null),
) : TriggerResponseSettingsViewState()

data class RGBTriggerResponseViewState(
    var value: MutableState<Int?> = mutableStateOf(null),
    var contextType: MutableState<ERGBTriggerType_context>,
) : TriggerResponseSettingsViewState()

data class BooleanTriggerResponseViewState(
    var value: MutableState<Boolean>,
) : TriggerResponseSettingsViewState()

data class NotificationEmailViewState(
    var title: MutableState<String> = mutableStateOf(""),
    var text: MutableState<String> = mutableStateOf(""),
)
