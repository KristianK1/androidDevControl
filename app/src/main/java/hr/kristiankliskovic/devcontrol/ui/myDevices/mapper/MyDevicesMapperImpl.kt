package hr.kristiankliskovic.devcontrol.ui.myDevices.mapper

import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.DeviceNameAndStatusViewState
import hr.kristiankliskovic.devcontrol.ui.myDevices.MyDevicesViewState

class MyDevicesMapperImpl: MyDevicesMapper {
    override fun toMyDevicesViewState(devices: List<Device>): MyDevicesViewState {
        val list = devices.map{ device ->
            DeviceNameAndStatusViewState(
                deviceId = device.deviceId,
                deviceName = device.deviceName,
                deviceStatus = device.isActive,
            )
        }
        return MyDevicesViewState(list)
    }
}
