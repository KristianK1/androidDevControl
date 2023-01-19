package hr.kristiankliskovic.devcontrol.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    init {
        viewModelScope.launch {
            userRepository.loginByToken()
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            userRepository.loginByCreds(
                username = username,
                password = password,
            )
        }
    }
}
