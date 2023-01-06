package hr.kristiankliskovic.devcontrol.ui.myDevices

import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.DeviceNameAndStatusViewState

data class MyDevicesViewState(
    val devices: List<DeviceNameAndStatusViewState>,
) {
    companion object {
        fun empty(): MyDevicesViewState {
            return MyDevicesViewState(devices = listOf())
        }
    }
}
