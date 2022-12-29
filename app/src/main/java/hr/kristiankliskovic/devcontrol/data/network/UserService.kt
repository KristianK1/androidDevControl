package hr.kristiankliskovic.devcontrol.data.network

import hr.kristiankliskovic.devcontrol.data.network.model.LoginResponse

interface UserService {
    suspend fun loginUserByCreds(username: String, password: String): LoginResponse?
    suspend fun loginUserByToken(token: String): LoginResponse?
    suspend fun registerUser(username: String, password: String): LoginResponse?

    suspend fun logoutUser(token: String, logoutOtherSessions: Boolean): Boolean
    suspend fun deleteUser(token: String): Boolean

    suspend fun changePassword(
        userId: Int,
        oldPassword: String,
        newPassword: String,
        logoutOtherSessions: Boolean,
        dontLogoutToken: String,
    ): Boolean

}
