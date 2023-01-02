package hr.kristiankliskovic.devcontrol.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import kotlinx.coroutines.flow.*

class MainScreenViewModel(
    userRepository: UserRepository,
) : ViewModel() {

    val loggedInUser: StateFlow<Boolean?> = userRepository.loggedInUser.mapLatest {
        it != null
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    val userMessages = userRepository.userMessages.mapLatest {
        Log.i("websocket", "mainVM_${it}")
        if (it != null) {
            Log.i("websocket", "mainScreen_VM_logout")
            userRepository.logoutUser(false)
        }
        it
    }.stateIn(viewModelScope, SharingStarted.Eagerly, "")
}
