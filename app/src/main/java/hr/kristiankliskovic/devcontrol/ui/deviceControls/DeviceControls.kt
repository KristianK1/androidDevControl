package hr.kristiankliskovic.devcontrol.ui.deviceControls

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.mock.getMockDeviceComplexGroupViewState
import hr.kristiankliskovic.devcontrol.mock.getMockDeviceGroupViewState
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceComplexGroup
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceComplexGroupStateViewState
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceGroup
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceGroupViewState


@Composable
fun DeviceControlsScreen(
    item: DeviceControlsScreenViewState,
) {
    Column {
        Text(
            text = item.deviceName,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.LightGray,
                ),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color.Black)
        )
        LazyColumn {
            items(item.groupsViewStates) { groupViewState ->
                DeviceGroup(
                    item = groupViewState,
                    onChange = { _, _, _ ->

                    })
            }
            items(item.complexGroupsViewStates) { complexGroupViewState ->
                DeviceComplexGroup(
                    item = complexGroupViewState,
                    changeComplexGroupState = { _, _ ->

                    },
                    onChange = { _, _, _, _ ->

                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDeviceControlsScreen() {
    val item = DeviceControlsScreenViewState(
        deviceId = 0,
        deviceName = "dEV1",
        groupsViewStates = listOf(
            getMockDeviceGroupViewState(),
            getMockDeviceGroupViewState(),
            getMockDeviceGroupViewState()),
        complexGroupsViewStates = listOf(
            getMockDeviceComplexGroupViewState(),
            getMockDeviceComplexGroupViewState(),
            getMockDeviceComplexGroupViewState()),
        deviceOnline = true,
    )
    DeviceControlsScreen(item = item)
}

