package hr.kristiankliskovic.devcontrol.ui.deviceControls

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceComplexGroup
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceGroup
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.DeviceComplexGroup
import hr.kristiankliskovic.devcontrol.model.RGBValue

@Composable
fun DeviceControlsRoute(
    viewModel: DeviceControlsViewModel,
) {
    val viewState by viewModel.deviceControlsViewState.collectAsState()

    if (viewState == null) {
        Text(
            text = stringResource(id = R.string.deviceControlsScreen_empty_screen_message),
            fontSize = 30.sp,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.deviceControlsScreen_emptyScreenMessage_padding))
        )
    } else {
        DeviceControlsScreen(
            item = viewState!!,
            onChangeNumeric = { deviceId, groupId, fieldId, value ->
                viewModel.onChangeNumeric(deviceId, groupId, fieldId, value)
            },
            onChangeText = { deviceId, groupId, fieldId, value ->
                viewModel.onChangeText(deviceId, groupId, fieldId, value)
            },
            onChangeButton = { deviceId, groupId, fieldId, value ->
                viewModel.onChangeButton(deviceId, groupId, fieldId, value)
            },
            onChangeMultipleChoice = { deviceId, groupId, fieldId, value ->
                viewModel.onChangeMultipleChoice(deviceId, groupId, fieldId, value)
            },
            onChangeRGB = { deviceId, groupId, fieldId, value ->
                viewModel.onChangeRGB(deviceId, groupId, fieldId, value)
            },
            changeComplexGroupState = { deviceId, groupId, stateId ->
                viewModel.changeComplexGroupState(deviceId, groupId, stateId)
            },
            onChangeNumericInCG = { deviceId, groupId, stateId, fieldId, value ->
                viewModel.onChangeNumericInCG(deviceId, groupId, stateId, fieldId, value)
            },
            onChangeTextInCG = { deviceId, groupId, stateId, fieldId, value ->
                viewModel.onChangeTextInCG(deviceId, groupId, stateId, fieldId, value)
            },
            onChangeButtonInCG = { deviceId, groupId, stateId, fieldId, value ->
                viewModel.onChangeButtonInCG(deviceId, groupId, stateId, fieldId, value)
            },
            onChangeMultipleChoiceInCG = { deviceId, groupId, stateId, fieldId, value ->
                viewModel.onChangeMultipleChoiceInCG(deviceId, groupId, stateId, fieldId, value)
            },
            onChangeRGBInCG = { deviceId, groupId, stateId, fieldId, value ->
                viewModel.onChangeRGBInCG(deviceId, groupId, stateId, fieldId, value)
            },
        )
    }
}

@Composable
fun DeviceControlsScreen(
    item: DeviceControlsViewState,
    onChangeNumeric: (Int, Int, Int, Float) -> Unit,
    onChangeText: (Int, Int, Int, String) -> Unit,
    onChangeButton: (Int, Int, Int, Boolean) -> Unit,
    onChangeMultipleChoice: (Int, Int, Int, Int) -> Unit,
    onChangeRGB: (Int, Int, Int, RGBValue) -> Unit,
    changeComplexGroupState: (Int, Int, Int) -> Unit,
    onChangeNumericInCG: (Int, Int, Int, Int, Float) -> Unit,
    onChangeTextInCG: (Int, Int, Int, Int, String) -> Unit,
    onChangeButtonInCG: (Int, Int, Int, Int, Boolean) -> Unit,
    onChangeMultipleChoiceInCG: (Int, Int, Int, Int, Int) -> Unit,
    onChangeRGBInCG: (Int, Int, Int, Int, RGBValue) -> Unit,
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
                    onChangeNumeric = { groupId, fieldId, value ->
                        onChangeNumeric(item.deviceId, groupId, fieldId, value)
                    },
                    onChangeText = { groupId, fieldId, value ->
                        onChangeText(item.deviceId, groupId, fieldId, value)
                    },
                    onChangeButton = { groupId, fieldId, value ->
                        onChangeButton(item.deviceId, groupId, fieldId, value)
                    },
                    onChangeMultipleChoice = { groupId, fieldId, value ->
                        onChangeMultipleChoice(item.deviceId, groupId, fieldId, value)
                    },
                    onChangeRGB = { groupId, fieldId, value ->
                        onChangeRGB(item.deviceId, groupId, fieldId, value)
                    },
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.deviceControlsScreen_spacer_height_between_groups)))
            }
            items(item.complexGroupsViewStates) { complexGroupViewState ->
                DeviceComplexGroup(
                    item = complexGroupViewState,
                    changeComplexGroupState = { stateId: Int ->
                        changeComplexGroupState(item.deviceId,
                            complexGroupViewState.complexGroupId,
                            stateId)
                    },
                    onChangeNumeric = { groupId, stateId, fieldId, value ->
                        onChangeNumericInCG(item.deviceId, groupId, stateId, fieldId, value)
                    },
                    onChangeText = { groupId, stateId, fieldId, value ->
                        onChangeTextInCG(item.deviceId, groupId, stateId, fieldId, value)
                    },
                    onChangeButton = { groupId, stateId, fieldId, value ->
                        onChangeButtonInCG(item.deviceId, groupId, stateId, fieldId, value)
                    },
                    onChangeMultipleChoice = { groupId, stateId, fieldId, value ->
                        onChangeMultipleChoiceInCG(item.deviceId, groupId, stateId, fieldId, value)
                    },
                    onChangeRGB = { groupId, stateId, fieldId, value ->
                        onChangeRGBInCG(item.deviceId, groupId, stateId, fieldId, value)
                    },
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.deviceControlsScreen_spacer_height_between_groups)))
            }
        }
    }
}

//
//@Preview
//@Composable
//fun PreviewDeviceControlsScreen() {
//    DeviceControlsScreen(item = getDevControlsMock())
//}
