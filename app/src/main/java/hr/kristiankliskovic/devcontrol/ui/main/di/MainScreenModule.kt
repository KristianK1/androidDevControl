package hr.kristiankliskovic.devcontrol.ui.main.di

import hr.kristiankliskovic.devcontrol.ui.main.MainScreenViewModel
import org.koin.dsl.module

val mainScreenModule = module {
    single<MainScreenViewModel>{
        MainScreenViewModel(
            userRepository = get()
        )
    }
}
