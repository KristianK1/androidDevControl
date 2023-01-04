package hr.kristiankliskovic.devcontrol.data.repository.device

import hr.kristiankliskovic.devcontrol.data.network.deviceService.DeviceService
import hr.kristiankliskovic.devcontrol.data.network.wsService.WebSocketService

class DeviceRepositoryImpl(
    private val websocketService: WebSocketService,
    private val deviceService: DeviceService,
) : DeviceRepository {

}
