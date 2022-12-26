package hr.kristiankliskovic.devcontrol.ui.adminPanelListOfDevices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.mock.getAdminPanelHomeMock
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.TextListOption

@Composable
fun AdminPanelHomeRoute(
    viewState: AdminPanelHomeViewState,
) {
    AdminPanelHomeScreen(
        viewState = viewState,
        onClick = {

        },
        addNew = {

        }
    )
}

@Composable
fun AdminPanelHomeScreen(
    viewState: AdminPanelHomeViewState,
    onClick: (Int) -> Unit,
    addNew: () -> Unit,
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(viewState.devices) { device ->
                TextListOption(
                    text = device.deviceName,
                    onClick = {
                        onClick(device.deviceId)
                    }
                )
                Line()
            }
        }
        FloatingActionButton(
            onClick = addNew,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.fabPadding)),
        ) {
            Icon(Icons.Filled.Add, "")
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
            .height(dimensionResource(id = R.dimen.adminPanelHomeScreen_dividerLine_height))
            .background(colorResource(id = R.color.adminPanelHomeScreen_dividerLine))
    )
}

@Preview
@Composable
fun PreviewAdminPanelHomeScreen() {
    AdminPanelHomeScreen(
        viewState = getAdminPanelHomeMock(),
        onClick = {

        },
        addNew = {

        }
    )
}
