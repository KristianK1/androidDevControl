package hr.kristiankliskovic.devcontrol.ui.userProfileSettingsAddEmail.di

import hr.kristiankliskovic.devcontrol.ui.userProfileSettingsChangePassword.ChangePasswordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val emailModule = module {
    viewModel {
        AddEmailViewModel(
            userRepository = get()
        )
    }
}
