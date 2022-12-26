package hr.kristiankliskovic.devcontrol.ui.adminPanelListOfDevices.mapper

import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.ui.adminPanelListOfDevices.AdminPanelHomeDeviceViewState
import hr.kristiankliskovic.devcontrol.ui.adminPanelListOfDevices.AdminPanelHomeViewState

class AdminPanelHomeMapperImpl : AdminPanelHomeMapper {
    override fun toAdminPanelHomeViewState(myUserId: Int,devices: List<Device>): AdminPanelHomeViewState {
        return AdminPanelHomeViewState(
            devices = devices.filter{
                myUserId == it.userAdminId
            }.map { device ->
                AdminPanelHomeDeviceViewState(
                    deviceId = device.deviceId,
                    deviceName = device.deviceName,
                )
            }
        )
    }
}
