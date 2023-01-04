package hr.kristiankliskovic.devcontrol.ui.main

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
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
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.SeeAllPermissionsRoute
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showIcons by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.route == MY_DEVICES_ROUTE
        }
    }

    val viewModel: MainScreenViewModel = getViewModel()
    val loggedIn = viewModel.loggedInUser.collectAsState()
    val userMessages = viewModel.userMessages.collectAsState()
    viewModel.startWS()
    Scaffold(
        topBar = {
            TopBar(
                text = "devControl",
                showIcons = showIcons,
                textSecondary = null,
                navigateToAdminPanel = {
                    navController.navigate(ADMIN_PANEL_ROUTE)
                },
                navigateToUserSettings = {
                    navController.navigate(USER_PROFILE_ROUTE)
                }
            )
        }
    ) { padding ->
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = LOGIN_ROUTE,
                modifier = Modifier.padding(padding)
            ) {
                composable(LOGIN_ROUTE) {
                    LoginRoute(
                        loginViewModel = getViewModel(),
                        registerInstead = {
                            navController.navigate(REGISTER_ROUTE)
                        }
                    )
                }
                composable(REGISTER_ROUTE) {
                    RegisterRoute(
                        registerViewModel = getViewModel(),
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
                composable(ADMIN_PANEL_ROUTE) {
                    AdminPanelHomeRoute(
                        navigateToAdminPanelDevice = { route ->
                            navController.navigate(route)
                        },
                        navigateToAddNewDevice = {
                            navController.navigate(ADD_NEW_DEVICE_ROUTE)
                        }
                    )
                }
                composable(
                    route = AdminPanelDeviceDestination.route,
                    arguments = listOf(navArgument(ADMIN_PANEL_DEVICE_ID_KEY) {
                        type = NavType.IntType
                    })
                ) {
                    AdminPanelDeviceRoute(
                        navigateToChangeDeviceAdmin = {
                            navController.navigate(CHANGE_DEVICE_ADMIN_ROUTE)
                        },
                        navigateToAddNewPermission = {
                            navController.navigate(ADD_PERMISSION_ROUTE)
                        },
                        navigateToSeeAllPermissions = {
                            navController.navigate(SEE_ALL_PERMISSIONS_ROUTE)
                        }
                    )
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
                composable(SEE_ALL_PERMISSIONS_ROUTE) {
                    SeeAllPermissionsRoute()
                }
                composable(USER_PROFILE_ROUTE) {
                    UserProfileSettingsRoute(
                        navigateToChangePasswordScreen = {
                            navController.navigate(CHANGE_PASSWORD_ROUTE)
                        },
                        userProfileSettingsViewModel = get()
                    )
                }
                composable(CHANGE_PASSWORD_ROUTE) {
                    ChangePasswordRoute(
                        viewModel = getViewModel(),
                        navigateBackToUserSettings = {
                            navController.navigate(USER_PROFILE_ROUTE){
                                popUpTo(USER_PROFILE_ROUTE)
                            }
                        }
                    )
                }
            }
        }
        when (loggedIn.value) {
            true -> {
                Log.i("mainScreen", "Qtrue")
                navController.navigate(MY_DEVICES_ROUTE) {
                    popUpTo(LOGIN_ROUTE) {
                        inclusive = true
                    }
                }
                viewModel.startWS()
            }
            false -> {
                Log.i("mainScreen", "Qfalse")
                navController.navigate(LOGIN_ROUTE) {
                    launchSingleTop = true
                    popUpTo(MY_DEVICES_ROUTE) {
                        inclusive = true
                    }
                }
                viewModel.stopWS()
            }
            null -> {
                Log.i("mainScreen", "Qnull")
            }
        }
        if (userMessages.value != null) {
            viewModel.logout()
        }
    }
}

@Composable
fun TopBar(
    showIcons: Boolean,
    text: String?,
    textSecondary: String?,
    navigateToUserSettings: () -> Unit,
    navigateToAdminPanel: () -> Unit,
) {
    if (text != null) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.topBarHeight)),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.topBarHeight))
            ) {
                Text(
                    text = text,
                )
            }
            if (showIcons) {
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.topBarHeight))
                ) {
                    Row(
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Icon(
                            Icons.Filled.AccountBox,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(dimensionResource(id = R.dimen.topBarHeight))
                                .clickable {
                                    navigateToAdminPanel()
                                }
                        )
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(dimensionResource(id = R.dimen.topBarHeight))
                                .clickable {
                                    navigateToUserSettings()
                                }
                        )
                    }
                }
            }
        }
    }
    if (textSecondary != null) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.topBarSecundaryHeight)),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = textSecondary)
        }
    }
}
