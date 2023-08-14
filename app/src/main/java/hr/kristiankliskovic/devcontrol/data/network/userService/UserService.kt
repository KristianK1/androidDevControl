package hr.kristiankliskovic.devcontrol.data.network.userService

import hr.kristiankliskovic.devcontrol.data.network.model.GetAllUsersResponse
import hr.kristiankliskovic.devcontrol.data.network.model.LoginResponse

interface UserService {
    suspend fun loginUserByCreds(
        username: String,
        password: String,
        firebaseToken: String?,
    ): LoginResponse?

    suspend fun loginUserByToken(token: String, firebaseToken: String?): LoginResponse?
    suspend fun registerUser(
        username: String,
        password: String,
        email: String,
        firebaseToken: String?,
    ): LoginResponse?

    suspend fun logoutUser(token: String, logoutOtherSessions: Boolean): Boolean
    suspend fun deleteUser(token: String): Boolean
    suspend fun getOtherUsers(token: String): GetAllUsersResponse?

    suspend fun changePassword(
        userId: Int,
        oldPassword: String,
        newPassword: String,
        logoutOtherSessions: Boolean,
        dontLogoutToken: String,
    ): Boolean

    suspend fun addEmail(token: String, email: String): Boolean
}
