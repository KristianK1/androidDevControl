package hr.kristiankliskovic.devcontrol.data.repository.device

import hr.kristiankliskovic.devcontrol.model.Device
import kotlinx.coroutines.flow.Flow

interface DeviceRepository {

    val devices: Flow<List<Device>>
    fun getDevice(deviceId: Int): Flow<Device>
}
