package hr.kristiankliskovic.devcontrol.ui.main

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
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
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = MY_DEVICES_ROUTE,
                modifier = Modifier.padding(padding)
            ) {
                composable(LOGIN_ROUTE) {
                    LoginRoute(
                        login = { _, _ ->

                        },
                        registerInstead = {
                            navController.navigate(REGISTER_ROUTE)
                        }
                    )
                }
                composable(REGISTER_ROUTE) {
                    RegisterRoute(
                        register = { _, _ ->

                        },
                        loginInstead = {
                            navController.navigate(LOGIN_ROUTE) {
                                popUpTo(LOGIN_ROUTE) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
                composable(MY_DEVICES_ROUTE) {
                    MyDevicesRoute(
                        navigateToDevice = { route ->
                            Log.i("navvv", route)
                            navController.navigate(route)
                        }
                    )
                }
                composable(
                    route = DeviceControlsDestination.route,
                    arguments = listOf(navArgument(DEVICE_CONTROLS_ID_KEY) {
                        type = NavType.IntType
                    })
                ) {
                    DeviceControlsRoute()
                }
//                composable(ADMIN_PANEL_ROUTE) {
//                    AdminPanelHomeRoute()
//                }
//                composable(
//                    route = AdminPanelDeviceDestination.route,
//                    arguments = listOf(navArgument(ADMIN_PANEL_DEVICE_ID_KEY) {
//                        type = NavType.IntType
//                    })
//                ) {
//                    AdminPanelDeviceRoute()
//                }
//                composable(ADD_NEW_DEVICE_ROUTE) {
//                    AddNewDeviceRoute()
//                }
//                composable(ADD_PERMISSION_ROUTE) {
//                    AddPermissionRoute()
//                }
//                composable(CHANGE_DEVICE_ADMIN_ROUTE) {
//                    ChangeDeviceAdminRoute()
//                }
//                composable(USER_PROFILE_ROUTE) {
//                    UserProfileSettingsRoute()
//                }
//                composable(CHANGE_PASSWORD_ROUTE) {
//                    ChangePasswordRoute()
//                }
            }
        }
    }
}
