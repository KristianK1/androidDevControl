package hr.kristiankliskovic.devcontrol.ui.userProfileSettings.di

import hr.kristiankliskovic.devcontrol.ui.userProfileSettings.UserProfileSettingsViewModel
import org.koin.dsl.module

val userProfileSettingsModule = module {
    single<UserProfileSettingsViewModel>{
        UserProfileSettingsViewModel(
            userRepository = get(),
        )
    }
}
