package hr.kristiankliskovic.devcontrol.ui.main

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
        it
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    val connectedToWS: StateFlow<Boolean> = userRepository.connectedToWSS.mapLatest {
        it
    }.stateIn(viewModelScope, SharingStarted.Lazily, false)

    fun startWS() {
        viewModelScope.launch {
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
