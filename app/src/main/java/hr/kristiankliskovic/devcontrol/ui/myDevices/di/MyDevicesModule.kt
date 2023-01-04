package hr.kristiankliskovic.devcontrol.ui.myDevices.di

import hr.kristiankliskovic.devcontrol.ui.myDevices.MyDevicesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myDevicesModule = module {
    viewModel {
        MyDevicesViewModel(
            deviceRepository = get()
        )
    }
}
