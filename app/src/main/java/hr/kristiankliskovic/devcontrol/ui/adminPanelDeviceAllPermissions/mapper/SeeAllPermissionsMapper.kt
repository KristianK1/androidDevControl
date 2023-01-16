package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.mapper

import hr.kristiankliskovic.devcontrol.data.network.model.UserPermissionsForDeviceResponse
import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.SeeAllPermissionsViewState

interface SeeAllPermissionsMapper {
    fun toSeeAllPermissionViewState(
        device: Device?,
        permissions: UserPermissionsForDeviceResponse?,
    ): SeeAllPermissionsViewState
}
