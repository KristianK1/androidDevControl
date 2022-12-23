package hr.kristiankliskovic.devcontrol.ui.deviceControls

import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceComplexGroupViewState
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceGroupViewState

data class DeviceControlsScreenViewState(
    val deviceId: Int,
    val deviceName: String,
    val groupsViewStates: List<DeviceGroupViewState>,
    val complexGroupsViewStates: List<DeviceComplexGroupViewState>,
    val deviceOnline: Boolean,
)

