package hr.kristiankliskovic.devcontrol.data.repository.user

import android.util.Log
import hr.kristiankliskovic.devcontrol.data.memory_db.InMemoryDb
import hr.kristiankliskovic.devcontrol.data.network.userService.UserService
import hr.kristiankliskovic.devcontrol.data.network.model.LoginResponse
import hr.kristiankliskovic.devcontrol.data.network.wsService.WebsocketServiceImpl
import hr.kristiankliskovic.devcontrol.data.repository.authToken.AuthTokenRepository
import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlin.math.log

class UserRepositoryImpl(
    private val userService: UserService,
    private val authTokenRepository: AuthTokenRepository,
    private val websocketService: WebsocketServiceImpl,
    private val ioDispatcher: CoroutineDispatcher,
) : UserRepository {
    override val loggedInUser: Flow<LoggedInUser?> = InMemoryDb.loggedInUser.mapLatest {
        it
    }.flowOn(ioDispatcher)

    val connectedToWs = websocketService.connectedToWSS.mapLatest { connected ->
        Log.i("websocket", "userRepo_mapLaters_${connected}")
        if(!connected){
            Log.i("websocket", "userRepo_mapLaters_222_${connected}")
            if(loggedInUser.last() != null){
                Log.i("websocket", "userRepo_mapLater_dalje")
                websocketService.connect()
            }
        }
        connected
    }.flowOn(ioDispatcher)

    val userMessages = websocketService.userMessages.mapLatest {
        Log.i("websocket", "repository:_${it}")
    }.flowOn(ioDispatcher)

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

    override suspend fun connectToWs(){
        websocketService.connect()
    }
}
