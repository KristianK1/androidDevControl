package hr.kristiankliskovic.devcontrol.ui.userProfileSettingsChangePassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ChangePasswordViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val navBackInternal: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val navBack =
        navBackInternal.asStateFlow().stateIn(viewModelScope, SharingStarted.Lazily, false)

    fun changePassword(
        oldPassword: String,
        newPassword: String,
        logoutOtherSessions: Boolean,
    ) {
        viewModelScope.launch {
            val navBack =
                userRepository.changePassword(oldPassword, newPassword, logoutOtherSessions)
        }
    }
}
