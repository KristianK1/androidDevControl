package hr.kristiankliskovic.devcontrol.ui.userProfileSettings.di

import hr.kristiankliskovic.devcontrol.ui.userProfileSettings.UserProfileSettingsViewModel
import hr.kristiankliskovic.devcontrol.ui.userProfileSettings.mapper.UserProfileSettingsMapper
import hr.kristiankliskovic.devcontrol.ui.userProfileSettings.mapper.UserProfileSettingsMapperImpl
import org.koin.dsl.module

val userProfileSettingsModule = module {
    single<UserProfileSettingsViewModel>{
        UserProfileSettingsViewModel(
            userRepository = get(),
            mapper = get(),
        )
    }
    single<UserProfileSettingsMapper>{
        UserProfileSettingsMapperImpl()
    }
}
