package hr.kristiankliskovic.devcontrol.ui.userProfileSettingsAddEmail.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AddEmailViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    fun addEmail(
        email: String,
    ) {
        viewModelScope.launch {
                userRepository.addEmail(email)
        }
    }
}
