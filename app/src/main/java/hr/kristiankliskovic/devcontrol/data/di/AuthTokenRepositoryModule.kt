package hr.kristiankliskovic.devcontrol.data.di

import hr.kristiankliskovic.devcontrol.data.repository.authToken.AuthTokenRepository
import hr.kristiankliskovic.devcontrol.data.repository.authToken.AuthTokenRepositoryImpl
import org.koin.dsl.module

val authTokenRepositoryModule = module {
    single<AuthTokenRepository> {
        AuthTokenRepositoryImpl(get())
    }
}
