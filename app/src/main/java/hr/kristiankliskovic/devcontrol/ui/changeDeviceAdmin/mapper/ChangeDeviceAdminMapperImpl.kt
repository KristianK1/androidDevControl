package hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin.mapper

import hr.kristiankliskovic.devcontrol.model.User
import hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin.ChangeDeviceAdminUserViewState
import hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin.ChangeDeviceAdminViewState

class ChangeDeviceAdminMapperImpl: ChangeDeviceAdminMapper {
    override fun toChangeDeviceAdminViewState(
        users: List<User>,
    ): ChangeDeviceAdminViewState {
        return ChangeDeviceAdminViewState(
            users = users.map{ user ->
                ChangeDeviceAdminUserViewState(
                    userId = user.id,
                    username = user.username,
                )
            }
        )
    }
}
