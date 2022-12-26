package hr.kristiankliskovic.devcontrol.model

data class AllUserRightsToDevice(
    val deviceId: Int,
    val deviceRights: List<UserRightToDevice>,
    val groupRights: List<UserRightToGroup>,
    val fieldRights: List<UserRightToField>,
    val complexGroupRights: List<UserRightToComplexGroup>,
)

data class UserRightToDevice(
    val deviceId: Int,
    val userId: Int,
    val readOnly: Boolean
)

data class UserRightToComplexGroup(
    val deviceId: Int,
    val complexGroupId: Int,
    val userId: Int,
    val readOnly: Boolean
)

data class UserRightToGroup(
    val deviceId: Int,
    val groupId: Int,
    val userId: Int,
    val readOnly: Boolean
)

data class UserRightToField(
    val deviceId: Int,
    val groupId: Int,
    val fieldId: Int,
    val userId: Int,
    val readOnly: Boolean
)
