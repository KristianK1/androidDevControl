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
        InMemoryDb.loggedInUser.mapLatest { it }.flowOn(ioDispatcher)

    override val connectedToWSS: Flow<Boolean> = websocketService.connectedToWSS

    override val userMessages: Flow<WssLogoutReason?> = websocketService.userMessages.mapLatest {
        Log.i("websocket", "mainScreen_VM_logout1")
        if (it != null) {
            Log.i("websocket", "mainScreen_VM_logout2")
            removeUser()
        }
        it
    }.flowOn(ioDispatcher)

//    val x: Flow<String> =
//        combine(loggedInUser, userMessages) { user: LoggedInUser?, userMessage: WssLogoutReason? ->
//            "jdf"
//        }.mapLatest {
//            it
//        }

    override suspend fun connectToWS() {
            val x: Flow<String> =
        combine(loggedInUser, userMessages) { user: LoggedInUser?, userMessage: WssLogoutReason? ->
            "jdf"
        }.mapLatest {
            it
        }
        userMessages.collect { userMessage ->
            Log.i("websocket", "collectEEEEEEEEEEEEEEEEEEEEEEEEEEE_${user != null}")
            if (userMessage != null) {
                websocketService.connect(user.token)
            }
            else{
                websocketService.disconnect()
            }
        }
    }

    private fun addUser(loginResponse: LoginResponse) {
        Log.i("websocket", "addUser_________________________QW")
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
        Log.i("websocket", "remove")
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

    override suspend fun disWS() {
        websocketService.disconnect()
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
