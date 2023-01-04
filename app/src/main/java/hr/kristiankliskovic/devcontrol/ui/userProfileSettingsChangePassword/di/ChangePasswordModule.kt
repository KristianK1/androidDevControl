package hr.kristiankliskovic.devcontrol.ui.userProfileSettingsChangePassword.di

import hr.kristiankliskovic.devcontrol.ui.userProfileSettingsChangePassword.ChangePasswordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val changePasswordModule = module {
    viewModel {
        ChangePasswordViewModel(
            userRepository = get()
        )
    }
}
