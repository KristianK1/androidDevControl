package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.mapper

import hr.kristiankliskovic.devcontrol.model.AllUserRightsToDevice
import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.model.User
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.SeeAllPermissionsViewState

interface SeeAllPermissionsMapper {
    fun toSeeAllPermissionViewState(
        device: Device,
        rights: AllUserRightsToDevice,
        users: List<User>,
    ): SeeAllPermissionsViewState
}
