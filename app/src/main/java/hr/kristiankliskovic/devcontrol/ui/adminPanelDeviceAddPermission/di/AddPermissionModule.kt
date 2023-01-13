package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission.di

import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission.AddPermissionViewModel
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission.mapper.AddPermissionMapper
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission.mapper.AddPermissionMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addPermissionModule = module {
    viewModel { (deviceId: Int) ->
        AddPermissionViewModel(
            deviceId = deviceId,
            mapper = get(),
            deviceRepository = get(),
            userRepository = get(),
        )
    }
    single<AddPermissionMapper>{
        AddPermissionMapperImpl()
    }
}
