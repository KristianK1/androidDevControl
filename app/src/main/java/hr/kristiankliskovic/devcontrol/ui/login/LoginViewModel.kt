package hr.kristiankliskovic.devcontrol.ui.login

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.DevControlApp
import hr.kristiankliskovic.devcontrol.data.network.HTTPSERVER
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.AccessController.getContext

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

    fun forgotPassword(){
        val urlIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("${HTTPSERVER.httpServer}/email/forgotPassword")
        )
        urlIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        viewModelScope.launch {
            startActivity(DevControlApp.application, urlIntent, null)
        }
    }
}
