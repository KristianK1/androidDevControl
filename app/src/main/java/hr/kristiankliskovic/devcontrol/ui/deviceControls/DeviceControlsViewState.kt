package hr.kristiankliskovic.devcontrol.ui.deviceControls

import androidx.compose.runtime.Composable
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceComplexGroupViewState
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceGroupViewState

data class DeviceControlsViewState(
    val deviceId: Int,
    val deviceName: String,
    val groupsViewStates: List<DeviceGroupViewState>,
    val complexGroupsViewStates: List<DeviceComplexGroupViewState>,
    val deviceOnline: Boolean,
) {
    companion object {
        fun empty(): DeviceControlsViewState {
            return DeviceControlsViewState(
                deviceId = -1,
                deviceName = "",
                groupsViewStates = listOf(),
                complexGroupsViewStates = listOf(),
                deviceOnline = false
            )
        }
    }
}

