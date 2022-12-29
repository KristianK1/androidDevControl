package hr.kristiankliskovic.devcontrol.ui.register

import androidx.lifecycle.ViewModel
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository

class RegisterViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {
    fun register(username: String, password: String) {
        userRepository.registerUser(
            username = username,
            password = password
        )
    }
}
