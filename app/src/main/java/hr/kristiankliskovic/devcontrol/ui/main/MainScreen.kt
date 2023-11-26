package hr.kristiankliskovic.devcontrol.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
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
import hr.kristiankliskovic.devcontrol.ui.adminPanelHome.AdminPanelHomeRoute
import hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin.ChangeDeviceAdminRoute
import hr.kristiankliskovic.devcontrol.ui.deviceControls.DeviceControlsRoute
import hr.kristiankliskovic.devcontrol.ui.login.LoginRoute
import hr.kristiankliskovic.devcontrol.ui.myDevices.MyDevicesRoute
import hr.kristiankliskovic.devcontrol.ui.register.RegisterRoute
import hr.kristiankliskovic.devcontrol.ui.userProfileSettings.UserProfileSettingsRoute
import hr.kristiankliskovic.devcontrol.ui.userProfileSettingsChangePassword.ChangePasswordRoute
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.data.network.pushNotifications.PushNotificationService
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.SeeAllPermissionsRoute
import hr.kristiankliskovic.devcontrol.ui.settings.SettingsRoute
import hr.kristiankliskovic.devcontrol.ui.triggerSettings.TriggerSettingsRoute
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.AddTriggerRoute
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_seeAll.SeeAllTriggersRoute
import hr.kristiankliskovic.devcontrol.ui.userProfileSettingsAddEmail.AddEmailRoute
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showIcons by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.route == MY_DEVICES_ROUTE
        }
    }

    val screenTitleResId by remember {
        derivedStateOf {
            when (navBackStackEntry?.destination?.route) {
                LOGIN_ROUTE -> {
                    null
                }
                REGISTER_ROUTE -> {
                    null
                }
                MY_DEVICES_ROUTE -> {
                    R.string.topBar_myDevices
                }
                DeviceControlsDestination.route -> {
                    R.string.topBar_devControls
                }
                ADMIN_PANEL_ROUTE -> {
                    R.string.topBar_adminPanelAllScreens
                }
                AdminPanelDeviceDestination.route -> {
                    R.string.topBar_adminPanelAllScreens
                }
                AddPermissionDestination.route -> {
                    R.string.topBar_adminPanelAllScreens
                }
                ChangeDeviceAdminDestination.route -> {
                    R.string.topBar_adminPanelAllScreens
                }
                seeAllPermissionsDestination.route -> {
                    R.string.topBar_adminPanelAllScreens
                }
                USER_PROFILE_ROUTE -> {
                    R.string.topBar_userSettings
                }
                CHANGE_PASSWORD_ROUTE -> {
                    R.string.topBar_changePassword
                }
                ADD_EMAIL_ROUTE -> {
                    R.string.topBar_addEmail
                }
                ADD_NEW_DEVICE_ROUTE -> {
                    R.string.topBar_addNewDevice
                }
                SETTINGS_ROUTE -> {
                    R.string.topBar_settings
                }
                TRIGGER_SETTINGS_ROUTE ->{
                    R.string.topBar_triggerSettings
                }
                ADD_TRIGGER_ROUTE -> {
                    R.string.topBar_addTrigger
                }
                SEE_ALL_TRIGGERS_ROUTE -> {
                    R.string.topBar_seeAllTriggers
                }
                else -> {
                    R.string.topBar_error
                }
            }

        }
    }

    val viewModel: MainScreenViewModel = getViewModel()
    val loggedIn = viewModel.loggedInUser.collectAsState()
    val userMessages = viewModel.userMessages.collectAsState()
    val connectedToWS =
        viewModel.connectedToWS.collectAsState() //could have been a viewState (all 3)

    viewModel.startWS()
    Scaffold(
        topBar = {
            TopBar(
                screenTitleResId = screenTitleResId,
                showIcons = showIcons,
                screenSubTitle = null,
                connectedToWS = connectedToWS.value,
                navigateToSettings = {
                    navController.navigate(SETTINGS_ROUTE)
                },
            )
        },
        modifier = Modifier.background(
            color = MaterialTheme.colorScheme.background
        )
    ) { padding ->
        Surface(
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
                        viewModel = getViewModel(),
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
                    DeviceControlsRoute(
                        viewModel = getViewModel {
                            parametersOf(
                                navBackStackEntry?.arguments?.getInt(DEVICE_CONTROLS_ID_KEY)
                                    ?: throw IllegalStateException("no deviceId sent")
                            )
                        }
                    )

                }
                composable(SETTINGS_ROUTE) {
                    SettingsRoute(
                        navigateToUserSettings = {
                            navController.navigate(USER_PROFILE_ROUTE)
                        },
                        navigateToAdminPanel = {
                            navController.navigate(ADMIN_PANEL_ROUTE)
                        },
                        navigateToTriggerSettings = {
                            navController.navigate(TRIGGER_SETTINGS_ROUTE)
                        }
                    )
                }
                composable(ADMIN_PANEL_ROUTE) {
                    AdminPanelHomeRoute(
                        viewModel = getViewModel(),
                        navigateToAdminPanelDevice = { route ->
                            navController.navigate(route)
                        },
                        navigateToAddNewDevice = {
                            navController.navigate(ADD_NEW_DEVICE_ROUTE)
                        }
                    )
                }
                composable(TRIGGER_SETTINGS_ROUTE) {
                    TriggerSettingsRoute(
                        navigateToAddTrigger = {
                            navController.navigate(ADD_TRIGGER_ROUTE)
                        },
                        navigateToSeeAllTriggers = {
                            navController.navigate(SEE_ALL_TRIGGERS_ROUTE)
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
                        viewModel = getViewModel {
                            parametersOf(
                                navBackStackEntry?.arguments?.getInt(ADMIN_PANEL_DEVICE_ID_KEY)
                                    ?: throw IllegalStateException("no deviceId sent")
                            )
                        },
                        navigateToChangeDeviceAdmin = { route ->
                            navController.navigate(route)
                        },
                        navigateToAddNewPermission = { route ->
                            navController.navigate(route)
                        },
                        navigateToSeeAllPermissions = { route ->
                            navController.navigate(route)
                        }
                    )
                }
                composable(ADD_NEW_DEVICE_ROUTE) {
                    AddNewDeviceRoute(
                        viewModel = getViewModel()
                    )
                }
                composable(
                    route = AddPermissionDestination.route,
                    arguments = listOf(navArgument(ADD_PERMISSION_ID_KEY) {
                        type = NavType.IntType
                    })
                ) {
                    AddPermissionRoute(
                        viewModel = getViewModel {
                            parametersOf(
                                navBackStackEntry?.arguments?.getInt(ADD_PERMISSION_ID_KEY)
                                    ?: throw IllegalStateException("no deviceId sent")
                            )
                        }
                    )
                }
                composable(
                    route = ChangeDeviceAdminDestination.route,
                    arguments = listOf(navArgument(CHANGE_DEVICE_ADMIN_ID_KEY) {
                        type = NavType.IntType
                    })
                ) {
                    ChangeDeviceAdminRoute(
                        viewModel = getViewModel {
                            parametersOf(
                                navBackStackEntry?.arguments?.getInt(CHANGE_DEVICE_ADMIN_ID_KEY)
                                    ?: throw IllegalStateException("no deviceId sent")
                            )
                        }
                    )
                }
                composable(
                    route = seeAllPermissionsDestination.route,
                    arguments = listOf(navArgument(SEE_ALL_PERMISSIONS_ID_KEY) {
                        type = NavType.IntType
                    })
                ) {
                    SeeAllPermissionsRoute(
                        viewModel = getViewModel {
                            parametersOf(
                                navBackStackEntry?.arguments?.getInt(SEE_ALL_PERMISSIONS_ID_KEY)
                                    ?: throw IllegalStateException("no deviceId sent")
                            )
                        }
                    )
                }
                composable(USER_PROFILE_ROUTE) {
                    UserProfileSettingsRoute(
                        navigateToChangePasswordScreen = {
                            navController.navigate(CHANGE_PASSWORD_ROUTE)
                        },
                        userProfileSettingsViewModel = get(),
                        navigateToAddEmailScreen = {
                            navController.navigate(ADD_EMAIL_ROUTE)
                        }
                    )
                }
                composable(CHANGE_PASSWORD_ROUTE) {
                    ChangePasswordRoute(
                        viewModel = getViewModel(),
                        navigateBackToUserSettings = {
                            navController.navigate(USER_PROFILE_ROUTE) {
                                popUpTo(USER_PROFILE_ROUTE)
                            }
                        }
                    )
                }
                composable(ADD_EMAIL_ROUTE) {
                    AddEmailRoute(
                        viewModel = getViewModel(),
                    )
                }
                composable(ADD_TRIGGER_ROUTE) {
                    AddTriggerRoute(
                        viewModel = getViewModel(),
                    )
                }
                composable(SEE_ALL_TRIGGERS_ROUTE) {
                    SeeAllTriggersRoute(
                        viewModel = getViewModel(),
                    )
                }
            }
        }
        when (loggedIn.value) {
            true -> {
                if (!viewModel.loggedInLocal) {
                    navController.navigate(MY_DEVICES_ROUTE) {
                        popUpTo(LOGIN_ROUTE) {
                            inclusive = true
                        }
                    }
                    viewModel.startWS()
                    viewModel.loggedInLocal = true
                }
            }
            false -> {
                if (viewModel.loggedInLocal) {
                    navController.navigate(LOGIN_ROUTE) {
                        launchSingleTop = true
                        popUpTo(MY_DEVICES_ROUTE) {
                            inclusive = true
                        }
                    }
                    viewModel.stopWS()
                    viewModel.loggedInLocal = false
                }
            }
            null -> {
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
    screenTitleResId: Int?,
    screenSubTitle: String?,
    connectedToWS: Boolean,
    navigateToSettings: () -> Unit,
) {
    if (screenTitleResId != null) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.topBarHeight)),
            ) {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.topBarHeight))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.connected_icon_foreground),
                        contentDescription = null,
                        modifier = Modifier
//                            .clip(CircleShape)
                            .background(colorResource(id = if (connectedToWS) R.color.WS_status_Online else R.color.WS_status_Offline)),
                    )
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.topBarHeight))
                ) {
                    Text(
                        text = stringResource(id = screenTitleResId),
                        fontSize = 30.sp,
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
                                Icons.Filled.Menu,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(dimensionResource(id = R.dimen.topBarHeight))
                                    .clickable {
                                        navigateToSettings()
                                    }
                            )
                        }
                    }
                }
            }
            Line()
        }
    }
    if (screenSubTitle != null) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.topBarSecundaryHeight)),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = screenSubTitle)
        }
    }
}

@Composable
fun Line(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.topBar_dividerLine_height))
            .background(colorResource(id = R.color.topBar_dividerLine))
    )
}
