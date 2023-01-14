package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R

@Composable
fun SeeAllPermissionsRoute(
    viewModel: SeeAllPermissionsViewModel,
) {
    val viewState by viewModel.viewState.collectAsState()
    SeeAllPermissionsScreen(
        viewState = viewState,
        deleteDevicePermission = { deviceId: Int, userId: Int ->

        },
        deleteGroupPermission = { deviceId: Int, groupId: Int, userId: Int ->

        },

        deleteFieldPermission = { deviceId: Int, groupId: Int, fieldId: Int, userId: Int ->

        },
        deleteComplexGroupPermission = { deviceId: Int, complexGroupId: Int, userId: Int ->

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
        for (devRight in viewState.permissions) {
            UserPermissionLabel(
                username = devRight.username,
                isWrite = !devRight.readOnly,
                onClick = {

                }
            )
        }
//        Text(
//            text = stringResource(id = R.string.seeAllPermissions_groupPermissions_title)
//        )
//        for (group in viewState.groupPermissions) {
//            Column(
//                modifier = Modifier
//                    .border(
//                        color = colorResource(id = R.color.seeAllPermissionScreen_rights_borders),
//                        width = dimensionResource(id = R.dimen.seeAllPermissionScreen_rights_borders)
//                    )
//            ) {
//                for (groupRight in group.permissions)
//                    UserPermissionLabel(
//                        username = groupRight.username,
//                        isWrite = !groupRight.readOnly,
//                        onClick = {
//
//                        }
//                    )
//                Text(
//                    text = stringResource(id = R.string.seeAllPermissions_fieldPermissions_title)
//                )
//                for (field in group.fields) {
//                    Column(
//                        modifier = Modifier
//                            .border(
//                                color = colorResource(id = R.color.seeAllPermissionScreen_rights_borders),
//                                width = dimensionResource(id = R.dimen.seeAllPermissionScreen_rights_borders)
//                            )
//                    ) {
//                        for (fieldRight in field.permissions)
//                            UserPermissionLabel(
//                                username = fieldRight.username,
//                                isWrite = !fieldRight.readOnly,
//                                onClick = {
//
//                                }
//                            )
//                    }
//                }
//            }
//        }
        ComplexGroupPermissions(
            complexGroupViewStates = viewState.complexGroupPermissions,
            deleteComplexGroupPermission = { complexGroupId: Int, userId: Int ->
                deleteComplexGroupPermission(viewState.deviceId, complexGroupId, userId)
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
        Text(
            text = stringResource(id = R.string.seeAllPermissions_complexGroupPermissions_title),
            fontSize = 20.sp,
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

@Composable
fun ComplexGroupPermission(
    complexGroupViewState: UserPermissionToComplexGroupViewState,
    deleteComplexGroupPermission: (Int, Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.seeAllPermissionScreen_padding_for_border))
            .border(
                color = colorResource(id = R.color.seeAllPermissionScreen_rights_borders),
                width = dimensionResource(id = R.dimen.seeAllPermissionScreen_rights_borders)
            )
            .padding(dimensionResource(id = R.dimen.seeAllPermissionScreen_padding_between_boxes))
            .fillMaxWidth()
    ) {
        Text(
            text = "${stringResource(id = R.string.seeAllPermissionsScreen_fieldPermission_complexGroupName_title)} ${complexGroupViewState.complexGroupName}",
            fontSize = 25.sp
        )
        if (complexGroupViewState.permissions.isEmpty()) {
            Text(
                text = stringResource(id = R.string.seeAllPermissionsScreen_noPermission_field),
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
    fieldViewState: UserPermissionToFieldViewState,
    deleteFieldPermission: (Int, Int) -> Unit,
) {
    Text(
        text = "${stringResource(id = R.string.seeAllPermissionsScreen_fieldPermission_fieldName_title)} ${fieldViewState.fieldName}",
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

@Composable
fun UserPermissionLabel(
    username: String,
    isWrite: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.seeAllPermissionScreen_userPermissionComponent_padding)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${stringResource(id = R.string.seeAllPermissionsScreen_permission_username_title)} " +
                    "$username " +
                    "${stringResource(id = R.string.seeAllPermissionsScreen_permission_permissionType_title)} " +
                    if (isWrite) stringResource(id = R.string.seeAllPermissions_write_permission_label) else stringResource(
                        id = R.string.seeAllPermissions_read_permission_label),
            fontSize = 25.sp,
        )
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = null,
            modifier = Modifier
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
