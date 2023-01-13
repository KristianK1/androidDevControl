package hr.kristiankliskovic.devcontrol.ui.adminPanelDevice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import kotlinx.coroutines.launch

class AdminPanelDeviceViewModel(
    val deviceId: Int,
    val deviceRepository: DeviceRepository,
) : ViewModel() {
    val state = AdminPanelDeviceViewState(deviceId = deviceId)

    fun deleteDevice() {
        viewModelScope.launch {
            deviceRepository.deleteDevice(deviceId = deviceId)
        }
    }
}
