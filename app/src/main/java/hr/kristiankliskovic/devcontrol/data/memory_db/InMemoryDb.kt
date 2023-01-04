package hr.kristiankliskovic.devcontrol.data.memory_db

import android.util.Log
import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.State

object InMemoryDb {
    private var loggedInUserInternal = MutableStateFlow<LoggedInUser?>(null)

    val loggedInUser: StateFlow<LoggedInUser?> = this.loggedInUserInternal.asStateFlow()

    fun loginUser(user: LoggedInUser) {
        loggedInUserInternal.value = user
    }

    fun logoutUser() {
        loggedInUserInternal.value = null
    }
}
