package hr.kristiankliskovic.devcontrol.data.repository.user

import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {
    val loggedInUser: Flow<LoggedInUser?>
    val userMessages: Flow<String>

    suspend fun loginByCreds(username: String, password: String)
    suspend fun loginByToken()
    suspend fun logoutUser(logoutAllSessions: Boolean)
    suspend fun registerUser(username: String, password: String)
    suspend fun changePassword(
        oldPassword: String,
        newPassword: String,
        logoutOtherSessions: Boolean,
    )
    suspend fun deleteUser()
    suspend fun connectToWs()
}
