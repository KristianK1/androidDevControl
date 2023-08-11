package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.mapper

import android.util.Log
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.*

class AddTriggerMapperImpl : AddTriggerMapper {
//    override fun toTriggerSourceDeviceViewState(devices: List<Device>): List<TriggerSourceDeviceViewState> {
//        val devices = devices.map { device ->
//            TriggerSourceDeviceViewState(
//                id = device.deviceId,
//                name = device.deviceName,
//                groups = device.groups.map { group ->
//                    TriggerSourceGroupViewState(
//                        groupId = group.groupId,
//                        groupName = group.groupName,
//                        fields = group.fields.map { field ->
//                            fieldToFieldViewState(field)
//                        }
//                    )
//                },
//                complexGroups = device.complexGroups.map { complexGroup ->
//                    TriggerSourceComplexGroupViewState(
//                        complexGroupId = complexGroup.complexGroupId,
//                        complexGroupName = complexGroup.groupName,
//                        states = complexGroup.states.map { complexGroupState ->
//                            TriggerSourceComplexGroupStateViewState(
//                                stateId = complexGroupState.stateId,
//                                stateName = complexGroupState.stateName,
//                                fields = complexGroupState.fields.map { field ->
//                                    fieldToFieldViewState(field)
//                                }
//                            )
//                        }
//                    )
//                }
//            )
//        }
//        return devices
//    }

    override fun devicesToEntityViewState(
        devices: List<Device>,
    ): List<DeviceEntityViewState> {
        Log.i("deviceMapX", "mapping")
        return devices.map {
            Log.i("deviceMapX", it.deviceName)
            DeviceEntityViewState(
                id = it.deviceId,
                name = it.deviceName,
            )
        }
    }

    override fun groupsToEntityViewState(groups: List<DeviceGroup>): List<DeviceEntityViewState> {
        return groups.map {
            DeviceEntityViewState(
                id = it.groupId,
                name = it.groupName,
            )
        }
    }

    override fun complexGroupsToEntityViewState(
        complexGroups: List<DeviceComplexGroup>,
        justWrites: Boolean,
    ): List<DeviceEntityViewState> {
        return complexGroups.mapNotNull {
            if (justWrites && it.readOnly) null
            else
                DeviceEntityViewState(
                    id = it.complexGroupId,
                    name = it.groupName,
                )
        }
    }

    override fun complexGroupsStatesToEntityViewState(
        states: List<DeviceComplexGroupState>,
    ): List<DeviceEntityViewState> {
        return states.map {
            DeviceEntityViewState(
                id = it.stateId,
                name = it.stateName,
            )
        }
    }

    override fun fieldsToEntityViewState(
        fields: List<BasicDeviceField>,
        justWrites: Boolean,
    ): List<DeviceEntityViewState> {
        return fields.mapNotNull {
            when (it) {
                is ButtonDeviceField -> {
                    if (justWrites && it.readOnly) null
                    else
                        DeviceEntityViewState(
                            id = it.fieldId,
                            name = it.fieldName,
                        )
                }
                is MultipleChoiceDeviceField -> {
                    if (justWrites && it.readOnly) null
                    else
                        DeviceEntityViewState(
                            id = it.fieldId,
                            name = it.fieldName,
                        )
                }
                is NumericDeviceField -> {
                    if (justWrites && it.readOnly) null
                    else
                        DeviceEntityViewState(
                            id = it.fieldId,
                            name = it.fieldName,
                        )
                }
                is RGBDeviceField -> {
                    if (justWrites && it.readOnly) null
                    else
                        DeviceEntityViewState(
                            id = it.fieldId,
                            name = it.fieldName,
                        )
                }
                is TextDeviceField -> {
                    if (justWrites && it.readOnly) null
                    else
                        DeviceEntityViewState(
                            id = it.fieldId,
                            name = it.fieldName,
                        )
                }
            }
        }
    }
}
