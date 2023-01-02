package hr.kristiankliskovic.devcontrol.data.repository.user

import android.util.Log
import hr.kristiankliskovic.devcontrol.data.memory_db.InMemoryDb
import hr.kristiankliskovic.devcontrol.data.network.userService.UserService
import hr.kristiankliskovic.devcontrol.data.network.model.LoginResponse
import hr.kristiankliskovic.devcontrol.data.network.model.WssLogoutReasonResponse
import hr.kristiankliskovic.devcontrol.data.network.wsService.WebSocketService
import hr.kristiankliskovic.devcontrol.data.repository.authToken.AuthTokenRepository
import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class UserRepositoryImpl(
    private val userService: UserService,
    private val authTokenRepository: AuthTokenRepository,
    private val websocketService: WebSocketService,
    private val ioDispatcher: CoroutineDispatcher,
) : UserRepository {

    override val loggedInUser: Flow<LoggedInUser?> =
        InMemoryDb.loggedInUser.mapLatest { it }.flowOn(ioDispatcher)

    override val connectedToWSS: Flow<Boolean> = websocketService.connectedToWSS

    override val userMessages: Flow<WssLogoutReasonResponse?> = websocketService.userMessages

    override suspend fun stayConnectedToWs() {
        combine(loggedInUser, connectedToWSS) { user: LoggedInUser?, connectedToWS: Boolean ->
            Log.i("websocket", "collect_token_${user != null}")
            Log.i("websocket", "collect_connected_${connectedToWS}")
            if (user != null && !connectedToWS) user.token else null
        }.mapLatest {
            it
        }.collect { token ->
            Log.i("websocket", "collect_${token != null}")
            if (token != null) {
                websocketService.connect(token)
            }
        }
    }

    private fun addUser(loginResponse: LoginResponse) {
        InMemoryDb.loginUser(
            LoggedInUser(
                userId = loginResponse.id,
                username = loginResponse.username,
                token = loginResponse.authToken
            )
        )
        authTokenRepository.saveAuthToken(loginResponse.authToken)
    }

    private fun removeUser() {
        InMemoryDb.logoutUser()
        authTokenRepository.deleteAuthToken()
    }

    override suspend fun loginByCreds(username: String, password: String) {
        Log.i("login", "UserRepositoryImpl_loginByCreds_start")
        val loginResponse = userService.loginUserByCreds(username, password)
        if (loginResponse != null) {
            addUser(loginResponse)
        }
    }

    override suspend fun loginByToken() {
        Log.i("login", "UserRepositoryImpl_loginByToken_start")
        val token = authTokenRepository.getAuthToken()
        if (token != null) {
            val loginResponse = userService.loginUserByToken(token)
            if (loginResponse != null) {
                addUser(loginResponse)
            }
        }
        Log.i("login", "UserRepositoryImpl_loginByToken_end")
    }

    override suspend fun logoutUser(logoutAllSessions: Boolean) {
        val token = authTokenRepository.getAuthToken()
        if (token != null) {
            val logoutWorked = userService.logoutUser(token, logoutAllSessions)
            if (logoutWorked) {
                removeUser()
            }
        }
    }

    override suspend fun registerUser(username: String, password: String) {
        val loginResponse = userService.registerUser(username, password)
        if (loginResponse != null) {
            addUser(loginResponse)
        }
    }

    override suspend fun changePassword(
        oldPassword: String,
        newPassword: String,
        logoutOtherSessions: Boolean,
    ) {
        val user = loggedInUser.last()
        if (user != null) {
            val chPassSuccess = userService.changePassword(
                userId = user.userId,
                oldPassword = oldPassword,
                newPassword = newPassword,
                logoutOtherSessions = logoutOtherSessions,
                dontLogoutToken = user.token,
            )
        }
    }

    override suspend fun deleteUser() {
        val user = loggedInUser.last()
        if (user != null) {
            val deleted = userService.deleteUser(user.token)
            if (deleted) {
                removeUser()
            }
        }
    }
}
