package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions

data class ChangeDeviceAdminViewState(
    val users: List<ChangeDeviceAdminUserViewState>
){
    companion object{
        fun getEmptyViewState(): ChangeDeviceAdminViewState{
            return ChangeDeviceAdminViewState(
                users = listOf(),
            )
        }
    }
}

data class ChangeDeviceAdminUserViewState(
    val userId: Int,
    val username: String,
)
