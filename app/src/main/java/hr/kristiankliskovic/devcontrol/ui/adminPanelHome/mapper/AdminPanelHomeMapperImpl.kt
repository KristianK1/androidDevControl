package hr.kristiankliskovic.devcontrol.ui.adminPanelHome.mapper

import android.util.Log
import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.ui.adminPanelHome.AdminPanelHomeDeviceViewState
import hr.kristiankliskovic.devcontrol.ui.adminPanelHome.AdminPanelHomeViewState

class AdminPanelHomeMapperImpl : AdminPanelHomeMapper {
    override fun toAdminPanelHomeViewState(myUserId: Int?,devices: List<Device>): AdminPanelHomeViewState {
        Log.i("mapper2", "${myUserId}_${devices.size}")
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
