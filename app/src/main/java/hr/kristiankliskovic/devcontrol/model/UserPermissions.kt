package hr.kristiankliskovic.devcontrol.model

data class AllUserRightsToDevice(
    val permissions: List<UserPermission>,
    val groupRights: List<UserRightToGroup>,
    val complexGroupRights: List<UserRightToComplexGroup>,
)

data class UserRightToGroup(
    val groupId: Int,
    val permissions: List<UserPermission>,
    val fields: List<UserPermissionToField>,
)

data class UserPermissionToField(
    val fieldId: Int,
    val permissions: List<UserPermission>,
)

data class UserRightToComplexGroup(
    val complexGroupId: Int,
    val permissions: List<UserPermission>,
)

data class UserPermission(
    val userId: Int,
    val username: String,
    val readOnly: Boolean,
)
