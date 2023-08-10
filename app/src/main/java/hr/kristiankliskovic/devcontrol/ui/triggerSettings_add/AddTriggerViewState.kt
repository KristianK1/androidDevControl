package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import hr.kristiankliskovic.devcontrol.model.*
import java.util.*


data class AddTriggerViewState(
    var triggerName: String = "",

    var sourceType: MutableState<ETriggerSourceType> = mutableStateOf(ETriggerSourceType.FieldInGroup),
    var sourceAddress: MutableState<TriggerSourceAddressViewState> = mutableStateOf(
        TriggerSourceAddressViewState()),

    var timeTriggerType: MutableState<ETriggerTimeType> = mutableStateOf(ETriggerTimeType.Once),
    var timeSourceTime: MutableState<Int?> = mutableStateOf(null),
    var timeSourceDate: MutableState<Calendar?> = mutableStateOf(null),

    var sourceSettings: MutableState<TriggerSettingsViewState?> = mutableStateOf(null),
)

data class DeviceEntityViewState(
    val id: Int,
    val name: String,
)

data class TriggerSourceAddressViewState(
    var selectedDevice: MutableState<DeviceEntityViewState?> = mutableStateOf(null),
    var selectedGroup: MutableState<DeviceEntityViewState?> = mutableStateOf(null),
    var selectedState: MutableState<DeviceEntityViewState?> = mutableStateOf(null),
    var selectedField: MutableState<DeviceEntityViewState?> = mutableStateOf(null),

    var sourceDevicesChoices: MutableList<DeviceEntityViewState> = mutableListOf(),
    var sourceGroupsChoices: MutableList<DeviceEntityViewState> = mutableListOf(),
    var sourceComplexGroupStatesChoices: MutableList<DeviceEntityViewState> = mutableListOf(),
    var sourceFieldChoices: MutableList<DeviceEntityViewState> = mutableListOf(),
)


sealed class TriggerSettingsViewState

data class NumericTriggerViewState(
    var prefix: String,
    var sufix: String,
    var minimum: Float,
    var maximum: Float,
    var step: Float,
    var value: MutableState<Float?> = mutableStateOf(null),
    var second_value: MutableState<Float?> = mutableStateOf(null),
    var type: MutableState<ENumericTriggerType>,
) : TriggerSettingsViewState()

data class TextTriggerViewState(
    var value: MutableState<String>,
    var type: MutableState<ETextTriggerType>,
) : TriggerSettingsViewState()

data class MCTriggerViewState(
    var values: List<String>,
    var value: MutableState<Int?> = mutableStateOf(null),
    var type: MutableState<EMCTriggerType>,
) : TriggerSettingsViewState()

data class RGBTriggerViewState(
    var value: MutableState<Int?> = mutableStateOf(null),
    var second_value: MutableState<Int?> = mutableStateOf(null),
    var type: MutableState<ERGBTriggerType_numeric>,
    var contextType: MutableState<ERGBTriggerType_context>,
) : TriggerSettingsViewState()

data class BooleanTriggerViewState(
    var value: MutableState<Boolean>,
) : TriggerSettingsViewState()








