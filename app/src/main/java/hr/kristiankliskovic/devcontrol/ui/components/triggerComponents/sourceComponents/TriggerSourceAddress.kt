package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission.SelectedItem
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes
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
            .width(IntrinsicSize.Max)
            .border(
                width = 1.dp,
                shape = RectangleShape,
                color = Color.Gray,
            )
            .padding(dimensionResource(id = R.dimen.addTriggerBorderPadding))
    ) {
        Text(
            text = stringResource(id = R.string.addTriggerScreen_sourceAddress_selectTitle),
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp
        )

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

        SelectedItemTriggers(
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

@Composable
fun SelectedItemTriggers(
    text: String,
    onClick: () -> Unit,
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.background,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.addTriggerScreen_SelectedItem_text_margin))
            .clip(Shapes.small)
            .background(
                color = MaterialTheme.colorScheme.primary,
            )
            .clickable {
                onClick()
            }
            .padding(dimensionResource(id = R.dimen.addTriggerScreen_SelectedItem_text_padding))
    )
}
