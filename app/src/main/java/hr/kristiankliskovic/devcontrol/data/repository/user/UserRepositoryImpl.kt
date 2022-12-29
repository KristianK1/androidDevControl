package hr.kristiankliskovic.devcontrol.data.repository.user

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import hr.kristiankliskovic.devcontrol.data.memory_db.LoggedInUserDao
import hr.kristiankliskovic.devcontrol.data.network.UserService
import hr.kristiankliskovic.devcontrol.data.network.model.LoginResponse
import hr.kristiankliskovic.devcontrol.data.repository.authToken.AuthTokenRepository
import hr.kristiankliskovic.devcontrol.mock.getLoggedInUserMock
import hr.kristiankliskovic.devcontrol.mock.getMockUsers
import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import hr.kristiankliskovic.devcontrol.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlin.math.log

class UserRepositoryImpl(
    private val userService: UserService,
    private val loggedInUserDao: LoggedInUserDao,
    private val authTokenRepository: AuthTokenRepository,
    private val bgDispatcher: CoroutineDispatcher,
) : UserRepository {
    override val loggedInUser: Flow<LoggedInUser?> = loggedInUserDao.loggedInUser

    private fun addUser(loginResponse: LoginResponse) {
        Log.i("loginX", loginResponse.authToken)
        loggedInUserDao.loginUser(
            LoggedInUser(
                userId = loginResponse.id,
                username = loginResponse.username,
                token = loginResponse.authToken
            )
        )
        authTokenRepository.saveAuthToken(loginResponse.authToken)
    }

    private fun removeUser() {
        loggedInUserDao.logoutUser()
        authTokenRepository.deleteAuthToken()
    }

    override fun loginByCreds(username: String, password: String) {
        runBlocking(bgDispatcher) {
            val loginResponse = userService.loginUserByCreds(username, password)
            if (loginResponse != null) {
                addUser(loginResponse)
            }
        }
    }

    override fun loginByToken(token: String) {
        runBlocking(bgDispatcher) {
            val loginResponse = userService.loginUserByToken(token)
            if (loginResponse != null) {
                addUser(loginResponse)
            }
        }
    }

    override fun logoutUser(token: String, logoutAllSessions: Boolean) {
        runBlocking(bgDispatcher) {
            val logoutWorked = userService.logoutUser(token, logoutAllSessions)
            if(logoutWorked){
                removeUser()
            }
        }
    }

    override fun registerUser(username: String, password: String) {
        runBlocking(bgDispatcher) {
            val loginResponse = userService.registerUser(username, password)
            if (loginResponse != null) {
                addUser(loginResponse)
            }
        }
    }

    override fun changePassword(
        oldPassword: String,
        newPassword: String,
        logoutOtherSessions: Boolean,
    ) {
        runBlocking(bgDispatcher) {
            val user = loggedInUser.lastOrNull()
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
    }
}
