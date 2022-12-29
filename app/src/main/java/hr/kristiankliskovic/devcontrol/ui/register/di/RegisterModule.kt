package hr.kristiankliskovic.devcontrol.ui.register.di

import hr.kristiankliskovic.devcontrol.ui.register.RegisterViewModel
import org.koin.dsl.module

val registerModule = module {
    single<RegisterViewModel> {
        RegisterViewModel(
            userRepository = get(),
        )
    }
}
