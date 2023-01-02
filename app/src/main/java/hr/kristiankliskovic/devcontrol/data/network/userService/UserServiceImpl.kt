package hr.kristiankliskovic.devcontrol.data.network.userService

import hr.kristiankliskovic.devcontrol.data.network.HTTPSERVER
import hr.kristiankliskovic.devcontrol.data.network.model.*
import hr.kristiankliskovic.devcontrol.data.network.userService.UserService
import hr.kristiankliskovic.devcontrol.data.network.wsService.WebsocketServiceImpl
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.map

const val userAuth_routerPath = "/api/userAuth"

const val loginByCreds_routerPath = "/login/creds"
const val loginByToken_routerPath = "/login/token"
const val logout_routerPath = "/logout"
const val register_routerPath = "/register"
const val deleteUser_routerPath = "/delete"
const val changePassword_routerPath = "/changePassword"

class UserServiceImpl(
    private val client: HttpClient,
) : UserService {

    override suspend fun loginUserByCreds(username: String, password: String): LoginResponse? {
        val httpResponse =
            client.post("${HTTPSERVER.httpServer}$userAuth_routerPath$loginByCreds_routerPath") {
                contentType(ContentType.Application.Json)
                setBody(LoginByCredsRequest(
                    username = username,
                    password = password,
                ))
            }
        return if (httpResponse.status.value in 200..299) {
            httpResponse.body<LoginResponse>()
        } else {
            null
        }
    }

    override suspend fun loginUserByToken(token: String): LoginResponse? {
        val httpResponse =
            client.post("${HTTPSERVER.httpServer}$userAuth_routerPath$loginByToken_routerPath") {
                contentType(ContentType.Application.Json)
                setBody(LoginByTokenRequest(
                    authToken = token,
                ))
            }
        return if (httpResponse.status.value in 200..299) {
            httpResponse.body<LoginResponse>()
        } else {
            null
        }
    }

    override suspend fun logoutUser(token: String, logoutOtherSessions: Boolean): Boolean {
        val httpResponse =
            client.post("${HTTPSERVER.httpServer}$userAuth_routerPath$logout_routerPath") {
                contentType(ContentType.Application.Json)
                setBody(LogoutRequest(
                    authToken = token,
                    logoutOtherSessions = logoutOtherSessions,
                ))
            }
        return httpResponse.status.value in 200..299
    }

    override suspend fun deleteUser(token: String): Boolean {
        val httpResponse =
            client.post("${HTTPSERVER.httpServer}$userAuth_routerPath$deleteUser_routerPath") {
                contentType(ContentType.Application.Json)
                setBody(DeleteUserRequest(
                    authToken = token,
                ))
            }
        return httpResponse.status.value in 200..299
    }

    override suspend fun changePassword(
        userId: Int,
        oldPassword: String,
        newPassword: String,
        logoutOtherSessions: Boolean,
        dontLogoutToken: String,
    ): Boolean {
        val httpResponse =
            client.post("${HTTPSERVER.httpServer}$userAuth_routerPath$changePassword_routerPath") {
                contentType(ContentType.Application.Json)
                setBody(ChangePasswordRequest(
                    userId = userId,
                    oldPassword = oldPassword,
                    newPassword = newPassword,
                    logoutOtherSessions = logoutOtherSessions,
                    dontLogoutToken = dontLogoutToken
                ))
            }
        return httpResponse.status.value in 200..299
    }

    override suspend fun registerUser(username: String, password: String): LoginResponse? {
        val httpResponse =
            client.post("${HTTPSERVER.httpServer}$userAuth_routerPath$register_routerPath") {
                contentType(ContentType.Application.Json)
                setBody(RegisterRequest(
                    username = username,
                    password = password,
                ))
            }
        return if (httpResponse.status.value in 200..299) {
            httpResponse.body<LoginResponse>()
        } else {
            null
        }
    }
}