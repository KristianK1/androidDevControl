package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions

data class SeeAllPermissionsViewState(
    val rights: List<UserRightViewState>,
    val groupRights: List<UserRightToGroupViewState>,
    val complexGroupRights: List<UserRightToComplexGroupViewState>
)

data class UserRightToGroupViewState(
    val groupId: Int,
    val groupName: String,
    val rights: List<UserRightViewState>,
    val fieldRights: List<UserRightToFieldViewState>
)

data class UserRightToFieldViewState(
    val fieldId: Int,
    val fieldName: String,
    val rights: List<UserRightViewState>
)

data class UserRightToComplexGroupViewState(
    val complexGroupId: Int,
    val complexGroupName: String,
    val rights: List<UserRightViewState>
)

data class UserRightViewState(
    val userId: Int,
    val username: String,
    val readOnly: Boolean
)
