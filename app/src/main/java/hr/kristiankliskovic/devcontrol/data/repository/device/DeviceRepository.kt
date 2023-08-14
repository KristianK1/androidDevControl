package hr.kristiankliskovic.devcontrol.data.repository.device

import hr.kristiankliskovic.devcontrol.data.network.model.GetAllUserTriggersResponse
import hr.kristiankliskovic.devcontrol.data.network.model.UserPermissionsForDeviceResponse
import hr.kristiankliskovic.devcontrol.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.CopyOnWriteArrayList

interface DeviceRepository {
    val devices: Flow<CopyOnWriteArrayList<Device>>
    fun getDevice(deviceId: Int): Flow<Device?>

    suspend fun addNewDevice(
        deviceName: String,
        deviceKey: String?,
    ): Boolean

    suspend fun changeNumericField(
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValue: Float,
    ): Boolean

    suspend fun changeTextField(
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValue: String,
    ): Boolean

    suspend fun changeButtonField(
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValue: Boolean,
    ): Boolean

    suspend fun changeMCField(
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValue: Int,
    ): Boolean

    suspend fun changeRGBField(
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValueR: Int,
        fieldValueG: Int,
        fieldValueB: Int,
    ): Boolean

    suspend fun changeNumericFieldInComplexGroup(
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValue: Float,
    ): Boolean

    suspend fun changeTextFieldInComplexGroup(
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValue: String,
    ): Boolean

    suspend fun changeButtonFieldInComplexGroup(
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValue: Boolean,
    ): Boolean

    suspend fun changeMCFieldInComplexGroup(
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValue: Int,
    ): Boolean

    suspend fun changeRGBFieldInComplexGroup(
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValueR: Int,
        fieldValueG: Int,
        fieldValueB: Int,
    ): Boolean

    suspend fun changeComplexGroupState(
        deviceId: Int,
        groupId: Int,
        state: Int,
    ): Boolean

    suspend fun changeDeviceAdmin(
        deviceId: Int,
        adminId: Int,
    ): Boolean

    suspend fun deleteDevice(deviceId: Int): Boolean

    suspend fun addUserPermissionToDevice(
        userId: Int,
        deviceId: Int,
        readOnly: Boolean,
    ): Boolean

    suspend fun addUserPermissionToGroup(
        userId: Int,
        deviceId: Int,
        groupId: Int,
        readOnly: Boolean,
    ): Boolean

    suspend fun addUserPermissionToField(
        userId: Int,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        readOnly: Boolean,
    ): Boolean

    suspend fun addUserPermissionToComplexGroup(
        userId: Int,
        deviceId: Int,
        complexGroupId: Int,
        readOnly: Boolean,
    ): Boolean

    suspend fun deleteUserPermissionToDevice(
        userId: Int,
        deviceId: Int,
    ): Boolean

    suspend fun deleteUserPermissionToGroup(
        userId: Int,
        deviceId: Int,
        groupId: Int,
    ): Boolean

    suspend fun deleteUserPermissionToField(
        userId: Int,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
    ): Boolean

    suspend fun deleteUserPermissionToComplexGroup(
        userId: Int,
        deviceId: Int,
        complexGroupId: Int,
    ): Boolean

    val allPermissionsForDeviceResponse: StateFlow<UserPermissionsForDeviceResponse?>
    suspend fun getUserPermissionsForDevice(deviceId: Int)
    fun clearAllPermissionsResponse()

    suspend fun addTrigger(
        triggerName: String,
        sourceType: ETriggerSourceType,
        sourceData: TriggerSourceData,
        fieldType: String?,
        settings: TriggerSettings?,

        responseType: ETriggerResponseType,
        responseSettings: TriggerResponse,
    ): Boolean

    suspend fun deleteTrigger(triggerId: Int): Boolean

    suspend fun seeAllTriggers()

    val allTriggersForUserResponse: StateFlow<GetAllUserTriggersResponse?>

    fun clearAllTriggersResponse()
}
