package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions

data class ChangeDeviceAdminViewState(
    val users: List<ChangeDeviceAdminUserViewState>
)

data class ChangeDeviceAdminUserViewState(
    val userId: Int,
    val username: String,
)
