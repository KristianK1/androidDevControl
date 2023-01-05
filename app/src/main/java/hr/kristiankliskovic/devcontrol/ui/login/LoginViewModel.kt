package hr.kristiankliskovic.devcontrol.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.network.deviceService.mapper.mapDeviceData
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    init {


//
//        Log.i("login", "LoginViewModel_init")
//        viewModelScope.launch {
//            Log.i("login", "LoginViewModel_viewModeLScope_init")
//            userRepository.loginByToken()
//            Log.i("login", "LoginViewModel_viewModeLScope_init_end")
//        }
//        Log.i("login", "LoginViewModel_viewModeLScope_init_end2")
    }

    fun login(username: String, password: String) {
        mapDeviceData("1")

//        Log.i("login", "LoginViewModel_loginByCreds_${username}_${password}")
//        viewModelScope.launch(Dispatchers.IO) {
//            Log.i("login", "LoginViewModel_viewModelScope_loginByCreds_${username}_${password}")
//            userRepository.loginByCreds(
//                username = username,
//                password = password,
//            )
//            Log.i("login", "LoginViewModel_viewModelScope_end_loginByCreds_${username}_${password}")
//        }
//        Log.i("login", "LoginViewModel_viewModelScope_end2_loginByCreds_${username}_${password}")
    }
}
