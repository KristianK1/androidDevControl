package hr.kristiankliskovic.devcontrol.data.repository.user

import android.util.Log
import hr.kristiankliskovic.devcontrol.data.memory_db.InMemoryDb
import hr.kristiankliskovic.devcontrol.data.network.userService.UserService
import hr.kristiankliskovic.devcontrol.data.network.model.LoginResponse
import hr.kristiankliskovic.devcontrol.data.network.model.WssLogoutReason
import hr.kristiankliskovic.devcontrol.data.network.wsService.WebSocketService
import hr.kristiankliskovic.devcontrol.data.repository.authToken.AuthTokenRepository
import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import hr.kristiankliskovic.devcontrol.model.User
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.descriptors.PrimitiveKind

class UserRepositoryImpl(
    private val userService: UserService,
    private val authTokenRepository: AuthTokenRepository,
    private val websocketService: WebSocketService,
    ioDispatcher: CoroutineDispatcher,
) : UserRepository {

    override val loggedInUser: Flow<LoggedInUser?> =
        InMemoryDb.loggedInUser.mapLatest { it }

    override val connectedToWSS: Flow<Boolean> = websocketService.connectedToWSS

    override val userMessages: Flow<WssLogoutReason?> = websocketService.userMessages.mapLatest {
        if (it != null) {
            removeUser()
        }
        it
    }

    override fun getOtherUsers(): Flow<List<User>> = flow {
        val response = userService.getOtherUsers(authTokenRepository.getAuthToken()!!)
        if (response == null) {
            emit(listOf())
        } else
            emit(response.users.map {
                User(
                    username = it.username,
                    id = it.id,
                )
            })
    }

    override suspend fun connectToWS() {
        val token = authTokenRepository.getAuthToken()
        if (token != null) {
            websocketService.connect(token)
        }
    }

    override suspend fun disconnectWS() {
        websocketService.disconnect()
        websocketService.resetUserMessages()
        websocketService.resetDeviceMessages()
    }

    private fun addUser(loginResponse: LoginResponse) {
        authTokenRepository.saveAuthToken(loginResponse.authToken)
        InMemoryDb.loginUser(
            LoggedInUser(
                userId = loginResponse.id,
                username = loginResponse.username,
                token = loginResponse.authToken
            )
        )
    }

    private suspend fun removeUser() {
        InMemoryDb.logoutUser()
        authTokenRepository.deleteAuthToken()
    }

    override suspend fun loginByCreds(username: String, password: String) {
        val loginResponse = userService.loginUserByCreds(username, password)
        if (loginResponse != null) {
            addUser(loginResponse)
        }
    }

    override suspend fun loginByToken() {
        val token = authTokenRepository.getAuthToken()
        if (token != null) {
            val loginResponse = userService.loginUserByToken(token)
            if (loginResponse != null) {
                addUser(loginResponse)
            }
        }
    }

    override suspend fun logoutUser(logoutAllSessions: Boolean) {
        val token = authTokenRepository.getAuthToken()
        if (token != null) {
            val logoutWorked = userService.logoutUser(token, logoutAllSessions)
            removeUser()
        }
    }

    override suspend fun registerUser(username: String, password: String, email: String) {
        val loginResponse = userService.registerUser(username, password, email)
        if (loginResponse != null) {
            addUser(loginResponse)
        }
    }

    override suspend fun changePassword(
        oldPassword: String,
        newPassword: String,
        logoutOtherSessions: Boolean,
    ): Boolean {
        val user = InMemoryDb.loggedInUser.value
        return if (user != null) {
            userService.changePassword(
                userId = user.userId,
                oldPassword = oldPassword,
                newPassword = newPassword,
                logoutOtherSessions = logoutOtherSessions,
                dontLogoutToken = user.token,
            )
        } else {
            false
        }
    }

    override suspend fun deleteUser() {
        val token = authTokenRepository.getAuthToken()
        if (token != null) {
            val deleted = userService.deleteUser(token)
            if (deleted) {
                logoutUser(
                    logoutAllSessions = true
                )
            }
        }
    }
}
