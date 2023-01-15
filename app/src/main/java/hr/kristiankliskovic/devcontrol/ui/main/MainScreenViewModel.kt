package hr.kristiankliskovic.devcontrol.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.network.model.WssLogoutReason
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainScreenViewModel(
    val userRepository: UserRepository,
    val deviceRepository: DeviceRepository,
) : ViewModel() {

    var loggedInLocal: Boolean = false

    val loggedInUser: StateFlow<Boolean?> = userRepository.loggedInUser.mapLatest {
        it != null
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    val userMessages: StateFlow<WssLogoutReason?> = userRepository.userMessages.mapLatest {
        Log.i("websocket_usermessages", "listen")
        it
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun onPause(){
        Log.i("sviki", "onPause2")

    }

    fun onResume(){
        Log.i("sviki", "onResume2")

    }

    fun startWS() {
        viewModelScope.launch {
            Log.i("websocket", "started stay conn")
            userRepository.connectToWS()
        }
    }

    fun stopWS() {
        viewModelScope.launch {
            userRepository.disconnectWS()
        }
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logoutUser(false)
        }
    }
}
