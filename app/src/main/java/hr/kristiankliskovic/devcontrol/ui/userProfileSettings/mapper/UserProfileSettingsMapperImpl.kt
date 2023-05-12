package hr.kristiankliskovic.devcontrol.ui.userProfileSettings.mapper

import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import hr.kristiankliskovic.devcontrol.ui.userProfileSettings.UserProfileSettingsViewState

class UserProfileSettingsMapperImpl: UserProfileSettingsMapper {
    override fun toUserProfileViewState(loggedInUser: LoggedInUser): UserProfileSettingsViewState {
        return UserProfileSettingsViewState(
            userId = loggedInUser.userId,
            username = loggedInUser.username,
            email = loggedInUser.email,
        )
    }
}
