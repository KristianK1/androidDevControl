package hr.kristiankliskovic.devcontrol.ui.triggerSettings_seeAll.di

import hr.kristiankliskovic.devcontrol.ui.triggerSettings_seeAll.SeeAllTriggersViewModel
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_seeAll.mapper.SeeAllTriggersMapper
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_seeAll.mapper.SeeAllTriggersMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val getAllTriggersModule = module {
    viewModel{
        SeeAllTriggersViewModel(
            deviceRepository = get(),
            mapper = get(),
        )
    }
    single<SeeAllTriggersMapper>{
        SeeAllTriggersMapperImpl()
    }
}
