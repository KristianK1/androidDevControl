package hr.kristiankliskovic.devcontrol.data.repository.user

import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val loggedInUser: Flow<LoggedInUser?>

    suspend fun loginByCreds(username: String, password: String)
    suspend fun loginByToken()
    fun logoutUser(token: String, logoutAllSessions: Boolean)
    fun registerUser(username: String, password: String)
    fun changePassword(
        oldPassword: String,
        newPassword: String,
        logoutOtherSessions: Boolean,
    )
}
