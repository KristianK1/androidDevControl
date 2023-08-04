package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.mapper

import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.*
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.*

class AddTriggerMapperImpl : AddTriggerMapper {
    override fun toAddTriggerViewState(devices: List<Device>): TriggerSourceDevicesViewState {
        return TriggerSourceDevicesViewState(
            devices = devices.map { device ->
                TriggerSourceDeviceViewState(
                    id = device.deviceId,
                    name = device.deviceName,
                    groups = device.groups.map { group ->
                        TriggerSourceGroupViewState(
                            groupId = group.groupId,
                            groupName = group.groupName,
                            fields = group.fields.map { field ->
                                fieldToFieldViewState(field)
                            }
                        )
                    },
                    complexGroups = device.complexGroups.map { complexGroup ->
                        TriggerSourceComplexGroupViewState(
                            complexGroupId = complexGroup.complexGroupId,
                            complexGroupName = complexGroup.groupName,
                            states = complexGroup.states.map { complexGroupState ->
                                TriggerSourceComplexGroupStateViewState(
                                    stateId = complexGroupState.stateId,
                                    stateName = complexGroupState.stateName,
                                    fields = complexGroupState.fields.map { field ->
                                        fieldToFieldViewState(field)
                                    }
                                )
                            }
                        )
                    }
                )
            }
        )
    }

    private fun fieldToFieldViewState(field: BasicDeviceField): TriggerSourceFieldViewState {
        when (field) {
            is NumericDeviceField -> {
                return TriggerSourceFieldViewState(
                    fieldId = field.fieldId,
                    fieldName = field.fieldName
                )
            }
            is TextDeviceField -> {
                return TriggerSourceFieldViewState(
                    fieldId = field.fieldId,
                    fieldName = field.fieldName
                )
            }
            is ButtonDeviceField -> {
                return TriggerSourceFieldViewState(
                    fieldId = field.fieldId,
                    fieldName = field.fieldName
                )
            }
            is MultipleChoiceDeviceField -> {
                return TriggerSourceFieldViewState(
                    fieldId = field.fieldId,
                    fieldName = field.fieldName
                )
            }
            is RGBDeviceField -> {
                return TriggerSourceFieldViewState(
                    fieldId = field.fieldId,
                    fieldName = field.fieldName
                )
            }
        }
    }
}
