package hr.kristiankliskovic.devcontrol.ui.deviceControls

import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceComplexGroupStateViewState
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceGroupViewState

data class DeviceControlsViewState(
    val groupsViewStates: List<DeviceGroupViewState>,
    val complexGroupsViewStates: List<DeviceComplexGroupStateViewState>,
    val deviceOnline: Boolean,
)


