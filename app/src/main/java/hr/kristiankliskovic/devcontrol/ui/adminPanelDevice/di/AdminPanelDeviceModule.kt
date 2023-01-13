package hr.kristiankliskovic.devcontrol.ui.adminPanelDevice.di

import hr.kristiankliskovic.devcontrol.ui.adminPanelDevice.AdminPanelDeviceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val adminPanelDeviceModule = module {
    viewModel { (deviceId: Int) ->
        AdminPanelDeviceViewModel(
            deviceId = deviceId,
            deviceRepository = get()
        )
    }
}
