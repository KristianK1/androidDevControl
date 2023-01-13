package hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin

import hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin.mapper.ChangeDeviceAdminMapper
import hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin.mapper.ChangeDeviceAdminMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val changeDeviceAdminModule = module {
    single<ChangeDeviceAdminMapper> {
        ChangeDeviceAdminMapperImpl()
    }
    viewModel {
        ChangeDeviceAdminViewModel(
            mapper = get(),
            userRepository = get(),
            deviceRepository = get(),
        )
    }
}
