package hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin.mapper

import hr.kristiankliskovic.devcontrol.model.User
import hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin.ChangeDeviceAdminViewState

interface ChangeDeviceAdminMapper {
    fun toChangeDeviceAdminViewState(users: List<User>): ChangeDeviceAdminViewState
}
