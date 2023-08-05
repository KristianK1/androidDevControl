package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add

import hr.kristiankliskovic.devcontrol.model.*

//data class AddTriggerViewState(
//    val device: Device,
//)

data class TriggerSourceDevicesViewState(
    val devices: List<TriggerSourceDeviceViewState>,
) {
    companion object {
        fun empty(): TriggerSourceDevicesViewState {
            return TriggerSourceDevicesViewState(
                devices = listOf()
            )
        }
    }
}

data class TriggerSourceDeviceViewState(
    val id: Int,
    val name: String,
    val groups: List<TriggerSourceGroupViewState>,
    val complexGroups: List<TriggerSourceComplexGroupViewState>,
) {
    companion object {
        fun empty(): TriggerSourceDeviceViewState {
            return TriggerSourceDeviceViewState(
                id = -1,
                name = "",
                groups = listOf(),
                complexGroups = listOf(),
            )
        }
    }
}

data class TriggerSourceGroupViewState(
    val groupId: Int,
    val groupName: String,
    val fields: List<TriggerSourceFieldViewState>,
)

data class TriggerSourceFieldViewState(
    val fieldId: Int,
    val fieldName: String,
    val fieldData: BasicDeviceField,
)

data class TriggerSourceComplexGroupViewState(
    val complexGroupId: Int,
    val complexGroupName: String,
    val states: List<TriggerSourceComplexGroupStateViewState>,
)

data class TriggerSourceComplexGroupStateViewState(
    val stateId: Int,
    val stateName: String,
    val fields: List<TriggerSourceFieldViewState>,
)






sealed class TriggerSourceDataViewState

data class TriggerSourceAdress_fieldInGroupViewState(
    var deviceId: Int? = null,
    var groupId: Int? = null,
    var fieldId: Int? = null,
) : TriggerSourceDataViewState()

data class TriggerSourceAdress_fieldInComplexGroupViewState(
    var deviceId: Int? = null,
    var complexGroupId: Int? = null,
    var stateId: Int? = null,
    var fieldId: Int? = null,
) : TriggerSourceDataViewState()

data class TriggerTimeSourceDataViewState(
    var type: ETriggerTimeType,
    var firstTimeStamp: String? = null,
) : TriggerSourceDataViewState()

sealed class TriggerSettingsViewState

data class NumericTriggerViewState(
    var value: Float?  = null,
    var second_value: Float? = null,
    var type: ENumericTriggerType,
) : TriggerSettingsViewState()

data class TextTriggerViewState(
    var value: String? = null,
    var type: ETextTriggerType,
) : TriggerSettingsViewState()

data class MCTriggerViewState(
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









