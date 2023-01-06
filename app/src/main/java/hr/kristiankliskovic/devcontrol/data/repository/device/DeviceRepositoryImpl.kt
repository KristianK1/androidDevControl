package hr.kristiankliskovic.devcontrol.data.repository.device

import android.util.Log
import hr.kristiankliskovic.devcontrol.data.network.deviceService.DeviceService
import hr.kristiankliskovic.devcontrol.data.network.wsService.WebSocketService
import hr.kristiankliskovic.devcontrol.model.Device
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DeviceRepositoryImpl(
    private val websocketService: WebSocketService,
    private val deviceService: DeviceService,
    private val bgDispatcher: CoroutineDispatcher,
) : DeviceRepository {

    private var devicesInternal: MutableList<Device> = mutableListOf()

    override val devices: Flow<List<Device>> = flow {
        websocketService.deviceMessages.collect { device ->
            Log.i("deviceData", "devRepo_devices_collect")
            if (device != null) {
                val index = devicesInternal.indexOfLast { it.deviceId == device.deviceId }
                devicesInternal.removeIf { it.deviceId == device.deviceId }
                if (index != -1) {
                    devicesInternal.add(index, device)
                } else {
                    devicesInternal.add(device)
                }
                emit(devicesInternal)
            }
        }
    }.flowOn(bgDispatcher)

    override fun getDevice(deviceId: Int): Flow<Device> = flow {
        emit(devicesInternal.find { it.deviceId == deviceId }!!)
    }.flowOn(bgDispatcher)
}
