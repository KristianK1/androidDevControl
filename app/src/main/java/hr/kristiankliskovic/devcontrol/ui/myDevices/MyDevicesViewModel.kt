package hr.kristiankliskovic.devcontrol.ui.myDevices

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.ui.myDevices.mapper.MyDevicesMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MyDevicesViewModel(
    deviceRepository: DeviceRepository,
    myDevicesMapper: MyDevicesMapper,
) : ViewModel() {
    val myDevicesViewState: StateFlow<MyDevicesViewState> = deviceRepository.devices.map {
        Log.i("deviceData", "deviceViewModel")
        myDevicesMapper.toMyDevicesViewState(devices = it)
    }.stateIn(viewModelScope, SharingStarted.Lazily, MyDevicesViewState.empty())
}
