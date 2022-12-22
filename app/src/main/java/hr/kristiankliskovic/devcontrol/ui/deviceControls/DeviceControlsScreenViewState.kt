package hr.kristiankliskovic.devcontrol.ui.deviceControls

import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceComplexGroupStateViewState
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceGroupViewState

data class DeviceControlsScreenViewState(
    val deviceId: Int,
    val groupsViewStates: List<DeviceGroupViewState>,
    val complexGroupsViewStates: List<DeviceComplexGroupStateViewState>,
    val deviceOnline: Boolean,
)

