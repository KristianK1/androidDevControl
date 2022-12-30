package hr.kristiankliskovic.devcontrol.ui.userProfileSettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import kotlinx.coroutines.launch

class UserProfileSettingsViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {
    fun logout() {
        viewModelScope.launch {
            userRepository.logoutUser(
                logoutAllSessions = false
            )
        }
    }

    fun logoutAllSessions() {
        viewModelScope.launch {
            userRepository.logoutUser(
                logoutAllSessions = true
            )
        }
    }

    fun deleteUser(){
        viewModelScope.launch {
            userRepository.deleteUser()
        }
    }
}
