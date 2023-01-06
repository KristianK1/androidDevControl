package hr.kristiankliskovic.devcontrol.data.di

import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val deviceRepositoryModule = module {
    single<DeviceRepository>{
        DeviceRepositoryImpl(
            deviceService = get(),
            authTokenRepository = get(),
            websocketService = get(),
            bgDispatcher = Dispatchers.IO
        )
    }
}
