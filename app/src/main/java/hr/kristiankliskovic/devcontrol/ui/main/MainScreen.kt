package hr.kristiankliskovic.devcontrol.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hr.kristiankliskovic.devcontrol.navigation.*
import hr.kristiankliskovic.devcontrol.ui.addNewDevice.AddNewDeviceRoute
import hr.kristiankliskovic.devcontrol.ui.adminPanelDevice.AdminPanelDeviceRoute
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission.AddPermissionRoute
import hr.kristiankliskovic.devcontrol.ui.adminPanelListOfDevices.AdminPanelHomeRoute
import hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin.ChangeDeviceAdminRoute
import hr.kristiankliskovic.devcontrol.ui.deviceControls.DeviceControlsRoute
import hr.kristiankliskovic.devcontrol.ui.login.LoginRoute
import hr.kristiankliskovic.devcontrol.ui.myDevices.MyDevicesRoute
import hr.kristiankliskovic.devcontrol.ui.register.RegisterRoute
import hr.kristiankliskovic.devcontrol.ui.userProfileSettings.UserProfileSettingsRoute
import hr.kristiankliskovic.devcontrol.ui.userProfileSettingsChangePassword.ChangePasswordRoute

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = LOGIN_ROUTE,
            modifier = Modifier.padding(padding)
        ) {
            composable(LOGIN_ROUTE) {
                LoginRoute(
                    login = { _, _ ->

                    },
                    registerInstead = {

                    }
                )
            }
            composable(REGISTER_ROUTE) {
                RegisterRoute(
                    register = { _, _ ->

                    },
                    loginInstead = {

                    }
                )
            }
            composable(MY_DEVICES_ROUTE) {
                MyDevicesRoute()
            }
            composable(
                route = DeviceControlsDestination.route,
                arguments = listOf(navArgument(DEVICE_CONTROLS_ID_KEY) { type = NavType.IntType })
            ) {
                DeviceControlsRoute()
            }
            composable(ADMIN_PANEL_ROUTE) {
                AdminPanelHomeRoute()
            }
            composable(
                route = AdminPanelDeviceDestination.route,
                arguments = listOf(navArgument(ADMIN_PANEL_DEVICE_ID_KEY) {
                    type = NavType.IntType
                })
            ) {
                AdminPanelDeviceRoute()
            }
            composable(ADD_NEW_DEVICE_ROUTE) {
                AddNewDeviceRoute()
            }
            composable(ADD_PERMISSION_ROUTE) {
                AddPermissionRoute()
            }
            composable(CHANGE_DEVICE_ADMIN_ROUTE) {
                ChangeDeviceAdminRoute()
            }
            composable(USER_PROFILE_ROUTE) {
                UserProfileSettingsRoute()
            }
            composable(CHANGE_PASSWORD_ROUTE) {
                ChangePasswordRoute()
            }
        }
    }
}
