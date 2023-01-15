package hr.kristiankliskovic.devcontrol.data.memory_db

import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import kotlinx.coroutines.flow.*

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
