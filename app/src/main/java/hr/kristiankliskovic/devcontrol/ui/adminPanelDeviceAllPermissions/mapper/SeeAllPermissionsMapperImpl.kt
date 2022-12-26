package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.mapper

import androidx.compose.ui.node.modifierElementOf
import hr.kristiankliskovic.devcontrol.model.AllUserRightsToDevice
import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.model.User
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.SeeAllPermissionsViewState
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.UserRightViewState

class SeeAllPermissionsMapperImpl : SeeAllPermissionsMapper {
    override fun toSeeAllPermissionViewState(
        device: Device,
        rights: AllUserRightsToDevice,
        users: List<User>,
    ): SeeAllPermissionsViewState {
        return SeeAllPermissionsViewState(
            rights = rights.deviceRights.map { userRightToDevice ->
                UserRightViewState(
                    userId = userRightToDevice.userId,
                    username = users.find { it.userId == userRightToDevice.userId }!!.username,
                    readOnly = userRightToDevice.readOnly,
                )
            },
            groupRights = listOf(),
            complexGroupRights = listOf()
        )
    }
}
