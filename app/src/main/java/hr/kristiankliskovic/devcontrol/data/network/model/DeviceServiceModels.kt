package hr.kristiankliskovic.devcontrol.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ChangeNumericField(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val fieldId: Int,
    val fieldValue: Float,
)

@Serializable
data class ChangeTextField(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val fieldId: Int,
    val fieldValue: String,
)

@Serializable
data class ChangeButtonField(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val fieldId: Int,
    val fieldValue: Boolean,
)

@Serializable
data class ChangeMCField(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val fieldId: Int,
    val fieldValue: Int,
)

@Serializable
data class ChangeRGBField(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val fieldId: Int,
    val fieldValue: RGBvalue,
)

@Serializable
data class RGBvalue(
    val R: Int,
    val G: Int,
    val B: Int,
)


@Serializable
data class ChangeNumericFieldInCG(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val stateId: Int,
    val fieldId: Int,
    val fieldValue: Float,
)

@Serializable
data class ChangeTextFieldInCG(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val stateId: Int,
    val fieldId: Int,
    val fieldValue: String,
)

@Serializable
data class ChangeButtonFieldInCG(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val stateId: Int,
    val fieldId: Int,
    val fieldValue: Boolean,
)

@Serializable
data class ChangeMCFieldInCG(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val stateId: Int,
    val fieldId: Int,
    val fieldValue: Int,
)

@Serializable
data class ChangeRGBFieldInCG(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val stateId: Int,
    val fieldId: Int,
    val fieldValue: RGBvalue,
)

@Serializable
data class ChangeComplexGroupState(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val state: Int,
)

@Serializable
data class ChangeDeviceAdminRequest(
    val authToken: String,
    val deviceId: Int,
    val userAdminId: Int,
)

@Serializable
data class AddNewDeviceRequest(
    val authToken: String,
    val deviceName: String,
    val deviceKey: String?,
)

@Serializable
data class DeleteDeviceRequest(
    val authToken: String,
    val deviceId: Int,
)

@Serializable
data class AddUserPermissionToDevice(
    val authToken: String,
    val userId: Int,
    val deviceId: Int,
    val readOnly: Boolean,
)

@Serializable
data class AddUserPermissionToGroup(
    val authToken: String,
    val userId: Int,
    val deviceId: Int,
    val groupId: Int,
    val readOnly: Boolean,
)

@Serializable
data class AddUserPermissionToField(
    val authToken: String,
    val userId: Int,
    val deviceId: Int,
    val groupId: Int,
    val fieldId: Int,
    val readOnly: Boolean,
)

@Serializable
data class AddUserPermissionToComplexGroup(
    val authToken: String,
    val userId: Int,
    val deviceId: Int,
    val complexGroupId: Int,
    val readOnly: Boolean,
)


@Serializable
data class DeleteUserPermissionToDevice(
    val authToken: String,
    val userId: Int,
    val deviceId: Int,
)

@Serializable
data class DeleteUserPermissionToGroup(
    val authToken: String,
    val userId: Int,
    val deviceId: Int,
    val groupId: Int,
)

@Serializable
data class DeleteUserPermissionToField(
    val authToken: String,
    val userId: Int,
    val deviceId: Int,
    val groupId: Int,
    val fieldId: Int,
)

@Serializable
data class DeleteUserPermissionToComplexGroup(
    val authToken: String,
    val userId: Int,
    val deviceId: Int,
    val complexGroupId: Int,
)

@Serializable
data class UserPermissionsForDeviceRequest(
    val authToken: String,
    val deviceId: Int,
)

@Serializable
data class UserPermissionsForDeviceResponse(
    val userPermissions: List<UserPermissionResponse>,
    val groups: List<UserPermissionsForDeviceResponseGroup>,
    val complexGroups: List<UserPermissionsForDeviceResponseComplexGroup>,
)

@Serializable
data class UserPermissionsForDeviceResponseGroup (
    val userPermissions: List<UserPermissionResponse>,
    val groupId: Int,
    val groupName: String,
    val fields: List<UserPermissionsForDeviceResponseField>
)

@Serializable
data class UserPermissionsForDeviceResponseField (
    val fieldId: Int,
    val fieldName: String,
    val fieldType: String,
    val userPermissions:List<UserPermissionResponse>
)

@Serializable
data class UserPermissionsForDeviceResponseComplexGroup(
    val userPermissions: List<UserPermissionResponse>,
    val complexGroupId: Int,
    val complexGroupName: String
)

@Serializable
data class UserPermissionResponse (
    val userId: Int,
    val username: String,
    val readOnly: Boolean,
)
