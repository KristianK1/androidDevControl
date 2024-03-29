package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.mock.getDeviceMock
import hr.kristiankliskovic.devcontrol.mock.getMockUsers
import hr.kristiankliskovic.devcontrol.model.PermissionCategory
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission.mapper.AddPermissionMapperImpl
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.OutlineTextWrapper
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.TextListOption
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes

@Composable
fun AddPermissionRoute(
    viewModel: AddPermissionViewModel,
) {
    val viewState by viewModel.viewState.collectAsState()
    AddPermissionScreen(
        state = viewState,
        { userId, readOnly ->
            viewModel.addUserPermissionToDevice(userId, viewState.deviceId, readOnly)
        },
        { userId, groupId, readOnly ->
            viewModel.addUserPermissionToGroup(userId, viewState.deviceId, groupId, readOnly)
        },
        { userId, groupId, fieldId, readOnly ->
            viewModel.addUserPermissionToField(userId, viewState.deviceId, groupId, fieldId, readOnly)
        },
        { userId, complexGroupId, readOnly ->
            viewModel.addUserPermissionToComplexGroup(userId, viewState.deviceId, complexGroupId, readOnly)
        },
    )
}

@Composable
fun AddPermissionScreen(
    state: AddPermissionViewState,
    addDevicePermission: (Int, Boolean) -> Unit,
    addGroupPermission: (Int, Int, Boolean) -> Unit,
    addFieldPermission: (Int, Int, Int, Boolean) -> Unit,
    addComplexGroupPermission: (Int, Int, Boolean) -> Unit,
) {
    val scrollState = rememberScrollState()
    var userSelected by remember { mutableStateOf<AddPermissionUserViewState?>(null) }
    var permissionCategorySelected by remember { mutableStateOf(PermissionCategory.GROUP) }

    var groupSelected by remember { mutableStateOf<AddPermissionGroupViewState?>(null) }
    var fieldSelected by remember { mutableStateOf<AddPermissionFieldViewState?>(null) }
    var complexGroupSelected by remember { mutableStateOf<AddPermissionComplexGroupViewState?>(null) }
    var permissionIsWrite by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
    ) {
        Spacer(modifier = Modifier
            .height(dimensionResource(id = R.dimen.addPermissionScreen_top_bottom_screen_padding))
        )

        SelectUserDialog(
            users = state.users,
            userSelected = userSelected,
            selectValue = { userId ->
                userSelected = state.users.find { it.userId == userId }
            }
        )

        PermissionCategorySelector(
            permissionCategorySelected = permissionCategorySelected,
            onClick = {
                permissionCategorySelected = it
            }
        )

        if (permissionCategorySelected == PermissionCategory.GROUP) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                var dropDownMenuExpandedGroup by remember { mutableStateOf(false) }
                SelectedItem(
                    text = if (groupSelected == null) stringResource(id = R.string.addPermissionScreen_no_group_selected_text)
                    else "${groupSelected!!.groupName} (id:${groupSelected!!.groupId})",
                    onClick = {
                        dropDownMenuExpandedGroup = true
                    }
                )
                DropdownMenu(
                    expanded = dropDownMenuExpandedGroup,
                    onDismissRequest = {
                        dropDownMenuExpandedGroup = false
                    },
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    for (group in state.device.groups) {
                        DropdownMenuItem(
                            onClick = {
                                groupSelected = group
                                dropDownMenuExpandedGroup = false
                            },
                        ) {
                            Text(
                                text = "${group.groupName} (id:${group.groupId})"
                            )
                        }
                    }
                }
            }
        }
        if (permissionCategorySelected == PermissionCategory.FIELD) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                var dropDownMenuExpandedGroup by remember { mutableStateOf(false) }
                SelectedItem(
                    text = if (groupSelected == null) stringResource(id = R.string.addPermissionScreen_no_group_selected_text)
                    else "${groupSelected!!.groupName} (id:${groupSelected!!.groupId})",
                    onClick = {
                        dropDownMenuExpandedGroup = true
                    }
                )
                DropdownMenu(
                    expanded = dropDownMenuExpandedGroup,
                    onDismissRequest = {
                        dropDownMenuExpandedGroup = false
                    },
                ) {
                    for (group in state.device.groups) {
                        DropdownMenuItem(
                            onClick = {
                                if (groupSelected != group) {
                                    groupSelected = group
                                    fieldSelected = null
                                }
                                dropDownMenuExpandedGroup = false
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = "${group.groupName} (id:${group.groupId})"
                            )
                        }
                    }
                }
            }

            Box(
                contentAlignment = Alignment.Center
            ) {
                var dropDownMenuExpandedField by remember { mutableStateOf(false) }
                SelectedItem(
                    text = if (fieldSelected == null) stringResource(id = R.string.addPermissionScreen_no_field_selected_text)
                    else "${fieldSelected!!.fieldName} (id:${fieldSelected!!.fieldId})",
                    onClick = {
                        dropDownMenuExpandedField = true
                    }
                )
                DropdownMenu(
                    expanded = dropDownMenuExpandedField,
                    onDismissRequest = {
                        dropDownMenuExpandedField = false
                    }
                ) {
                    if (groupSelected != null) {
                        for (field in groupSelected!!.fields) {
                            DropdownMenuItem(
                                onClick = {
                                    fieldSelected = field
                                    dropDownMenuExpandedField = false
                                },
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(
                                    text = "${field.fieldName} (id:${field.fieldId})"
                                )
                            }
                        }
                    } else {
                        DropdownMenuItem(
                            onClick = {
                                dropDownMenuExpandedField = false
                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = stringResource(id = R.string.addPermissionScreen_no_field_selected_warning)
                            )
                        }
                    }
                }
            }
        }
        if (permissionCategorySelected == PermissionCategory.COMPLEX_GROUP) {
            Box(
//                modifier = Modifier
//                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                var dropDownMenuExpandedComplexGroup by remember { mutableStateOf(false) }
                SelectedItem(
                    text = if (complexGroupSelected == null) stringResource(id = R.string.addPermissionScreen_no_complex_group_selected_text)
                    else "${complexGroupSelected!!.complexGroupName} (id:${complexGroupSelected!!.complexGroupId})",
                    onClick = {
                        dropDownMenuExpandedComplexGroup = true
                    }
                )
                DropdownMenu(
                    expanded = dropDownMenuExpandedComplexGroup,
                    onDismissRequest = {
                        dropDownMenuExpandedComplexGroup = false
                    },
                ) {
                    for (complexGroup in state.device.complexGroups) {
                        DropdownMenuItem(
                            onClick = {
                                complexGroupSelected = complexGroup
                                dropDownMenuExpandedComplexGroup = false
                            }
                        ) {
                            Text(
                                text = "${complexGroup.complexGroupName} (id:${complexGroup.complexGroupId})"
                            )
                        }
                    }
                }
            }
        }
        ReadWriteSelector(
            state = permissionIsWrite,
            changeReadWriteState = {
                permissionIsWrite = it
            }
        )

        Text(
            text = stringResource(id = R.string.addPermissionScreen_addPermission),
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.addPermissionScreen_addPermission_text_margin))
                .clip(Shapes.small)
                .background(
                    color = MaterialTheme.colorScheme.primary
                ).clickable {
                    if (userSelected != null) {
                        if (permissionCategorySelected == PermissionCategory.DEVICE) {
                            addDevicePermission(userSelected!!.userId, !permissionIsWrite)
                        }
                        if (permissionCategorySelected == PermissionCategory.GROUP && groupSelected != null) {
                            addGroupPermission(userSelected!!.userId,
                                groupSelected!!.groupId,
                                !permissionIsWrite)
                        } else if (permissionCategorySelected == PermissionCategory.FIELD && fieldSelected != null) {
                            addFieldPermission(
                                userSelected!!.userId,
                                groupSelected!!.groupId,
                                fieldSelected!!.fieldId,
                                !permissionIsWrite)
                        } else if (permissionCategorySelected == PermissionCategory.COMPLEX_GROUP && complexGroupSelected != null) {
                            addComplexGroupPermission(userSelected!!.userId,
                                complexGroupSelected!!.complexGroupId,
                                !permissionIsWrite)
                        } else {
                            //TOAST
                        }
                    } else {
                        //TOAST
                    }
                }
                .padding(dimensionResource(id = R.dimen.addPermissionScreen_addPermission_text_padding))
        )

        Spacer(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.addPermissionScreen_top_bottom_screen_padding))
        )
    }
}


@Composable
fun SelectUserDialog(
    users: List<AddPermissionUserViewState>,
    userSelected: AddPermissionUserViewState?,
    selectValue: (Int) -> Unit,
) {

    var dialogOpen by remember { mutableStateOf(false) }
    var searchValue by remember { mutableStateOf("") }

    if (dialogOpen) {
        AlertDialog(
            onDismissRequest = {
                dialogOpen = false
            },
            buttons = {
                OutlineTextWrapper(
                    label = stringResource(id = R.string.addPermissionScreen_userSearchHint),
                    onChange = {
                        searchValue = it
                    }
                )
                LazyColumn {
                    items(users.filter { it.username.contains(searchValue, true) }) { user ->
                        TextListOption(
                            text = "${user.username} (id: ${user.userId})",
                            onClick = {
                                dialogOpen = false
                                selectValue(user.userId)
                            }
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.addPermissionScreen_dialog_padding))
                .clip(Shapes.small)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.6f)
                ),
            shape = RoundedCornerShape(5.dp),
            backgroundColor = MaterialTheme.colorScheme.background,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxHeight()
            .padding(dimensionResource(id = R.dimen.addPermissionScreen_user_button_margin))
            .clip(Shapes.small)
            .background(
                color = MaterialTheme.colorScheme.primary
            )
            .clickable { dialogOpen = true }
    ) {
        Text(
            text =
            if (userSelected != null) "${userSelected.username} (id: ${userSelected.userId})"
            else stringResource(id = R.string.addPermissionScreen_UserButton_startText),
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(id = R.dimen.addPermissionScreen_user_button_padding_horizontal),
                    vertical = dimensionResource(id = R.dimen.addPermissionScreen_user_button_padding_vertical)
                )
        )
    }
}

@Composable
fun PermissionCategorySelector(
    permissionCategorySelected: PermissionCategory,
    onClick: (PermissionCategory) -> Unit,
) {
    Column {
        PermissionCategoryView(
            selected = PermissionCategory.DEVICE == permissionCategorySelected,
            text = stringResource(id = R.string.addPermissionScreen_permission_category_device),
            onClick = {
                onClick(PermissionCategory.DEVICE)
            }
        )
        PermissionCategoryView(
            selected = PermissionCategory.GROUP == permissionCategorySelected,
            text = stringResource(id = R.string.addPermissionScreen_permission_category_group),
            onClick = {
                onClick(PermissionCategory.GROUP)
            }
        )
        PermissionCategoryView(
            selected = PermissionCategory.FIELD == permissionCategorySelected,
            text = stringResource(id = R.string.addPermissionScreen_permission_category_field),
            onClick = {
                onClick(PermissionCategory.FIELD)
            }
        )
        PermissionCategoryView(
            selected = PermissionCategory.COMPLEX_GROUP == permissionCategorySelected,
            text = stringResource(id = R.string.addPermissionScreen_permission_category_complexGroup),
            onClick = {
                onClick(PermissionCategory.COMPLEX_GROUP)
            }
        )
    }
}

@Composable
fun PermissionCategoryView(
    selected: Boolean,
    text: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.primary,
            )
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.addPermissionScreen_radiobutton_spacer_width)))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp,
        )
    }
}

@Composable
fun SelectedItem(
    text: String,
    onClick: () -> Unit,
) {
    Text(
        text = text,
        fontSize = 20.sp,
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.addPermissionScreen_SelectedItem_text_margin))
            .clip(Shapes.small)
            .background(
                color = MaterialTheme.colorScheme.primary
            ).clickable {
                onClick()
            }
            .padding(dimensionResource(id = R.dimen.addPermissionScreen_SelectedItem_text_padding))
    )
}

@Composable
fun ReadWriteSelector(
    state: Boolean,
    changeReadWriteState: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.addPermissionScreen_readWrite_radioButtons_margin))
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    changeReadWriteState(false)
                },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RadioButton(
                selected = !state,
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary,
                    unselectedColor = MaterialTheme.colorScheme.primary,
                )
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.addPermissionScreen_radiobutton_spacer_width)))
            Text(
                text = stringResource(id = R.string.addPermissionScreen_read_permission_label),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp,
            )
        }
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.addPermissionScreen_radiobutton_spacer_width)))
        Row(
            modifier = Modifier
                .clickable {
                    changeReadWriteState(true)
                },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RadioButton(
                selected = state,
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary,
                    unselectedColor = MaterialTheme.colorScheme.primary,
                )
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.addPermissionScreen_radiobutton_spacer_width)))
            Text(
                text = stringResource(id = R.string.addPermissionScreen_write_permission_label),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp,
            )
        }
    }

}

@Preview
@Composable
fun PreviewAddPermissionScreen() {
    val state = AddPermissionMapperImpl().toAddPermissionViewState(
        device = getDeviceMock(),
        users = getMockUsers(),
    )
    AddPermissionScreen(
        state = state,
        { _, _ ->

        },
        { _, _, _ ->

        },
        { _, _, _, _ ->

        },
        { _, _, _ ->

        },
    )
}
