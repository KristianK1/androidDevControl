package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
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
import hr.kristiankliskovic.devcontrol.mock.getDeviceListMockData
import hr.kristiankliskovic.devcontrol.mock.getDeviceMock
import hr.kristiankliskovic.devcontrol.mock.getMockUsers
import hr.kristiankliskovic.devcontrol.model.PermissionCategory
import hr.kristiankliskovic.devcontrol.model.User
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.OutlineTextWrapper
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.TextListOption
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes
import kotlinx.coroutines.selects.select

@Composable
fun AddPermissionScreen(
    state: AddPermissionViewState,
) {
    val scrollState = rememberScrollState()
    var userSelected by remember { mutableStateOf<User?>(null) }
    var permissionCategorySelected by remember { mutableStateOf(PermissionCategory.DEVICE) }

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
                permissionCategorySelected = it;
            }
        )
    }

    Spacer(modifier = Modifier
        .height(dimensionResource(id = R.dimen.addPermissionScreen_top_bottom_screen_padding))
    )
}


@Composable
fun SelectUserDialog(
    users: List<User>,
    userSelected: User?,
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
                        searchValue = it;
                    }
                )
                LazyColumn {
                    items(users.filter { it.username.contains(searchValue) }) { user ->
                        TextListOption(
                            text = "${user.username}(id: ${user.userId})",
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
            if (userSelected != null) "${userSelected.username}(id: ${userSelected.userId})"
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


@Preview
@Composable
fun PreviewAddPermissionScreen() {
    val state = AddPermissionViewState(
        users = getMockUsers(),
        device = getDeviceMock(),
    )
    AddPermissionScreen(state = state)
}
