package hr.kristiankliskovic.devcontrol.data.di

import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepositoryImpl
import org.koin.dsl.module

val deviceRepositoryModule = module {
    single<DeviceRepository>{
        DeviceRepositoryImpl(
            deviceService = get(),
            websocketService = get(),
        )
    }
}
