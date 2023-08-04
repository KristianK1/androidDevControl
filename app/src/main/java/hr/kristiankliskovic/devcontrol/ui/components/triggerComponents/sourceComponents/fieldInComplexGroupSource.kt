package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import androidx.compose.foundation.layout.Box
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
fun TriggerSourceAddressFieldInComplexGroup(
    state: TriggerSourceDevicesViewState,
    selectFieldInComplexGroupData: (ITriggerSourceAdress_fieldInComplexGroup) -> Unit,
) {
    var deviceSelected by remember { mutableStateOf<TriggerSourceDeviceViewState?>(null) }
    var complexGroupSelected by remember { mutableStateOf<TriggerSourceComplexGroupViewState?>(null) }
    var stateSelected by remember { mutableStateOf<TriggerSourceComplexGroupStateViewState?>(null) }
    var fieldSelected by remember { mutableStateOf<TriggerSourceFieldViewState?>(null) }

//    var complexGroupSelected by remember { mutableStateOf<DeviceComplexGroup?>(null) }
//    var complexGroupStateSelected by remember { mutableStateOf<DeviceComplexGroupState?>(null) }
//    var fieldInComplexGroupSelected by remember { mutableStateOf<BasicDeviceField?>(null) }

    //DEVICE
    Box(
        contentAlignment = Alignment.Center
    ) {
        var dropDownMenuExpandedDevice by remember { mutableStateOf(false) }
        SelectedItem(
            text = if (deviceSelected == null) stringResource(id = R.string.addTriggerScreen_no_device_selected_warning)
            else "${deviceSelected!!.name} (id:${deviceSelected!!.id})",
            onClick = {
                dropDownMenuExpandedDevice = true
            }
        )
        DropdownMenu(
            expanded = dropDownMenuExpandedDevice,
            onDismissRequest = {
                dropDownMenuExpandedDevice = false
            },
        ) {
            for (device in state.devices) {
                DropdownMenuItem(
                    onClick = {
                        if (deviceSelected != device) {
                            deviceSelected = device
                            complexGroupSelected = null
                            stateSelected = null
                            fieldSelected = null
                        }
                        dropDownMenuExpandedDevice = false
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "${device.name} (id:${device.id})"
                    )
                }
            }
        }
    }
    //COMPLEX GROUP
    Box(
        contentAlignment = Alignment.Center
    ) {
        var dropDownMenuExpandedComplexGroup by remember { mutableStateOf(false) }
        SelectedItem(
            text = if (complexGroupSelected == null) stringResource(id = R.string.addTriggerScreen_no_complex_group_selected_text)
            else "${complexGroupSelected!!.complexGroupName} (id:${complexGroupSelected!!.complexGroupId})",
            onClick = {
                dropDownMenuExpandedComplexGroup = true
            }
        )
        DropdownMenu(
            expanded = dropDownMenuExpandedComplexGroup,
            onDismissRequest = {
                dropDownMenuExpandedComplexGroup = false
            },
        ) {
            if(deviceSelected != null) {
                for (complexGroup in deviceSelected!!.complexGroups) {
                    DropdownMenuItem(
                        onClick = {
                            if (complexGroupSelected != complexGroup) {
                                complexGroupSelected = complexGroup
                                fieldSelected = null
                            }
                            dropDownMenuExpandedComplexGroup = false
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "${complexGroup.complexGroupName} (id:${complexGroup.complexGroupId})"
                        )
                    }
                }
            }else{
                DropdownMenuItem(
                    onClick = {
                        dropDownMenuExpandedComplexGroup = false
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = stringResource(id = R.string.addTriggerScreen_no_device_selected_warning)
                    )
                }
            }
        }
    }
    //STATE
    Box(
        contentAlignment = Alignment.Center
    ) {
        var dropDownMenuExpandedState by remember { mutableStateOf(false) }
        SelectedItem(
            text = if (fieldSelected == null) stringResource(id = R.string.addTriggerScreen_no_complex_group_state_selected_text)
            else "${fieldSelected!!.fieldName} (id:${fieldSelected!!.fieldId})",
            onClick = {
                dropDownMenuExpandedState = true
            }
        )
        DropdownMenu(
            expanded = dropDownMenuExpandedState,
            onDismissRequest = {
                dropDownMenuExpandedState = false
            }
        ) {
            if (complexGroupSelected != null) {
                for (state in complexGroupSelected!!.states) {
                    DropdownMenuItem(
                        onClick = {
                            stateSelected = state
                            dropDownMenuExpandedState = false
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "${state.stateName} (id:${state.stateId})"
                        )
                    }
                }
            } else {
                DropdownMenuItem(
                    onClick = {
                        dropDownMenuExpandedState = false
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = stringResource(id = R.string.addTriggerScreen_no_complex_group_selected_warning)
                    )
                }
            }
        }
    }
    //FIELD
    Box(
        contentAlignment = Alignment.Center
    ) {
        var dropDownMenuExpandedField by remember { mutableStateOf(false) }
        SelectedItem(
            text = if (fieldSelected == null) stringResource(id = R.string.addTriggerScreen_no_field_selected_text)
            else "${fieldSelected!!.fieldName} (id:${fieldSelected!!.fieldId})",
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
            if (stateSelected != null) {
                for (field in stateSelected!!.fields) {
                    DropdownMenuItem(
                        onClick = {
                            fieldSelected = field
                            dropDownMenuExpandedField = false
                            val result = ITriggerSourceAdress_fieldInComplexGroup(
                                deviceId = deviceSelected!!.id,
                                complexGroupId = complexGroupSelected!!.complexGroupId,
                                stateId = stateSelected!!.stateId,
                                fieldId = fieldSelected!!.fieldId
                            )
                            selectFieldInComplexGroupData(result)
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
                        dropDownMenuExpandedField = false
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = stringResource(id = R.string.addTriggerScreen_no_state_selected_warning)
                    )
                }
            }
        }
    }
}
