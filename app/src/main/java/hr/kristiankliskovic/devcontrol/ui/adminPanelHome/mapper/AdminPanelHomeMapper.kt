package hr.kristiankliskovic.devcontrol.ui.adminPanelHome.mapper

import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.ui.adminPanelHome.AdminPanelHomeViewState

interface AdminPanelHomeMapper {
    fun toAdminPanelHomeViewState(myUserId: Int?, devices: List<Device>): AdminPanelHomeViewState
}
