package hr.kristiankliskovic.devcontrol.ui.deviceControls.di

import hr.kristiankliskovic.devcontrol.ui.deviceControls.DeviceControlsViewModel
import hr.kristiankliskovic.devcontrol.ui.deviceControls.mapper.DeviceControlsMapper
import hr.kristiankliskovic.devcontrol.ui.deviceControls.mapper.DeviceControlsMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val deviceControlsModule = module {
    viewModel { (deviceId: Int) ->
        DeviceControlsViewModel(
            deviceId = deviceId,
            deviceRepository = get(),
            deviceControlsMapper = get()
        )
    }
    single<DeviceControlsMapper> {
        DeviceControlsMapperImpl()
    }
}
