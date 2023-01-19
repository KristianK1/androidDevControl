package hr.kristiankliskovic.devcontrol.ui.adminPanelHome

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import hr.kristiankliskovic.devcontrol.ui.adminPanelHome.mapper.AdminPanelHomeMapper
import kotlinx.coroutines.flow.*
import java.util.concurrent.CopyOnWriteArrayList

class AdminPanelHomeViewModel(
    adminPanelHomeMapper: AdminPanelHomeMapper,
    deviceRepository: DeviceRepository,
    userRepository: UserRepository,
) : ViewModel() {

    val state: StateFlow<AdminPanelHomeViewState> = combine(userRepository.loggedInUser,
        deviceRepository.devices) { loggedInUser: LoggedInUser?, devices: CopyOnWriteArrayList<Device> ->
        adminPanelHomeMapper.toAdminPanelHomeViewState(loggedInUser?.userId, devices)
    }.stateIn(viewModelScope, SharingStarted.Lazily, AdminPanelHomeViewState.empty())
}
