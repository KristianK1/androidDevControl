package hr.kristiankliskovic.devcontrol.ui.login.di

import hr.kristiankliskovic.devcontrol.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel{
        LoginViewModel(
            userRepository = get(),
        )
    }
}
