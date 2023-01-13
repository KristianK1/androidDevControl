package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
            Log.i("perms", "one")
            viewModel.addUserPermissionToDevice(userId, viewState.deviceId, readOnly)
        },
        { userId, groupId, readOnly ->
            Log.i("perms", "one")
            viewModel.addUserPermissionToGroup(userId, viewState.deviceId, groupId, readOnly)
        },
        { userId, groupId, fieldId, readOnly ->
            Log.i("perms", "one")
            viewModel.addUserPermissionToField(userId, viewState.deviceId, groupId, fieldId, readOnly)
        },
        { userId, complexGroupId, readOnly ->
            Log.i("perms", "one")
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
                var dropDownMenuExpanded_group by remember { mutableStateOf(false) }
                SelectedItem(
                    text = if (groupSelected == null) stringResource(id = R.string.addPermissionScreen_no_group_selected_text)
                    else "${groupSelected!!.groupName} (id:${groupSelected!!.groupId})",
                    onClick = {
                        dropDownMenuExpanded_group = true
                    }
                )
                DropdownMenu(
                    expanded = dropDownMenuExpanded_group,
                    onDismissRequest = {
                        dropDownMenuExpanded_group = false
                    },
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    for (group in state.device.groups) {
                        DropdownMenuItem(
                            onClick = {
                                groupSelected = group
                                dropDownMenuExpanded_group = false
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
                var dropDownMenuExpanded_group by remember { mutableStateOf(false) }
                SelectedItem(
                    text = if (groupSelected == null) stringResource(id = R.string.addPermissionScreen_no_group_selected_text)
                    else "${groupSelected!!.groupName} (id:${groupSelected!!.groupId})",
                    onClick = {
                        dropDownMenuExpanded_group = true
                    }
                )
                DropdownMenu(
                    expanded = dropDownMenuExpanded_group,
                    onDismissRequest = {
                        dropDownMenuExpanded_group = false
                    },
                ) {
                    for (group in state.device.groups) {
                        DropdownMenuItem(
                            onClick = {
                                if (groupSelected != group) {
                                    groupSelected = group
                                    fieldSelected = null
                                }
                                dropDownMenuExpanded_group = false
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
                var dropDownMenuExpanded_field by remember { mutableStateOf(false) }
                SelectedItem(
                    text = if (fieldSelected == null) stringResource(id = R.string.addPermissionScreen_no_field_selected_text)
                    else "${fieldSelected!!.fieldName} (id:${fieldSelected!!.fieldId})",
                    onClick = {
                        dropDownMenuExpanded_field = true
                    }
                )
                DropdownMenu(
                    expanded = dropDownMenuExpanded_field,
                    onDismissRequest = {
                        dropDownMenuExpanded_field = false
                    }
                ) {
                    if (groupSelected != null) {
                        for (field in groupSelected!!.fields) {
                            DropdownMenuItem(
                                onClick = {
                                    fieldSelected = field
                                    dropDownMenuExpanded_field = false
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
                                dropDownMenuExpanded_field = false
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
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                var dropDownMenuExpanded_complexGroup by remember { mutableStateOf(false) }
                SelectedItem(
                    text = if (complexGroupSelected == null) stringResource(id = R.string.addPermissionScreen_no_complex_group_selected_text)
                    else "${complexGroupSelected!!.complexGroupName} (id:${complexGroupSelected!!.complexGroupId})",
                    onClick = {
                        dropDownMenuExpanded_complexGroup = true
                    }
                )
                DropdownMenu(
                    expanded = dropDownMenuExpanded_complexGroup,
                    onDismissRequest = {
                        dropDownMenuExpanded_complexGroup = false
                    },
                ) {
                    for (complexGroup in state.device.complexGroups) {
                        DropdownMenuItem(
                            onClick = {
                                complexGroupSelected = complexGroup
                                dropDownMenuExpanded_complexGroup = false
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
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.addPermissionScreen_addPermission_text_margin))
                .clip(Shapes.small)
                .background(colorResource(id = R.color.addPermissionScreen_addPermission_button_background))
                .clickable {
                    if (userSelected != null) {
                        if (permissionCategorySelected == PermissionCategory.DEVICE) {
                            addDevicePermission(userSelected!!.userId, permissionIsWrite)
                        }
                        if (permissionCategorySelected == PermissionCategory.GROUP && groupSelected != null) {
                            addGroupPermission(userSelected!!.userId,
                                groupSelected!!.groupId,
                                permissionIsWrite)
                        } else if (permissionCategorySelected == PermissionCategory.FIELD && fieldSelected != null) {
                            addFieldPermission(
                                userSelected!!.userId,
                                groupSelected!!.groupId,
                                fieldSelected!!.fieldId,
                                permissionIsWrite)
                        } else if (permissionCategorySelected == PermissionCategory.COMPLEX_GROUP && complexGroupSelected != null) {
                            addComplexGroupPermission(userSelected!!.userId,
                                complexGroupSelected!!.complexGroupId,
                                permissionIsWrite)
                        } else {
                            //TOAST
                        }
                    } else {
                        //TOAST
                    }
                }
                .padding(dimensionResource(id = R.dimen.addPermissionScreen_addPermission_text_padding))
        )

        Spacer(modifier = Modifier
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
                .padding(dimensionResource(id = R.dimen.addPermissionScreen_dialog_padding)),
            shape = Shapes.small,
            backgroundColor = Color.White,
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
            .background(colorResource(id = R.color.addPermissionScreen_user_button))
            .clickable { dialogOpen = true }
    ) {
        Text(
            text =
            if (userSelected != null) "${userSelected.username} (id: ${userSelected.userId})"
            else stringResource(id = R.string.addPermissionScreen_UserButton_startText),
            fontSize = 20.sp,
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
            onClick = null
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.addPermissionScreen_radiobutton_spacer_width)))
        Text(
            text = text
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
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.addPermissionScreen_SelectedItem_text_margin))
            .clip(Shapes.small)
            .background(colorResource(id = R.color.addPermissionScreen_SelectedItem_background))
            .clickable {
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
                    changeReadWriteState(true)
                },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RadioButton(
                selected = !state,
                onClick = null
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.addPermissionScreen_radiobutton_spacer_width)))
            Text(
                text = stringResource(id = R.string.addPermissionScreen_read_permission_label)
            )
        }
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.addPermissionScreen_radiobutton_spacer_width)))
        Row(
            modifier = Modifier
                .clickable {
                    changeReadWriteState(false)
                },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RadioButton(
                selected = state,
                onClick = null
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.addPermissionScreen_radiobutton_spacer_width)))
            Text(
                text = stringResource(id = R.string.addPermissionScreen_write_permission_label)
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
