package hr.kristiankliskovic.devcontrol.data.encrpytedPreferences.di

import hr.kristiankliskovic.devcontrol.DevControlApp
import hr.kristiankliskovic.devcontrol.data.encrpytedPreferences.PreferencesManager
import io.ktor.http.*
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import kotlin.coroutines.coroutineContext

val preferencesModule = module {
    single {
        PreferencesManager(context = androidContext())
    }
}
