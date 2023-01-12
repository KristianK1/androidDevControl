package hr.kristiankliskovic.devcontrol.ui.adminPanelHome.di

import hr.kristiankliskovic.devcontrol.ui.adminPanelHome.AdminPanelHomeViewModel
import hr.kristiankliskovic.devcontrol.ui.adminPanelHome.mapper.AdminPanelHomeMapper
import hr.kristiankliskovic.devcontrol.ui.adminPanelHome.mapper.AdminPanelHomeMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val adminPanelHomeModule = module {
    viewModel {
        AdminPanelHomeViewModel(
            adminPanelHomeMapper = get(),
            deviceRepository = get(),
            userRepository = get(),
        )
    }
    single<AdminPanelHomeMapper>{
        AdminPanelHomeMapperImpl()
    }
}
