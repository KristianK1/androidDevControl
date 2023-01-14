package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes

@Composable
fun SeeAllPermissionsRoute(
    viewModel: SeeAllPermissionsViewModel,
) {
    val viewState by viewModel.viewState.collectAsState()
    SeeAllPermissionsScreen(
        viewState = viewState,
        deleteDevicePermission = { deviceId: Int, userId: Int ->
            viewModel.deleteUserPermissionToDevice(
                userId = userId,
                deviceId = deviceId,
            )
        },
        deleteGroupPermission = { deviceId: Int, groupId: Int, userId: Int ->
            viewModel.deleteUserPermissionToGroup(
                userId = userId,
                deviceId = deviceId,
                groupId = groupId,
            )
        },
        deleteFieldPermission = { deviceId: Int, groupId: Int, fieldId: Int, userId: Int ->
            viewModel.deleteUserPermissionToField(
                userId = userId,
                deviceId = deviceId,
                groupId = groupId,
                fieldId = fieldId,
            )
        },
        deleteComplexGroupPermission = { deviceId: Int, complexGroupId: Int, userId: Int ->
            viewModel.deleteUserPermissionToComplexGroup(
                userId = userId,
                deviceId = deviceId,
                complexGroupId = complexGroupId,
            )
        },
    )
}

@Composable
fun SeeAllPermissionsScreen(
    viewState: SeeAllPermissionsViewState,
    deleteDevicePermission: (Int, Int) -> Unit,
    deleteGroupPermission: (Int, Int, Int) -> Unit,
    deleteFieldPermission: (Int, Int, Int, Int) -> Unit,
    deleteComplexGroupPermission: (Int, Int, Int) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
    ) {
        Text(
            text = stringResource(id = R.string.seeAllPermissions_devicePermissions_title)
        )
        if (viewState.permissions.isEmpty()) {
            Text(
                text = stringResource(id = R.string.seeAllPermissionsScreen_noPermission_device),
                fontSize = 15.sp
            )
        } else {
            for (permission in viewState.permissions) {
                UserPermissionLabel(
                    username = permission.username,
                    isWrite = !permission.readOnly,
                    onClick = {
                        deleteDevicePermission(viewState.deviceId, permission.userId)
                    }
                )
            }
        }

        GroupPermissions(
            groupViewStates = viewState.groupPermissions,
            deleteGroupPermission = { groupId: Int, userId: Int ->
                deleteGroupPermission(viewState.deviceId, groupId, userId)
            },
            deleteFieldPermission = { groupId: Int, fieldId: Int, userId: Int ->
                deleteFieldPermission(viewState.deviceId, groupId, fieldId, userId)
            }
        )
        ComplexGroupPermissions(
            complexGroupViewStates = viewState.complexGroupPermissions,
            deleteComplexGroupPermission = { complexGroupId: Int, userId: Int ->
                deleteComplexGroupPermission(viewState.deviceId, complexGroupId, userId)
            }
        )
    }
}


@Composable
fun GroupPermissions(
    groupViewStates: List<UserPermissionToGroupViewState>,
    deleteGroupPermission: (Int, Int) -> Unit,
    deleteFieldPermission: (Int, Int, Int) -> Unit,
) {
    if (groupViewStates.isNotEmpty()) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.seeAllPermissionScreen_padding_for_border))
                .clip(Shapes.small)
                .border(
                    color = colorResource(id = R.color.seeAllPermissionScreen_rights_borders),
                    width = dimensionResource(id = R.dimen.seeAllPermissionScreen_rights_borders)
                )
                .background(
                    color = colorResource(id = R.color.seeAllPermissionsScreen_groups_background)
                )
                .padding(dimensionResource(id = R.dimen.seeAllPermissionScreen_padding_between_boxes))
        ) {
            Text(
                text = stringResource(id = R.string.seeAllPermissions_groupPermissions_title),
                fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            for (groupViewState in groupViewStates) {
                GroupPermission(
                    groupViewState = groupViewState,
                    deleteGroupPermission = deleteGroupPermission,
                    deleteFieldPermission = deleteFieldPermission,
                )
            }
        }
    }
}

@Composable
fun GroupPermission(
    groupViewState: UserPermissionToGroupViewState,
    deleteGroupPermission: (Int, Int) -> Unit,
    deleteFieldPermission: (Int, Int, Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.seeAllPermissionScreen_padding_for_border))
            .clip(Shapes.small)
            .border(
                color = colorResource(id = R.color.seeAllPermissionScreen_rights_borders),
                width = dimensionResource(id = R.dimen.seeAllPermissionScreen_rights_borders)
            )
            .background(
                color = colorResource(id = R.color.seeAllPermissionsScreen_group_background)
            )
            .padding(dimensionResource(id = R.dimen.seeAllPermissionScreen_padding_between_boxes))
            .fillMaxWidth()
    ) {
        Text(
            text = "${stringResource(id = R.string.seeAllPermissionsScreen_fieldPermission_groupName_title)}\n${groupViewState.groupName}",
            fontSize = 25.sp
        )
        if (groupViewState.permissions.isEmpty()) {
            Text(
                text = stringResource(id = R.string.seeAllPermissionsScreen_noPermission_group),
                fontSize = 15.sp
            )
        } else {
            for (permission in groupViewState.permissions) {
                UserPermissionLabel(
                    username = permission.username,
                    isWrite = !permission.readOnly,
                    onClick = {
                        deleteGroupPermission(groupViewState.groupId, permission.userId)
                    }
                )
            }
        }
        FieldPermissions(
            fieldViewStates = groupViewState.fields,
            deleteFieldPermission = { fieldId: Int, userId: Int ->
                deleteFieldPermission(groupViewState.groupId, fieldId, userId)
            }
        )
    }
}

@Composable
fun ComplexGroupPermissions(
    complexGroupViewStates: List<UserPermissionToComplexGroupViewState>,
    deleteComplexGroupPermission: (Int, Int) -> Unit,
) {
    if (complexGroupViewStates.isNotEmpty()) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.seeAllPermissionScreen_padding_for_border))
                .clip(Shapes.small)
                .border(
                    color = colorResource(id = R.color.seeAllPermissionScreen_rights_borders),
                    width = dimensionResource(id = R.dimen.seeAllPermissionScreen_rights_borders)
                )
                .background(
                    color = colorResource(id = R.color.seeAllPermissionsScreen_groups_background)
                )
                .padding(dimensionResource(id = R.dimen.seeAllPermissionScreen_padding_between_boxes))
        ) {
            Text(
                text = stringResource(id = R.string.seeAllPermissions_complexGroupPermissions_title),
                fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            for (complexGroupViewState in complexGroupViewStates) {
                ComplexGroupPermission(
                    complexGroupViewState = complexGroupViewState,
                    deleteComplexGroupPermission = { complexGroupId: Int, userId: Int ->
                        deleteComplexGroupPermission(complexGroupId, userId)
                    }
                )
            }
        }
    }
}

@Composable
fun ComplexGroupPermission(
    complexGroupViewState: UserPermissionToComplexGroupViewState,
    deleteComplexGroupPermission: (Int, Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.seeAllPermissionScreen_padding_for_border))
            .clip(Shapes.small)
            .border(
                color = colorResource(id = R.color.seeAllPermissionScreen_rights_borders),
                width = dimensionResource(id = R.dimen.seeAllPermissionScreen_rights_borders)
            )
            .background(
                color = colorResource(id = R.color.seeAllPermissionsScreen_group_background)
            )
            .padding(dimensionResource(id = R.dimen.seeAllPermissionScreen_padding_between_boxes))
            .fillMaxWidth()
    ) {
        Text(
            text = "${stringResource(id = R.string.seeAllPermissionsScreen_fieldPermission_complexGroupName_title)}\n${complexGroupViewState.complexGroupName}",
            fontSize = 25.sp
        )
        if (complexGroupViewState.permissions.isEmpty()) {
            Text(
                text = stringResource(id = R.string.seeAllPermissionsScreen_noPermission_complexGroup),
                fontSize = 15.sp
            )
        } else {
            for (permission in complexGroupViewState.permissions) {
                UserPermissionLabel(
                    username = permission.username,
                    isWrite = !permission.readOnly,
                    onClick = {
                        deleteComplexGroupPermission(complexGroupViewState.complexGroupId,
                            permission.userId)
                    }
                )
            }
        }
    }
}

@Composable
fun FieldPermissions(
    fieldViewStates: List<UserPermissionToFieldViewState>,
    deleteFieldPermission: (Int, Int) -> Unit,
) {
    if (fieldViewStates.isNotEmpty()) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.seeAllPermissionScreen_padding_for_border))
                .clip(Shapes.small)
                .border(
                    color = colorResource(id = R.color.seeAllPermissionScreen_rights_borders),
                    width = dimensionResource(id = R.dimen.seeAllPermissionScreen_rights_borders)
                )
                .background(
                    color = colorResource(id = R.color.seeAllPermissionsScreen_fields_background)
                )
                .padding(dimensionResource(id = R.dimen.seeAllPermissionScreen_padding_between_boxes))
        ) {
            Text(
                text = stringResource(id = R.string.seeAllPermissions_fieldPermissions_title),
                fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            for (fieldViewState in fieldViewStates) {
                FieldPermission(
                    fieldViewState = fieldViewState,
                    deleteFieldPermission = { fieldId: Int, userId: Int ->
                        deleteFieldPermission(fieldId, userId)
                    }
                )
            }
        }
    }
}

@Composable
fun FieldPermission(
    fieldViewState: UserPermissionToFieldViewState,
    deleteFieldPermission: (Int, Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.seeAllPermissionScreen_padding_for_border))
            .clip(Shapes.small)
            .border(
                color = colorResource(id = R.color.seeAllPermissionScreen_rights_borders),
                width = dimensionResource(id = R.dimen.seeAllPermissionScreen_rights_borders)
            )
            .background(
                color = colorResource(id = R.color.seeAllPermissionsScreen_field_background)
            )
            .padding(dimensionResource(id = R.dimen.seeAllPermissionScreen_padding_between_boxes))
            .fillMaxWidth()
    ) {
        Text(
            text = "${stringResource(id = R.string.seeAllPermissionsScreen_fieldPermission_fieldName_title)} ${fieldViewState.fieldName} (${fieldViewState.fieldType})",
            fontSize = 20.sp
        )
        if (fieldViewState.permissions.isEmpty()) {
            Text(
                text = stringResource(id = R.string.seeAllPermissionsScreen_noPermission_field),
                fontSize = 15.sp
            )
        } else {
            for (permission in fieldViewState.permissions) {
                UserPermissionLabel(
                    username = permission.username,
                    isWrite = !permission.readOnly,
                    onClick = {
                        deleteFieldPermission(fieldViewState.fieldId, permission.userId)
                    }
                )
            }
        }
    }
}

@Composable
fun UserPermissionLabel(
    username: String,
    isWrite: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.seeAllPermissionScreen_padding_between_userPermissions))
            .clip(Shapes.small)
            .border(
                color = colorResource(id = R.color.seeAllPermissionScreen_rights_borders),
                width = dimensionResource(id = R.dimen.seeAllPermissionScreen_userPermission_border)
            )
            .padding(dimensionResource(id = R.dimen.seeAllPermissionScreen_userPermissionComponent_padding)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${stringResource(id = R.string.seeAllPermissionsScreen_permission_username_title)} " +
                    "$username\n" +
                    "${stringResource(id = R.string.seeAllPermissionsScreen_permission_permissionType_title)} " +
                    if (isWrite) stringResource(id = R.string.seeAllPermissions_write_permission_label) else stringResource(
                        id = R.string.seeAllPermissions_read_permission_label),
            fontSize = 25.sp,
        )
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onClick()
                }
        )
    }
}

@Preview
@Composable
fun PreviewSeeAllPermissionsScreen() {

}
