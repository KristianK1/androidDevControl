package hr.kristiankliskovic.devcontrol.ui.addNewDevice.di

import hr.kristiankliskovic.devcontrol.ui.addNewDevice.AddNewDeviceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addNewDeviceModule = module {
    viewModel {
        AddNewDeviceViewModel(
            deviceRepository = get(),
        )
    }
}
