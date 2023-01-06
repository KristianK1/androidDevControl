package hr.kristiankliskovic.devcontrol.ui.deviceControls

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.ui.deviceControls.mapper.DeviceControlsMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DeviceControlsViewModel(
    private val deviceId: Int,
    private val deviceRepository: DeviceRepository,
    private val deviceControlsMapper: DeviceControlsMapper
): ViewModel(){
    val deviceControlsViewState: StateFlow<DeviceControlsViewState> =
        deviceRepository.getDevice(deviceId = deviceId).map { deviceData ->
            deviceControlsMapper.toDeviceControlsViewState(deviceData)
        }.stateIn(viewModelScope, SharingStarted.Lazily, DeviceControlsViewState.empty())
}
