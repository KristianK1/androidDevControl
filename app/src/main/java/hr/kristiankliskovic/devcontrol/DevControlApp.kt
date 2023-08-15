package hr.kristiankliskovic.devcontrol

import android.app.Application
import hr.kristiankliskovic.devcontrol.data.di.authTokenRepositoryModule
import hr.kristiankliskovic.devcontrol.data.di.deviceRepositoryModule
import hr.kristiankliskovic.devcontrol.data.di.firebaseNotificationTokenModule
import hr.kristiankliskovic.devcontrol.data.encrpytedPreferences.di.preferencesModule
import hr.kristiankliskovic.devcontrol.data.network.di.networkModule
import hr.kristiankliskovic.devcontrol.data.di.userRepositoryModule
import hr.kristiankliskovic.devcontrol.data.network.pushNotifications.PushNotificationService
import hr.kristiankliskovic.devcontrol.ui.addNewDevice.di.addNewDeviceModule
import hr.kristiankliskovic.devcontrol.ui.adminPanelDevice.di.adminPanelDeviceModule
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission.di.addPermissionModule
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.di.seeAllPermissionsModule
import hr.kristiankliskovic.devcontrol.ui.adminPanelHome.di.adminPanelHomeModule
import hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin.di.changeDeviceAdminModule
import hr.kristiankliskovic.devcontrol.ui.deviceControls.di.deviceControlsModule
import hr.kristiankliskovic.devcontrol.ui.login.di.loginModule
import hr.kristiankliskovic.devcontrol.ui.main.di.mainScreenModule
import hr.kristiankliskovic.devcontrol.ui.myDevices.di.myDevicesModule
import hr.kristiankliskovic.devcontrol.ui.register.di.registerModule
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.di.addTriggerModule
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_seeAll.di.getAllTriggersModule
import hr.kristiankliskovic.devcontrol.ui.userProfileSettings.di.userProfileSettingsModule
import hr.kristiankliskovic.devcontrol.ui.userProfileSettingsAddEmail.di.emailModule
import hr.kristiankliskovic.devcontrol.ui.userProfileSettingsChangePassword.di.changePasswordModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.get
import org.koin.core.context.startKoin

class DevControlApp: Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
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
                adminPanelHomeModule,
                changeDeviceAdminModule,
                adminPanelDeviceModule,
                addNewDeviceModule,
                addPermissionModule,
                seeAllPermissionsModule,
                emailModule,
                addTriggerModule,
                getAllTriggersModule,
                firebaseNotificationTokenModule,
            )
        }
    }

    companion object{
        lateinit var application: Application
    }
}
