package hr.kristiankliskovic.devcontrol.data.network.deviceService

import android.util.Log
import hr.kristiankliskovic.devcontrol.data.network.HTTPSERVER
import hr.kristiankliskovic.devcontrol.data.network.model.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

private const val device_routerPath = "/api/device"
private const val userPermission_routerPath = "/api/userRights"

private const val changeFieldValue_routerPath = "/changeField/user"
private const val changeFieldInComplexGroupValue_routerPath = "/fieldInComplexGroupState/user"
private const val changeComplexGroupStateValue_routerPath = "/changeComplexGroupState/user"
private const val changeDeviceAdmin_routerPath = "/changeAdmin"
private const val addDevice_routerPath = "/addDevice"
private const val deleteDevice_routerPath = "/deleteDevice"

private const val addDevicePermission_routerPath = "/addDeviceRight"
private const val addGroupPermission_routerPath = "/addGroupRight"
private const val addFieldPermission_routerPath = "/addFieldRight"
private const val addComplexGroupPermission_routerPath = "/addComplexGroupRight"
private const val deleteDevicePermission_routerPath = "/deleteDeviceRight"
private const val deleteGroupPermission_routerPath = "/deleteGroupRight"
private const val deleteFieldPermission_routerPath = "/deleteFieldRight"
private const val deleteComplexGroupPermission_routerPath = "/deleteComplexGroupRight"
private const val getUserPermissions_routerPath = "/getUserPermissions"


class DeviceServiceImpl(
    private val client: HttpClient,
) : DeviceService {

    private suspend fun httpPostRequest(url: String, body: Any): HttpResponse? {
        return try {
            client.post(url) {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
        } catch (e: Throwable) {
            null
        }
    }

    override suspend fun changeNumericField(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValue: Float,
    ): Boolean {
        val httpResponse =
            httpPostRequest(url = "${HTTPSERVER.httpServer}$device_routerPath$changeFieldValue_routerPath",
                body = ChangeNumericField(authToken, deviceId, groupId, fieldId, fieldValue))
        return (httpResponse != null && httpResponse.status.value in 200..299)

    }

    override suspend fun changeTextField(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValue: String,
    ): Boolean {
        val httpResponse =
            httpPostRequest(url = "${HTTPSERVER.httpServer}$device_routerPath$changeFieldValue_routerPath",
                body = ChangeTextField(authToken, deviceId, groupId, fieldId, fieldValue))
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun changeButtonField(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValue: Boolean,
    ): Boolean {
        val httpResponse =
            httpPostRequest(url = "${HTTPSERVER.httpServer}$device_routerPath$changeFieldValue_routerPath",
                body = ChangeButtonField(authToken, deviceId, groupId, fieldId, fieldValue))
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun changeMCField(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValue: Int,
    ): Boolean {
        val httpResponse =
            httpPostRequest(url = "${HTTPSERVER.httpServer}$device_routerPath$changeFieldValue_routerPath",
                body = ChangeMCField(authToken, deviceId, groupId, fieldId, fieldValue))
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun changeRGBField(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValueR: Int,
        fieldValueG: Int,
        fieldValueB: Int,
    ): Boolean {
        val httpResponse =
            httpPostRequest(url = "${HTTPSERVER.httpServer}$device_routerPath$changeFieldValue_routerPath",
                body = ChangeRGBField(authToken,
                    deviceId,
                    groupId,
                    fieldId,
                    RGBvalue(fieldValueR, fieldValueG, fieldValueB)))
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun changeNumericFieldInComplexGroup(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValue: Float,
    ): Boolean {
        val httpResponse =
            httpPostRequest(url = "${HTTPSERVER.httpServer}$device_routerPath$changeFieldInComplexGroupValue_routerPath",
                body = ChangeNumericFieldInCG(authToken,
                    deviceId,
                    groupId,
                    stateId,
                    fieldId,
                    fieldValue))
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun changeTextFieldInComplexGroup(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValue: String,
    ): Boolean {
        val httpResponse =
            httpPostRequest(url = "${HTTPSERVER.httpServer}$device_routerPath$changeFieldInComplexGroupValue_routerPath",
                body = ChangeTextFieldInCG(authToken,
                    deviceId,
                    groupId,
                    stateId,
                    fieldId,
                    fieldValue))
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun changeButtonFieldInComplexGroup(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValue: Boolean,
    ): Boolean {
        val httpResponse =
            httpPostRequest(url = "${HTTPSERVER.httpServer}$device_routerPath$changeFieldInComplexGroupValue_routerPath",
                body = ChangeButtonFieldInCG(authToken,
                    deviceId,
                    groupId,
                    stateId,
                    fieldId,
                    fieldValue))
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun changeMCFieldInComplexGroup(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValue: Int,
    ): Boolean {
        val httpResponse =
            httpPostRequest(url = "${HTTPSERVER.httpServer}$device_routerPath$changeFieldInComplexGroupValue_routerPath",
                body = ChangeMCFieldInCG(authToken,
                    deviceId,
                    groupId,
                    stateId,
                    fieldId,
                    fieldValue))
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun changeRGBFieldInComplexGroup(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValueR: Int,
        fieldValueG: Int,
        fieldValueB: Int,
    ): Boolean {
        val httpResponse =
            httpPostRequest(url = "${HTTPSERVER.httpServer}$device_routerPath$changeFieldInComplexGroupValue_routerPath",
                body = ChangeRGBFieldInCG(authToken,
                    deviceId,
                    groupId,
                    stateId,
                    fieldId,
                    RGBvalue(fieldValueR, fieldValueG, fieldValueB)))
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun changeComplexGroupState(
        authToken: String,
        deviceId: Int,
        groupId: Int,
        state: Int,
    ): Boolean {
        val httpResponse =
            httpPostRequest(url = "${HTTPSERVER.httpServer}$device_routerPath$changeComplexGroupStateValue_routerPath",
                body = ChangeComplexGroupState(authToken, deviceId, groupId, state))
        Log.i("CGstate", "sent_${httpResponse?.status?.value}")

        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun changeDeviceAdmin(authToken: String, deviceId: Int, userId: Int): Boolean {
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$device_routerPath$changeDeviceAdmin_routerPath",
            body = ChangeDeviceAdminRequest(
                authToken = authToken,
                deviceId = deviceId,
                userAdminId = userId,
            )
        )
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun addNewDevice(
        authToken: String,
        deviceName: String,
        deviceKey: String?,
    ): Boolean {
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$device_routerPath$addDevice_routerPath",
            body = AddNewDeviceRequest(
                authToken = authToken,
                deviceName = deviceName,
                deviceKey = deviceKey,
            )
        )
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun deleteDevice(authToken: String, deviceId: Int): Boolean {
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$device_routerPath$deleteDevice_routerPath",
            body = DeleteDeviceRequest(
                authToken = authToken,
                deviceId = deviceId,
            )
        )
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun addUserPermissionToDevice(
        authToken: String,
        userId: Int,
        deviceId: Int,
        readOnly: Boolean,
    ): Boolean {
        Log.i("perms", "four")
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$userPermission_routerPath$addDevicePermission_routerPath",
            body = AddUserPermissionToDevice(
                authToken = authToken,
                userId = userId,
                deviceId = deviceId,
                readOnly = readOnly,
            )
        )
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun addUserPermissionToGroup(
        authToken: String,
        userId: Int,
        deviceId: Int,
        groupId: Int,
        readOnly: Boolean,
    ): Boolean {
        Log.i("perms", "four")
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$userPermission_routerPath$addGroupPermission_routerPath",
            body = AddUserPermissionToGroup(
                authToken = authToken,
                userId = userId,
                deviceId = deviceId,
                groupId = groupId,
                readOnly = readOnly,
            )
        )
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun addUserPermissionToField(
        authToken: String,
        userId: Int,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        readOnly: Boolean,
    ): Boolean {
        Log.i("perms", "four")
        Log.i("perms", "${HTTPSERVER.httpServer}$userPermission_routerPath$addFieldPermission_routerPath")
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$userPermission_routerPath$addFieldPermission_routerPath",
            body = AddUserPermissionToField(
                authToken = authToken,
                userId = userId,
                deviceId = deviceId,
                groupId = groupId,
                fieldId = fieldId,
                readOnly = readOnly,
            )
        )
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun addUserPermissionToComplexGroup(
        authToken: String,
        userId: Int,
        deviceId: Int,
        complexGroupId: Int,
        readOnly: Boolean,
    ): Boolean {
        Log.i("perms", "four")
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$userPermission_routerPath$addComplexGroupPermission_routerPath",
            body = AddUserPermissionToComplexGroup(
                authToken = authToken,
                userId = userId,
                deviceId = deviceId,
                complexGroupId = complexGroupId,
                readOnly = readOnly,
            )
        )
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun deleteUserPermissionToDevice(
        authToken: String,
        userId: Int,
        deviceId: Int,
    ): Boolean {
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$userPermission_routerPath$deleteDevicePermission_routerPath",
            body = DeleteUserPermissionToDevice(
                authToken = authToken,
                userId = userId,
                deviceId = deviceId,
            )
        )
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun deleteUserPermissionToGroup(
        authToken: String,
        userId: Int,
        deviceId: Int,
        groupId: Int,
    ): Boolean {
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$userPermission_routerPath$deleteGroupPermission_routerPath",
            body = DeleteUserPermissionToGroup(
                authToken = authToken,
                userId = userId,
                deviceId = deviceId,
                groupId = groupId
            )
        )
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun deleteUserPermissionToField(
        authToken: String,
        userId: Int,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
    ): Boolean {
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$userPermission_routerPath$deleteFieldPermission_routerPath",
            body = DeleteUserPermissionToField(
                authToken = authToken,
                userId = userId,
                deviceId = deviceId,
                groupId = groupId,
                fieldId = fieldId,
            )
        )
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun deleteUserPermissionToComplexGroup(
        authToken: String,
        userId: Int,
        deviceId: Int,
        complexGroupId: Int,
    ): Boolean {
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$userPermission_routerPath$deleteComplexGroupPermission_routerPath",
            body = DeleteUserPermissionToComplexGroup(
                authToken = authToken,
                userId = userId,
                deviceId = deviceId,
                complexGroupId = complexGroupId,
            )
        )
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun getUserPermissionsForDevice(authToken: String, deviceId: Int): UserPermissionsForDeviceResponse? {
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$userPermission_routerPath$userPermission_routerPath",
            body = UserPermissionsForDeviceRequest(
                authToken = authToken,
                deviceId = deviceId,
            )
        )
        return if (httpResponse != null && httpResponse.status.value in 200..299) {
            httpResponse.body<UserPermissionsForDeviceResponse>()
        } else {
            null
        }
    }
}
