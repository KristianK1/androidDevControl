package hr.kristiankliskovic.devcontrol.data.network.deviceService

import android.util.Log
import com.google.gson.Gson
import hr.kristiankliskovic.devcontrol.data.network.HTTPSERVER
import hr.kristiankliskovic.devcontrol.data.network.model.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

private const val device_routerPath = "/api/device"

private const val changeFieldValue_routerPath = "/changeField/user"
private const val changeFieldInComplexGroupValue_routerPath = "/fieldInComplexGroupState/user"
private const val changeComplexGroupStateValue_routerPath = "/changeComplexGroupState/user"
private const val changeDeviceAdmin_routerPath = "/changeAdmin"

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
}
