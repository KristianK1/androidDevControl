package hr.kristiankliskovic.devcontrol.data.network.userService

import hr.kristiankliskovic.devcontrol.data.network.HTTPSERVER
import hr.kristiankliskovic.devcontrol.data.network.model.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

private const val userAuth_routerPath = "/api/userAuth"

private const val loginByCreds_routerPath = "/login/creds"
private const val loginByToken_routerPath = "/login/token"
private const val logout_routerPath = "/logout"
private const val register_routerPath = "/register"
private const val deleteUser_routerPath = "/delete"
private const val changePassword_routerPath = "/changePassword"
private const val getAllUsers_routerPath = "/getUsers"

class UserServiceImpl(
    private val client: HttpClient,
) : UserService {

    override suspend fun loginUserByCreds(username: String, password: String): LoginResponse? {
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$userAuth_routerPath$loginByCreds_routerPath",
            body = LoginByCredsRequest(
                username = username,
                password = password,
            )
        )
        return if (httpResponse != null && httpResponse.status.value in 200..299) {
            httpResponse.body<LoginResponse>()
        } else {
            null
        }
    }

    override suspend fun loginUserByToken(token: String): LoginResponse? {
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$userAuth_routerPath$loginByToken_routerPath",
            body = LoginByTokenRequest(
                authToken = token,
            ),
        )
        return if (httpResponse != null && httpResponse.status.value in 200..299) {
            httpResponse.body<LoginResponse>()
        } else {
            null
        }
    }

    override suspend fun logoutUser(token: String, logoutOtherSessions: Boolean): Boolean {
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$userAuth_routerPath$logout_routerPath",
            body = LogoutRequest(
                authToken = token,
                logoutOtherSessions = logoutOtherSessions,
            )
        )
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun deleteUser(token: String): Boolean {
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$userAuth_routerPath$deleteUser_routerPath",
            body = DeleteUserRequest(
                authToken = token,
            )
        )
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun getOtherUsers(token: String): GetAllUsersResponse? {
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$userAuth_routerPath$getAllUsers_routerPath",
            body = GetAllUsersRequest(
                authToken = token,
            )
        )
        return if (httpResponse != null && httpResponse.status.value in 200..299) {
            httpResponse.body<GetAllUsersResponse>()
        } else {
            null
        }
    }

    override suspend fun changePassword(
        userId: Int,
        oldPassword: String,
        newPassword: String,
        logoutOtherSessions: Boolean,
        dontLogoutToken: String,
    ): Boolean {
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$userAuth_routerPath$changePassword_routerPath",
            body = ChangePasswordRequest(
                userId = userId,
                oldPassword = oldPassword,
                newPassword = newPassword,
                logoutOtherSessions = logoutOtherSessions,
                dontLogoutToken = dontLogoutToken
            )
        )
        return (httpResponse != null && httpResponse.status.value in 200..299)
    }

    override suspend fun registerUser(username: String, password: String): LoginResponse? {
        val httpResponse = httpPostRequest(
            url = "${HTTPSERVER.httpServer}$userAuth_routerPath$register_routerPath",
            body = RegisterRequest(
                username = username,
                password = password,
            )
        )
        return if (httpResponse != null && httpResponse.status.value in 200..299) {
            httpResponse.body<LoginResponse>()
        } else {
            null
        }
    }

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
