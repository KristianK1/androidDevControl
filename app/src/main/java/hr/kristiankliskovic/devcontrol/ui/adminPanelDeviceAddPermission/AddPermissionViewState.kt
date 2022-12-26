package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission

import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.model.User

data class AddPermissionViewState(
    val device: Device,
    val users: List<User>,
)
