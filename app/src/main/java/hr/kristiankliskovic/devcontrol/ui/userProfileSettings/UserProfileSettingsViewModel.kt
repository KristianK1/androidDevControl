package hr.kristiankliskovic.devcontrol.ui.userProfileSettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import hr.kristiankliskovic.devcontrol.ui.userProfileSettings.mapper.UserProfileSettingsMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserProfileSettingsViewModel(
    private val userRepository: UserRepository,
    val mapper: UserProfileSettingsMapper,
) : ViewModel() {

    val userProfileSettingsViewState: StateFlow<UserProfileSettingsViewState> =
        userRepository.loggedInUser.map { it ->
            if(it != null){
                mapper.toUserProfileViewState(it)
            }
            else{
                UserProfileSettingsViewState.getEmptyObject()
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, UserProfileSettingsViewState.getEmptyObject())

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

    fun deleteUser() {
        viewModelScope.launch {
            userRepository.deleteUser()
        }
    }
}
