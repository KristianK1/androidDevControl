package hr.kristiankliskovic.devcontrol.data.memory_db

import android.util.Log
import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.State

class InMemoryDb(
    private val ioDispatcher: CoroutineDispatcher,
) : LoggedInUserDao {
    private var loggedInUserInternal = MutableStateFlow<LoggedInUser?>(null)

    override val loggedInUser: StateFlow<LoggedInUser?> = this.loggedInUserInternal

    override fun loginUser(user: LoggedInUser) {
        Log.i("fakeMainRouter", "ide1")
        Log.i("fakeMainRouter", "${loggedInUserInternal.value != null}")
        loggedInUserInternal.value = user
        Log.i("fakeMainRouter", "${loggedInUserInternal.value != null}")
    }

    override fun logoutUser() {
        Log.i("fakeMainRouter", "ide2")
        loggedInUserInternal.value = null
    }
}
