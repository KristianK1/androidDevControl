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
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.TriggerSourceDeviceViewState
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.TriggerSourceDevicesViewState
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.TriggerSourceFieldViewState
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.TriggerSourceGroupViewState

@Composable
fun TriggerSourceAddressFieldInGroup(
    state: TriggerSourceDevicesViewState,
    selectFieldInGroupData: (ITriggerSourceAdress_fieldInGroup) -> Unit,
) {
    var deviceSelected by remember { mutableStateOf<TriggerSourceDeviceViewState?>(null) }
    var groupSelected by remember { mutableStateOf<TriggerSourceGroupViewState?>(null) }
    var fieldSelected by remember { mutableStateOf<TriggerSourceFieldViewState?>(null) }

//    var complexGroupSelected by remember { mutableStateOf<DeviceComplexGroup?>(null) }
//    var complexGroupStateSelected by remember { mutableStateOf<DeviceComplexGroupState?>(null) }
//    var fieldInComplexGroupSelected by remember { mutableStateOf<BasicDeviceField?>(null) }

    Box(
        contentAlignment = Alignment.Center
    ) {
        var dropDownMenuExpandedDevice by remember { mutableStateOf(false) }
        SelectedItem(
            text = if (deviceSelected == null) stringResource(id = R.string.addTriggerScreen_no_device_selected_text)
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
                            groupSelected = null
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

    Box(
        contentAlignment = Alignment.Center
    ) {
        var dropDownMenuExpandedGroup by remember { mutableStateOf(false) }
        SelectedItem(
            text = if (groupSelected == null) stringResource(id = R.string.addTriggerScreen_no_group_selected_text)
            else "${groupSelected!!.groupName} (id:${groupSelected!!.groupId})",
            onClick = {
                dropDownMenuExpandedGroup = true
            }
        )
        DropdownMenu(
            expanded = dropDownMenuExpandedGroup,
            onDismissRequest = {
                dropDownMenuExpandedGroup = false
            },
        ) {
            if (deviceSelected != null) {
                for (group in deviceSelected!!.groups) {
                    DropdownMenuItem(
                        onClick = {
                            if (groupSelected != group) {
                                groupSelected = group
                                fieldSelected = null
                            }
                            dropDownMenuExpandedGroup = false
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "${group.groupName} (id:${group.groupId})"
                        )
                    }
                }
            } else {
                DropdownMenuItem(
                    onClick = {
                        dropDownMenuExpandedGroup = false
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
            if (groupSelected != null) {
                for (field in groupSelected!!.fields) {
                    DropdownMenuItem(
                        onClick = {
                            fieldSelected = field
                            dropDownMenuExpandedField = false
                            val result = ITriggerSourceAdress_fieldInGroup(
                                deviceId = deviceSelected!!.id,
                                groupId = groupSelected!!.groupId,
                                fieldId = fieldSelected!!.fieldId
                            )
                            selectFieldInGroupData(result)
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
                        text = stringResource(id = R.string.addTriggerScreen_no_group_selected_warning)
                    )
                }
            }
        }
    }
}
