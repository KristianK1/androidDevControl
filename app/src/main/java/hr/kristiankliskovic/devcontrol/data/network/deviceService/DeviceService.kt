package hr.kristiankliskovic.devcontrol.data.network.deviceService

import hr.kristiankliskovic.devcontrol.data.network.model.RGBvalue

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
}
