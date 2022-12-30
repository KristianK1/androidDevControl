package hr.kristiankliskovic.devcontrol.ui.register.di

import hr.kristiankliskovic.devcontrol.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registerModule = module {
    viewModel {
        RegisterViewModel(
            userRepository = get(),
        )
    }
}
