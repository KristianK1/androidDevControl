package hr.kristiankliskovic.devcontrol.data.repository.user

import hr.kristiankliskovic.devcontrol.data.network.model.WssLogoutReason
import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import hr.kristiankliskovic.devcontrol.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val loggedInUser: Flow<LoggedInUser?>
    val connectedToWSS: Flow<Boolean>
    val userMessages: Flow<WssLogoutReason?>
    fun getOtherUsers(): Flow<List<User>>
    suspend fun loginByCreds(username: String, password: String)
    suspend fun loginByToken()
    suspend fun logoutUser(logoutAllSessions: Boolean)
    suspend fun registerUser(username: String, password: String)
    suspend fun changePassword(
        oldPassword: String,
        newPassword: String,
        logoutOtherSessions: Boolean,
    ): Boolean

    suspend fun deleteUser()
    suspend fun connectToWS()
    suspend fun disconnectWS()
}
