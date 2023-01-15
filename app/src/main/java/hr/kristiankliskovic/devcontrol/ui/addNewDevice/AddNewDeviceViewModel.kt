package hr.kristiankliskovic.devcontrol.ui.addNewDevice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import kotlinx.coroutines.launch

class AddNewDeviceViewModel(
    val deviceRepository: DeviceRepository,
) : ViewModel() {

    fun addNewDevice(deviceName: String, deviceKey: String?) {
        viewModelScope.launch {
            deviceRepository.addNewDevice(
                deviceName = deviceName,
                deviceKey = deviceKey
            )
        }
    }
}
