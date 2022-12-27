package hr.kristiankliskovic.devcontrol.ui.deviceControls

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceComplexGroup
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceGroup
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.mock.getDevControlsMock

@Composable
fun DeviceControlsRoute(){
    val data by remember {
        mutableStateOf(getDevControlsMock())
    }
    DeviceControlsScreen(item = data)
}

@Composable
fun DeviceControlsScreen(
    item: DeviceControlsViewState,
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
        LazyColumn{
            items(item.groupsViewStates) { groupViewState ->
                DeviceGroup(
                    item = groupViewState,
                    onChange = { _, _, _ ->

                    })
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.deviceControlsScreen_spacer_height_between_groups)))
            }
            items(item.complexGroupsViewStates) { complexGroupViewState ->
                DeviceComplexGroup(
                    item = complexGroupViewState,
                    changeComplexGroupState = { _, _ ->

                    },
                    onChange = { _, _, _, _ ->

                    }
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.deviceControlsScreen_spacer_height_between_groups)))
            }
        }
    }
}

@Preview
@Composable
fun PreviewDeviceControlsScreen() {
    DeviceControlsScreen(item = getDevControlsMock())
}
