package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.network.model.UserPermissionsForDeviceResponse
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission.AddPermissionViewState
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.mapper.SeeAllPermissionsMapper
import kotlinx.coroutines.flow.*

class SeeAllPermissionsViewModel(
    deviceId: Int,
    deviceRepository: DeviceRepository,
    private val mapper: SeeAllPermissionsMapper,
) : ViewModel() {
    val viewState: StateFlow<SeeAllPermissionsViewState> = combine(
        deviceRepository.getDevice(deviceId),
        deviceRepository.getUserPermissionsForDevice(deviceId)
    ) { device: Device, userPermissionsForDeviceResponse: UserPermissionsForDeviceResponse? ->
        mapper.toSeeAllPermissionViewState(device, userPermissionsForDeviceResponse)
    }.stateIn(viewModelScope, SharingStarted.Lazily, SeeAllPermissionsViewState.getEmptyObject())
}
