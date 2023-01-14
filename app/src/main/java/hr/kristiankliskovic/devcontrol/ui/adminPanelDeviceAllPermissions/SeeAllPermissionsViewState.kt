package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions

data class SeeAllPermissionsViewState(
    val deviceId: Int,
    val deviceName: String,
    val permissions: List<UserPermissionViewState>,
    val groupPermissions: List<UserPermissionToGroupViewState>,
    val complexGroupPermissions: List<UserPermissionToComplexGroupViewState>,
){
    companion object{
        fun getEmptyObject(): SeeAllPermissionsViewState =
            SeeAllPermissionsViewState(
                deviceId = 0,
                deviceName = "",
                permissions = listOf(),
                groupPermissions = listOf(),
                complexGroupPermissions = listOf(),
            )
    }
}

data class UserPermissionToGroupViewState(
    val groupId: Int,
    val groupName: String,
    val permissions: List<UserPermissionViewState>,
    val fields: List<UserPermissionToFieldViewState>,
)

data class UserPermissionToFieldViewState(
    val fieldId: Int,
    val fieldName: String,
    val fieldType: String,
    val permissions: List<UserPermissionViewState>,
)

data class UserPermissionToComplexGroupViewState(
    val complexGroupId: Int,
    val complexGroupName: String,
    val permissions: List<UserPermissionViewState>,
)

data class UserPermissionViewState(
    val userId: Int,
    val username: String,
    val readOnly: Boolean,
)
