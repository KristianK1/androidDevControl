package hr.kristiankliskovic.devcontrol.ui.adminPanelHome

data class AdminPanelHomeViewState(
    val devices: List<AdminPanelHomeDeviceViewState>,
){
    companion object {
        fun empty(): AdminPanelHomeViewState {
            return AdminPanelHomeViewState(
                devices = listOf(),
            )
        }
    }
}

data class AdminPanelHomeDeviceViewState(
    val deviceName: String,
    val deviceId: Int,
)
