package hr.kristiankliskovic.devcontrol.data.memory_db

import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface LoggedInUserDao {
    val loggedInUser: StateFlow<LoggedInUser?>
    fun loginUser(user: LoggedInUser)
    fun logoutUser()
}
