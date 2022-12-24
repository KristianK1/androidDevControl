package hr.kristiankliskovic.devcontrol.ui.myDevices.mapper

import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.ui.myDevices.MyDevicesViewState

interface MyDevicesMapper {
    fun toMyDevicesViewState(devices: List<Device>): MyDevicesViewState
}
