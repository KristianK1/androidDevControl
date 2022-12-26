package hr.kristiankliskovic.devcontrol.ui.adminPanelListOfDevices.mapper

import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.ui.adminPanelListOfDevices.AdminPanelHomeViewState

interface AdminPanelHomeMapper {
    fun toAdminPanelHomeViewState(myUserId: Int, devices: List<Device>): AdminPanelHomeViewState
}
