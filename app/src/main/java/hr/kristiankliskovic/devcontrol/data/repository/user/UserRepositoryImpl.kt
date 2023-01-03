package hr.kristiankliskovic.devcontrol.data.repository.user

import android.util.Log
import hr.kristiankliskovic.devcontrol.data.memory_db.InMemoryDb
import hr.kristiankliskovic.devcontrol.data.network.userService.UserService
import hr.kristiankliskovic.devcontrol.data.network.model.LoginResponse
import hr.kristiankliskovic.devcontrol.data.network.model.WssLogoutReason
import hr.kristiankliskovic.devcontrol.data.network.wsService.WebSocketService
import hr.kristiankliskovic.devcontrol.data.repository.authToken.AuthTokenRepository
import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

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
        Log.i("websocket", "mainScreen_VM_logout1")
        if (it != null) {
            Log.i("websocket", "mainScreen_VM_logout2")
            removeUser()
        }
        it
    }


    override suspend fun connectToWS() {
        Log.i("websocket", "connectToWs - called by MainScreen")
        val token = authTokenRepository.getAuthToken()
        if(token != null){
            Log.i("websocket", "connectToWs - called by MainScreen_2")

            websocketService.connect(token)
        }else{
            Log.i("websocket", "but no token")
        }
    }

    override suspend fun disconnectWS() {
        Log.i("websocket", "connectToWs - called by MainScreen")
        websocketService.disconnect()
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
        Log.i("websocket", "repo-removeUser")
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
        Log.i("websocket_logout", "logoutUser")
        val token = authTokenRepository.getAuthToken() //TODO change
        Log.i("websocket_logout", "token_${token != null}")
        if (token != null) {
            val logoutWorked = userService.logoutUser(token, logoutAllSessions)
            Log.i("websocket_logout", "worked_${logoutWorked}")
            removeUser()
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
