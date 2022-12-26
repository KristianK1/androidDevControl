package hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin.mapper

import hr.kristiankliskovic.devcontrol.model.User
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.ChangeDeviceAdminUserViewState
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.ChangeDeviceAdminViewState

class ChangeDeviceAdminMapperImpl: ChangeDeviceAdminMapper {
    override fun toChangeDeviceAdminViewState(
        users: List<User>,
        myUserId: Int,
    ): ChangeDeviceAdminViewState {
        return ChangeDeviceAdminViewState(
            users = users.filter { it.userId != myUserId }.map{ user ->
                ChangeDeviceAdminUserViewState(
                    userId = user.userId,
                    username = user.username,
                )
            }
        )
    }
}
