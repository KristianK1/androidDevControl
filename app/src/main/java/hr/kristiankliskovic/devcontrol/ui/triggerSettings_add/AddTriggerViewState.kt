package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import hr.kristiankliskovic.devcontrol.model.*
import java.util.*


data class AddTriggerViewState(
    var triggerName: String = "",

    var sourceType: MutableState<ETriggerSourceType> = mutableStateOf(ETriggerSourceType.FieldInGroup),
    var sourceAddress: MutableState<TriggerSourceAddressViewState> = mutableStateOf(TriggerSourceAddressViewState()),

    var timeTriggerType: MutableState<ETriggerTimeType> = mutableStateOf(ETriggerTimeType.Once),
    var timeSourceTime: MutableState<Int?> = mutableStateOf(null),
    var timeSourceDate: MutableState<Calendar?> = mutableStateOf(null),

    var sourceSettings: TriggerSettingsViewState? = null,
    var fieldData: BasicDeviceField? = null, //TODO
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
    var minimum: Float,
    var maximum: Float,
    var step: Float,
    var value: Float? = null,
    var second_value: Float? = null,
    var type: ENumericTriggerType,
) : TriggerSettingsViewState() {
    companion object {
        fun empty(): NumericTriggerViewState {
            return NumericTriggerViewState(
                type = ENumericTriggerType.Bigger,
                minimum = -1f,
                maximum = -1f,
                step = -1f,
            )
        }
    }
}

data class TextTriggerViewState(
    var value: String? = null,
    var type: ETextTriggerType,
) : TriggerSettingsViewState()

data class MCTriggerViewState(
    var values: List<String>,
    var value: Int? = null,
    var type: EMCTriggerType,
) : TriggerSettingsViewState()

data class RGBTriggerViewState(
    var value: Int? = null,
    var second_value: Int? = null,
    var type: ERGBTriggerType_numeric,
    var contextType: ERGBTriggerType_context,
) : TriggerSettingsViewState()

data class BooleanTriggerViewState(
    var value: Boolean,
) : TriggerSettingsViewState()








