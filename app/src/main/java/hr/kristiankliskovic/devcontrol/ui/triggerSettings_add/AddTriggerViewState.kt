package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add

import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission.AddPermissionFieldViewState

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
