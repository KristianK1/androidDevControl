package hr.kristiankliskovic.devcontrol.ui.userProfileSettings.mapper

import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import hr.kristiankliskovic.devcontrol.ui.userProfileSettings.UserProfileSettingsViewState

interface UserProfileSettingsMapper {
    fun toUserProfileViewState(loggedInUser: LoggedInUser): UserProfileSettingsViewState
}
