package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    sourceType: ETriggerSourceType,
    viewState: TriggerSourceAddressViewState,
    selectDevice: (Int) -> Unit,
    selectGroup: (Int) -> Unit,
    selectState: (Int) -> Unit,
    selectField: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
    ) {
        ChooseEntity(
            choices = viewState.sourceDevicesChoices,
            nothingSelectedText = stringResource(id = R.string.addTriggerScreen_no_device_selected_warning),
            noChoicesText = "",
            chosenEntity = viewState.selectedDevice,
            selectEntity = selectDevice,
        )

        ChooseEntity(
            choices = viewState.sourceGroupsChoices,
            nothingSelectedText = stringResource(id = R.string.addTriggerScreen_no_complex_group_selected_text),
            noChoicesText = stringResource(id = R.string.addTriggerScreen_no_device_selected_warning),
            chosenEntity = viewState.selectedGroup,
            selectEntity = selectGroup,
        )

        if (sourceType === ETriggerSourceType.FieldInComplexGroup) {
            ChooseEntity(
                choices = viewState.sourceComplexGroupStatesChoices,
                nothingSelectedText = stringResource(id = R.string.addTriggerScreen_no_complex_group_state_selected_text),
                noChoicesText = "",
                chosenEntity = viewState.selectedState,
                selectEntity = selectState,
            )
        }

        ChooseEntity(
            choices = viewState.sourceFieldChoices,
            nothingSelectedText = stringResource(id = R.string.addTriggerScreen_no_field_selected_text),
            noChoicesText = "",
            chosenEntity = viewState.selectedField,
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
        SelectedItem(
            text = if (chosenEntity == null) nothingSelectedText
            else "${chosenEntity.name} (id:${chosenEntity.id})",
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
            if (choices.isNotEmpty()) {
                for (field in choices) {
                    DropdownMenuItem(
                        onClick = {
                            selectEntity(field.id)
                            dropDownMenuExpandedField = false
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "${field.name} (id:${field.id})"
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
