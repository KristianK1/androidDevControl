package hr.kristiankliskovic.devcontrol.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainScreenViewModel(
    val userRepository: UserRepository,
) : ViewModel() {
    val loggedInUser: StateFlow<Boolean?> = userRepository.loggedInUser.mapLatest {
        Log.i("WEBSOCKET","qqqqqqqqqqqqqSSSSSSSSSSSSSSSSSSSSSS")
        it != null
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun startWS(){
        viewModelScope.launch {
            Log.i("websocket", "started stay conn")
            userRepository.connectToWS()
        }
    }
}
