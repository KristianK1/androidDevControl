package hr.kristiankliskovic.devcontrol.model


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
