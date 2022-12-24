package hr.kristiankliskovic.devcontrol.ui.myDevices

import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.DeviceNameAndStatusViewState

data class MyDevicesViewState(
    val devices: List<DeviceNameAndStatusViewState>,
)
