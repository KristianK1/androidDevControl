package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import hr.kristiankliskovic.devcontrol.model.User
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission.mapper.AddPermissionMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class AddPermissionViewModel(
    val deviceId: Int,
    val mapper: AddPermissionMapper,
    val deviceRepository: DeviceRepository,
    val userRepository: UserRepository,
) : ViewModel() {

    val viewState: StateFlow<AddPermissionViewState> = combine(
        userRepository.getOtherUsers(),
        deviceRepository.getDevice(deviceId)
    ) { users: List<User>, device: Device ->
        mapper.toAddPermissionViewState(device = device, users = users)
    }.stateIn(viewModelScope, SharingStarted.Lazily, AddPermissionViewState.getEmptyObject())
}
