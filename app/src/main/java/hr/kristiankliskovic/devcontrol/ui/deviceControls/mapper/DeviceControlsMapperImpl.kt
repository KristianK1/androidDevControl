package hr.kristiankliskovic.devcontrol.ui.deviceControls.mapper

import android.util.Log
import com.google.gson.Gson
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.*
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceComplexGroupStateViewState
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceComplexGroupViewState
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceGroupViewState
import hr.kristiankliskovic.devcontrol.ui.deviceControls.DeviceControlsViewState

class DeviceControlsMapperImpl : DeviceControlsMapper {
    private val gson = Gson()
    override fun toDeviceControlsViewState(device: Device?): DeviceControlsViewState? {
        if(device == null){
            return null
        }
        Log.i("mapperData", "mapperStart")
        return DeviceControlsViewState(
            deviceId = device.deviceId,
            deviceName = device.deviceName,
            groupsViewStates = device.groups.map{
                groupToGroupViewState(it)
            },
            complexGroupsViewStates = device.complexGroups.map {
                complexGroupToComplexGroupViewState(it)
            },
            deviceOnline = device.isActive,
        )
    }

    private fun complexGroupToComplexGroupViewState(complexGroup: DeviceComplexGroup): DeviceComplexGroupViewState {
        Log.i("mapperData", "mapperCG")
        Log.i("mapperData", gson.toJson(complexGroup))

        return DeviceComplexGroupViewState(
            complexGroupId = complexGroup.complexGroupId,
            groupName = complexGroup.groupName,
            currentState = complexGroup.currentState,
            readOnly = complexGroup.readOnly,
            states = complexGroup.states.map { state ->
                complexGroupStateToComplexGroupStateViewState(state)
            }
        )
    }

    private fun complexGroupStateToComplexGroupStateViewState(
        complexGroupState: DeviceComplexGroupState,
    ): DeviceComplexGroupStateViewState {
        Log.i("mapperData", "mapperCGState")
        Log.i("mapperData", gson.toJson(complexGroupState))

        return DeviceComplexGroupStateViewState(
            stateId = complexGroupState.stateId,
            stateName = complexGroupState.stateName,
            fields = complexGroupState.fields.map { field ->
                fieldToFieldViewState(field)
            }
        )
    }

    private fun groupToGroupViewState(group: DeviceGroup): DeviceGroupViewState {
        Log.i("mapperData", "mapper_group")
        Log.i("mapperData", gson.toJson(group))
        return DeviceGroupViewState(
            groupId = group.groupId,
            groupName = group.groupName,
            fields = group.fields.map { field ->
                val x = fieldToFieldViewState(field)
                Log.i("deviceData", gson.toJson(x))
                x
            }
        )
    }

    private fun fieldToFieldViewState(field: BasicDeviceField): BasicFieldComponentViewState {
        Log.i("mapperData", "mapperfield")
        Log.i("mapperData", gson.toJson(field))

        when (field) {
            is NumericDeviceField -> {
                return if (field.readOnly) {
                    TextFieldOutputViewState(
                        fieldId = field.fieldId,
                        name = field.fieldName,
                        currentValue = "%.2f".format(field.currentValue)
                    )
                } else {
                    NumericFieldInputViewState(
                        fieldId = field.fieldId,
                        name = field.fieldName,
                        currentValue = field.currentValue,
                        minValue = field.minValue,
                        maxValue = field.maxValue,
                        valueStep = field.valueStep,
                    )
                }
            }
            is TextDeviceField -> {
                return if (field.readOnly) {
                    TextFieldOutputViewState(
                        fieldId = field.fieldId,
                        name = field.fieldName,
                        currentValue = field.currentValue
                    )
                } else {
                    TextFieldInputViewState(
                        fieldId = field.fieldId,
                        name = field.fieldName,
                        currentValue = field.currentValue
                    )
                }
            }
            is ButtonDeviceField -> {
                return if (field.readOnly) {
                    ButtonFieldOutputViewState(
                        fieldId = field.fieldId,
                        name = field.fieldName,
                        currentValue = field.currentValue,
                    )
                } else {
                    ButtonFieldInputViewState(
                        fieldId = field.fieldId,
                        name = field.fieldName,
                        currentValue = field.currentValue,
                    )
                }
            }
            is MultipleChoiceDeviceField -> {
                return if (field.readOnly) {
                    TextFieldOutputViewState(
                        fieldId = field.fieldId,
                        name = field.fieldName,
                        currentValue = field.choices[field.currentValue]
                    )
                } else {
                    MultipleChoiceFieldInputViewState(
                        fieldId = field.fieldId,
                        name = field.fieldName,
                        choices = field.choices,
                        currentChoice = field.currentValue,
                    )
                }
            }
            is RGBDeviceField -> {
                return if (field.readOnly) {
                    RGBFieldOutputViewState(
                        fieldId = field.fieldId,
                        name = field.fieldName,
                        currentValue = field.currentValue
                    )
                } else {
                    RGBFieldInputViewState(
                        fieldId = field.fieldId,
                        name = field.fieldName,
                        currentValue = field.currentValue
                    )
                }
            }
        }
    }
}
