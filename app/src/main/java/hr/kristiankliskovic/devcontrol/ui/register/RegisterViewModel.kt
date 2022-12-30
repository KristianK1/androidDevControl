package hr.kristiankliskovic.devcontrol.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {
    fun register(username: String, password: String) {
        viewModelScope.launch {
            Log.i("regg", "qqwe")
            userRepository.registerUser(
                username = username,
                password = password
            )
        }
    }
}
