package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission.SelectedItem
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.*


@Composable
fun TriggerSourceAddress(
    includeComplexGroups: Boolean,
    viewState: TriggerAddressViewState,
    selectDevice: (Int) -> Unit,
    selectGroup: (Int) -> Unit,
    selectState: (Int) -> Unit,
    selectField: (Int) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ChooseEntity(
            choices = viewState.devicesChoices,
            nothingSelectedText = stringResource(id = R.string.addTriggerScreen_no_device_selected_warning),
            noChoicesText = "",
            chosenEntity = viewState.selectedDevice.value,
            selectEntity = selectDevice,
        )

        ChooseEntity(
            choices = viewState.groupsChoices,
            nothingSelectedText = if (!includeComplexGroups) stringResource(
                id = R.string.addTriggerScreen_no_group_selected_warning) else stringResource(id = R.string.addTriggerScreen_no_complex_group_selected_text),
            noChoicesText = stringResource(id = R.string.addTriggerScreen_no_device_selected_warning),
            chosenEntity = viewState.selectedGroup.value,
            selectEntity = selectGroup,
        )

        if (includeComplexGroups) {
            ChooseEntity(
                choices = viewState.complexGroupStatesChoices,
                nothingSelectedText = stringResource(id = R.string.addTriggerScreen_no_complex_group_state_selected_text),
                noChoicesText = "",
                chosenEntity = viewState.selectedState.value,
                selectEntity = selectState,
            )
        }

        ChooseEntity(
            choices = viewState.fieldChoices,
            nothingSelectedText = stringResource(id = R.string.addTriggerScreen_no_field_selected_text),
            noChoicesText = "",
            chosenEntity = viewState.selectedField.value,
            selectEntity = selectField,
        )
    }
}

@Composable
fun ChooseEntity(
    choices: List<DeviceEntityViewState>,
    chosenEntity: DeviceEntityViewState?,
    nothingSelectedText: String,
    noChoicesText: String,
    selectEntity: (Int) -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        var dropDownMenuExpandedField by remember { mutableStateOf(false) }
        Log.i("ttt1", "!" + choices.size)

        SelectedItem(
            text = if (chosenEntity == null) nothingSelectedText
            else "${chosenEntity.name} (id:${chosenEntity.id})",
            onClick = {
                dropDownMenuExpandedField = true
            }
        )
        Log.i("ttt2", "!" + choices.size)

        DropdownMenu(
            expanded = dropDownMenuExpandedField,
            onDismissRequest = {
                dropDownMenuExpandedField = false
            }
        ) {
            Log.i("ttt", "!" + choices.size)
            if (choices.isNotEmpty()) {
                for (choice in choices) {
                    DropdownMenuItem(
                        onClick = {
                            selectEntity(choice.id)
                            dropDownMenuExpandedField = false
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "${choice.name} (id:${choice.id})"
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
                        text = noChoicesText
                    )
                }
            }
        }
    }
}
