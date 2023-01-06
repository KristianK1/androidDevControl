package hr.kristiankliskovic.devcontrol

import android.app.Application
import hr.kristiankliskovic.devcontrol.data.di.authTokenRepositoryModule
import hr.kristiankliskovic.devcontrol.data.di.deviceRepositoryModule
import hr.kristiankliskovic.devcontrol.data.encrpytedPreferences.di.preferencesModule
import hr.kristiankliskovic.devcontrol.data.network.networkModule
import hr.kristiankliskovic.devcontrol.data.di.userRepositoryModule
import hr.kristiankliskovic.devcontrol.ui.deviceControls.di.deviceControlsModule
import hr.kristiankliskovic.devcontrol.ui.login.di.loginModule
import hr.kristiankliskovic.devcontrol.ui.main.di.mainScreenModule
import hr.kristiankliskovic.devcontrol.ui.myDevices.di.myDevicesModule
import hr.kristiankliskovic.devcontrol.ui.register.di.registerModule
import hr.kristiankliskovic.devcontrol.ui.userProfileSettings.di.userProfileSettingsModule
import hr.kristiankliskovic.devcontrol.ui.userProfileSettingsChangePassword.di.changePasswordModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DevControlApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DevControlApp)
            modules(
                networkModule,
                userRepositoryModule,
                preferencesModule,
                deviceRepositoryModule,
                authTokenRepositoryModule,
                loginModule,
                registerModule,
                mainScreenModule,
                userProfileSettingsModule,
                changePasswordModule,
                myDevicesModule,
                deviceControlsModule,
            )
        }
    }
}
