package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission

data class AddPermissionViewState(
    val device: AddPermissionDeviceViewState,
    val users: List<AddPermissionUserViewState>,
)

data class AddPermissionDeviceViewState(
    val groups: List<AddPermissionGroupViewState>,
    val complexGroups: List<AddPermissionComplexGroupViewState>,
)

data class AddPermissionGroupViewState(
    val groupId: Int,
    val groupName: String,
    val fields: List<AddPermissionFieldViewState>,
)

data class AddPermissionFieldViewState(
    val fieldId: Int,
    val fieldName: String,
)

data class AddPermissionComplexGroupViewState(
    val complexGroupId: Int,
    val complexGroupName: String,
)

data class AddPermissionUserViewState(
    val userId: Int,
    val username: String,
)
