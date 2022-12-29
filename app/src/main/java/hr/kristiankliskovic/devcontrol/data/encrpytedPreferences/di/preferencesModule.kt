package hr.kristiankliskovic.devcontrol.data.encrpytedPreferences.di

import hr.kristiankliskovic.devcontrol.data.encrpytedPreferences.PreferencesManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferencesModule = module{
    single<PreferencesManager>{
        PreferencesManager(context = androidContext())
    }
}
