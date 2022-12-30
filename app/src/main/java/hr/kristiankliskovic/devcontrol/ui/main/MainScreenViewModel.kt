package hr.kristiankliskovic.devcontrol.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

class MainScreenViewModel(
    userRepository: UserRepository,
) : ViewModel() {

    val loggedInUser: StateFlow<Boolean?> = userRepository.loggedInUser.mapLatest {
        it != null
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

}
