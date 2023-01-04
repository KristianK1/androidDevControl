package hr.kristiankliskovic.devcontrol.data.network.deviceService

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

private const val device_routerPath = "/api/device"

private const val changeFieldValue_routerPath = "/changeField/user"
private const val changeFieldInComplexGroupValue_routerPath = "/fieldInComplexGroupState/user"
private const val changeComplexGroupStateValue_routerPath = "/changeComplexGroupState/user"


class DeviceServiceImpl(
    private val client: HttpClient,
) : DeviceService {


    //5 F change
    //5 CGF change
    //1 CGS change

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
}
