package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission.mapper

import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.model.User
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission.AddPermissionViewState

interface AddPermissionMapper {
    fun toAddPermissionViewState(device: Device?, users: List<User>): AddPermissionViewState
}
