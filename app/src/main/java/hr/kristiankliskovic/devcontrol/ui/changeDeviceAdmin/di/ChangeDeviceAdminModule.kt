package hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin.di

import hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin.ChangeDeviceAdminViewModel
import hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin.mapper.ChangeDeviceAdminMapper
import hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin.mapper.ChangeDeviceAdminMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val changeDeviceAdminModule = module {
    single<ChangeDeviceAdminMapper> {
        ChangeDeviceAdminMapperImpl()
    }
    viewModel { (deviceId: Int) ->
        ChangeDeviceAdminViewModel(
            deviceId = deviceId,
            mapper = get(),
            userRepository = get(),
            deviceRepository = get(),
        )
    }
}
