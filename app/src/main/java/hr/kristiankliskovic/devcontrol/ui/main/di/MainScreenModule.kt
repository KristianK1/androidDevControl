package hr.kristiankliskovic.devcontrol.ui.main.di

import hr.kristiankliskovic.devcontrol.ui.main.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainScreenModule = module {
    viewModel{
        MainScreenViewModel(
            userRepository = get(),
            deviceRepository = get(),
        )
    }
}
