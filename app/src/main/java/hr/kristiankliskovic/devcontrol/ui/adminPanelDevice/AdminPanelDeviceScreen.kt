package hr.kristiankliskovic.devcontrol.ui.adminPanelDevice

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.navigation.ChangeDeviceAdminDestination
import hr.kristiankliskovic.devcontrol.navigation.AddPermissionDestination
import hr.kristiankliskovic.devcontrol.navigation.seeAllPermissionsDestination
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.TextListOption
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes

@Composable
fun AdminPanelDeviceRoute(
    viewModel: AdminPanelDeviceViewModel,
    navigateToChangeDeviceAdmin: (String) -> Unit,
    navigateToSeeAllPermissions: (String) -> Unit,
    navigateToAddNewPermission: (String) -> Unit,
) {

    val viewState = viewModel.state

    AdminPanelDeviceScreen(
        viewState,
        navigateToChangeDeviceAdmin = navigateToChangeDeviceAdmin,
        navigateToSeeAllPermissions = navigateToSeeAllPermissions,
        navigateToAddNewPermission = navigateToAddNewPermission,
        deleteDevice = {
            viewModel.deleteDevice()
        }
    )
}

@Composable
fun AdminPanelDeviceScreen(
    viewState: AdminPanelDeviceViewState,
    navigateToChangeDeviceAdmin: (String) -> Unit,
    navigateToSeeAllPermissions: (String) -> Unit,
    deleteDevice: () -> Unit,
    navigateToAddNewPermission: (String) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextListOption(
            text = stringResource(id = R.string.adminPanelDevice_changeAdmin),
            onClick = {
                navigateToChangeDeviceAdmin(
                    ChangeDeviceAdminDestination.createNavigationRoute(viewState.deviceId)
                )
            }
        )
        TextListOption(
            text = stringResource(id = R.string.adminPanelDevice_seeAllPermissions),
            onClick = {
                navigateToSeeAllPermissions(
                    seeAllPermissionsDestination.createNavigationRoute(viewState.deviceId)
                )
            }
        )
        TextListOption(
            text = stringResource(id = R.string.adminPanelDevice_addPermission),
            onClick = {
                navigateToAddNewPermission(
                    AddPermissionDestination.createNavigationRoute(viewState.deviceId)
                )
            }
        )
        TextListOption(
            text = stringResource(id = R.string.adminPanelDevice_deleteDevice),
            onClick = deleteDevice
        )
    }
}


@Composable
fun ConfirmDialog(
    onClick: () -> Unit,
) {
    var dialogOpen by remember {
        mutableStateOf(false)
    }

    if (dialogOpen) {
        AlertDialog(onDismissRequest = {
            dialogOpen = false
        },
            buttons = {
                Column(
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Row(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.dialog_confirm_button_margin))
                            .fillMaxWidth(),
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clip(Shapes.small)
                                .clickable {
                                    dialogOpen = false
                                }
                                .background(colorResource(id = R.color.adminPanelDevice_deleteDeviceButton_background))
                                .padding(dimensionResource(id = R.dimen.adminPanelDevice_deleteDeviceButton_padding))
                        ) {
                            Text(
                                text = stringResource(id = R.string.adminPanelDevice_deleteDeviceConfirm),
                                fontSize = 20.sp
                            )
                        }
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clip(Shapes.small)
                                .clickable {
                                    dialogOpen = false
                                }
                                .background(colorResource(id = R.color.adminPanelDevice_deleteDeviceButton_background))
                                .padding(dimensionResource(id = R.dimen.adminPanelDevice_deleteDeviceButton_padding))
                        ) {
                            Text(
                                text = stringResource(id = R.string.adminPanelDevice_deleteDeviceCancel),
                                fontSize = 20.sp
                            )
                        }
                    }
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.adminPanelDevice_confirmDialog_padding)),
            shape = Shapes.small,
            backgroundColor = Color.White,
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true))
    }
    TextListOption(
        text = stringResource(id = R.string.adminPanelDevice_deleteDevice),
        onClick = onClick
    )
}
