package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.di

import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.AddTriggerViewModel
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.mapper.AddTriggerMapper
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.mapper.AddTriggerMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addTriggerModule = module {
    viewModel {
        AddTriggerViewModel(
            deviceRepository = get(),
            addTriggerMapper = get()
        )
    }
    single<AddTriggerMapper> {
        AddTriggerMapperImpl()
    }
}
