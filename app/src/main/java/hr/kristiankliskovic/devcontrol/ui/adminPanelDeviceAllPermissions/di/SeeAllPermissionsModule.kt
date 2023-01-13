package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.di

import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.SeeAllPermissionsViewModel
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.mapper.SeeAllPermissionsMapper
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.mapper.SeeAllPermissionsMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val seeAllPermissionsModule = module{
    viewModel { (deviceId: Int) ->
        SeeAllPermissionsViewModel(
            deviceId = deviceId,
            deviceRepository = get(),
            mapper = get(),
        )
    }
    single<SeeAllPermissionsMapper>{
        SeeAllPermissionsMapperImpl()
    }
}
