package hr.kristiankliskovic.devcontrol.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainScreenViewModel(
    val userRepository: UserRepository,
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
    }.stateIn(viewModelScope, SharingStarted.Lazily, "")

    fun startWS(){
        viewModelScope.launch {
            Log.i("websocket", "started stay conn")
            userRepository.stayConnectedToWs()
        }
    }
}
