package hr.kristiankliskovic.devcontrol.ui.adminPanelListOfDevices

data class AdminPanelHomeViewState(
    val devices: List<AdminPanelHomeDeviceViewState>,
)

data class AdminPanelHomeDeviceViewState(
    val deviceName: String,
    val deviceId: Int,
)
