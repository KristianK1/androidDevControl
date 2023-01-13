package hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.mock.getMockUsers
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.ChangeDeviceAdminViewState
import hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin.mapper.ChangeDeviceAdminMapperImpl
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.OutlineTextWrapper
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.TextListOption

@Composable
fun ChangeDeviceAdminRoute(
    viewModel: ChangeDeviceAdminViewModel,
) {
    val viewState by viewModel.viewState.collectAsState()

    ChangeDeviceAdminScreen(
        state = viewState,
        selectNewAdmin = { userId ->
            viewModel.changeDeviceAdmin(userId)
        }
    )
}

@Composable
fun ChangeDeviceAdminScreen(
    state: ChangeDeviceAdminViewState,
    selectNewAdmin: (Int) -> Unit,
) {
    var searchValue by remember { mutableStateOf("") }
    Column {
        OutlineTextWrapper(
            label = stringResource(id = R.string.addPermissionScreen_userSearchHint),
            onChange = {
                searchValue = it
            }
        )
        LazyColumn {
            items(state.users.filter { it.username.contains(searchValue, true) }) { user ->
                TextListOption(
                    text = "${user.username} (id: ${user.userId})",
                    onClick = {
                        selectNewAdmin(user.userId)
                    }
                )
            }
        }
    }
}
