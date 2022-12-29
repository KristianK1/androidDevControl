package hr.kristiankliskovic.devcontrol.ui.login.di

import hr.kristiankliskovic.devcontrol.ui.login.LoginViewModel
import org.koin.dsl.module

val loginModule = module {
    single<LoginViewModel> {
        LoginViewModel(
            userRepository = get(),
        )
    }
}
