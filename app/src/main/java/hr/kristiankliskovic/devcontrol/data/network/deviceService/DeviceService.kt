package hr.kristiankliskovic.devcontrol.data.network.deviceService

import hr.kristiankliskovic.devcontrol.data.network.model.UserPermissionsForDeviceResponse

interface DeviceService {

    suspend fun changeNumericField(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValue: Float,
    ): Boolean

    suspend fun changeTextField(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValue: String,
    ): Boolean

    suspend fun changeButtonField(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValue: Boolean,
    ): Boolean

    suspend fun changeMCField(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValue: Int,
    ): Boolean

    suspend fun changeRGBField(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValueR: Int,
        fieldValueG: Int,
        fieldValueB: Int,
    ): Boolean

    suspend fun changeNumericFieldInComplexGroup(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValue: Float,
    ): Boolean

    suspend fun changeTextFieldInComplexGroup(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValue: String,
    ): Boolean

    suspend fun changeButtonFieldInComplexGroup(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValue: Boolean,
    ): Boolean

    suspend fun changeMCFieldInComplexGroup(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValue: Int,
    ): Boolean

    suspend fun changeRGBFieldInComplexGroup(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValueR: Int,
        fieldValueG: Int,
        fieldValueB: Int,
    ): Boolean

    suspend fun changeComplexGroupState(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        state: Int,
    ): Boolean

    suspend fun changeDeviceAdmin(
        authToken: String,
        deviceId: Int,
        userId: Int,
    ): Boolean

    suspend fun addNewDevice(
        authToken: String,
        deviceName: String,
        deviceKey: String?,
    ): Boolean

    suspend fun deleteDevice(
        authToken: String,
        deviceId: Int,
    ): Boolean

    suspend fun addUserPermissionToDevice(
        authToken: String,
        userId: Int,
        deviceId: Int,
        readOnly: Boolean,
    ): Boolean

    suspend fun addUserPermissionToGroup(
        authToken: String,
        userId: Int,
        deviceId: Int,
        groupId: Int,
        readOnly: Boolean,
    ): Boolean

    suspend fun addUserPermissionToField(
        authToken: String,
        userId: Int,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        readOnly: Boolean,
    ): Boolean

    suspend fun addUserPermissionToComplexGroup(
        authToken: String,
        userId: Int,
        deviceId: Int,
        complexGroupId: Int,
        readOnly: Boolean,
    ): Boolean

    suspend fun deleteUserPermissionToDevice(
        authToken: String,
        userId: Int,
        deviceId: Int,
    ): Boolean

    suspend fun deleteUserPermissionToGroup(
        authToken: String,
        userId: Int,
        deviceId: Int,
        groupId: Int,
    ): Boolean

    suspend fun deleteUserPermissionToField(
        authToken: String,
        userId: Int,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
    ): Boolean

    suspend fun deleteUserPermissionToComplexGroup(
        authToken: String,
        userId: Int,
        deviceId: Int,
        complexGroupId: Int,
    ): Boolean

    suspend fun getUserPermissionsForDevice(
        authToken: String,
        deviceId: Int,
    ): UserPermissionsForDeviceResponse?
}
