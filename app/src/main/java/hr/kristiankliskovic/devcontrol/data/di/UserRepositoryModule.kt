package hr.kristiankliskovic.devcontrol.data.di

import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val userRepositoryModule = module {
    single<UserRepository> {
        UserRepositoryImpl(
            userService = get(),
            authTokenRepository = get(),
            ioDispatcher = Dispatchers.IO,
        )
    }
}
