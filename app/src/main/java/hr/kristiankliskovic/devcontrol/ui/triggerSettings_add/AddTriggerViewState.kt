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
    var selectedDevice: DeviceEntityViewState? = null,
    var selectedGroup: DeviceEntityViewState? = null,
    var selectedState: DeviceEntityViewState? = null,
    var selectedField: DeviceEntityViewState? = null,

    var sourceDevicesChoices: List<DeviceEntityViewState> = listOf(),
    var sourceGroupsChoices: List<DeviceEntityViewState> = listOf(),
    var sourceComplexGroupStatesChoices: List<DeviceEntityViewState> = listOf(),
    var sourceFieldChoices: List<DeviceEntityViewState> = listOf(),
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








