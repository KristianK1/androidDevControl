package hr.kristiankliskovic.devcontrol.mock

import hr.kristiankliskovic.devcontrol.model.RGBValue
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.*
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceComplexGroupStateViewState
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceComplexGroupViewState
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceGroupViewState
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.DeviceNameAndStatusViewState
import hr.kristiankliskovic.devcontrol.ui.deviceControls.DeviceControlsViewState
import hr.kristiankliskovic.devcontrol.ui.myDevices.MyDevicesViewState

fun getMockDeviceGroupViewState(): DeviceGroupViewState {
    val list: List<BasicFieldComponentViewState> = listOf(
        ButtonFieldInputViewState(
            fieldId = 0,
            name = "BTN state 1",
            currentValue = !false,
        ),
        ButtonFieldInputViewState(
            fieldId = 1,
            name = "BTN state 2",
            currentValue = false,
        ),
        ButtonFieldOutputViewState(
            fieldId = 0,
            name = "BTN state 1",
            currentValue = !false,
        ),
        ButtonFieldOutputViewState(
            fieldId = 0,
            name = "BTN state 1",
            currentValue = !false,
        ),
        MultipleChoiceFieldInputViewState(
            fieldId = 0,
            name = "MC field 1",
            choices = listOf("Choice1", "Choice2", "Choice3", "Choice4"),
            currentChoice = 1,
        ),
        MultipleChoiceFieldInputViewState(
            fieldId = 0,
            name = "MC field 1",
            choices = listOf("Choice1", "Choice2", "Choice3", "Choice4"),
            currentChoice = 1,
        ),
        MultipleChoiceFieldInputViewState(
            fieldId = 0,
            name = "MC field 1",
            choices = listOf("Choice1", "Choice2", "Choice3", "Choice4"),
            currentChoice = 1,
        ),
        MultipleChoiceFieldInputViewState(
            fieldId = 0,
            name = "MC field 1",
            choices = listOf("Choice1", "Choice2", "Choice3", "Choice4"),
            currentChoice = 1,
        ),
        NumericFieldInputViewState(
            fieldId = 0,
            name = "Field 2 Text",
            minValue = -1f,
            maxValue = 25f,
            valueStep = 0.1f,
            currentValue = 18.1f,
            localValue = 18.1f,
        ),
        NumericFieldInputViewState(
            fieldId = 0,
            name = "Field 2 Text",
            minValue = -1f,
            maxValue = 25f,
            valueStep = 0.1f,
            currentValue = 18.1f,
            localValue = 18.1f,
        ),
        NumericFieldInputViewState(
            fieldId = 0,
            name = "Field 2 Text",
            minValue = -1f,
            maxValue = 25f,
            valueStep = 0.1f,
            currentValue = 18.1f,
            localValue = 18.1f,
        ),
        NumericFieldInputViewState(
            fieldId = 0,
            name = "Field 2 Text",
            minValue = -1f,
            maxValue = 25f,
            valueStep = 0.1f,
            currentValue = 18.1f,
            localValue = 18.1f,
        ),
        RGBFieldInputViewState(fieldId = 0,
            name = "RGB field 1",
            currentValue = RGBValue(54, 26, 165)
        ),
        RGBFieldInputViewState(fieldId = 0,
            name = "RGB field 1",
            currentValue = RGBValue(25, 55, 25)
        ),
        RGBFieldInputViewState(fieldId = 0,
            name = "RGB field 1",
            currentValue = RGBValue(255, 205, 155)
        ),
        RGBFieldInputViewState(fieldId = 0,
            name = "RGB field 1",
            currentValue = RGBValue(55, 255, 195)
        ),
        RGBFieldInputViewState(fieldId = 0,
            name = "RGB field 1",
            currentValue = RGBValue(25, 100, 115)
        ),
        TextFieldInputViewState(
            fieldId = 0,
            name = "Text field input 7",
            currentValue = "Hello1",
        ),
        TextFieldInputViewState(
            fieldId = 0,
            name = "Text field input 7",
            currentValue = "Hello1",
        ),
        TextFieldInputViewState(
            fieldId = 0,
            name = "Text field input 7",
            currentValue = "Hello1",
        ),
        TextFieldInputViewState(
            fieldId = 0,
            name = "Text field input 7",
            currentValue = "Hello1",
        ),
        TextFieldOutputViewState(
            fieldId = 0,
            name = "Text field 1",
            currentValue = "This is curr value"
        ),
        TextFieldOutputViewState(
            fieldId = 0,
            name = "Text field 1",
            currentValue = "This is curr value"
        ),
        TextFieldOutputViewState(
            fieldId = 0,
            name = "Text field 1",
            currentValue = "This is curr value"
        ),
    )
    return DeviceGroupViewState(
        fields = list,
        groupName = "Group1",
        groupId = 0,
    )
}


fun getMockDeviceComplexGroupViewState(): DeviceComplexGroupViewState {
    val fields1: List<BasicFieldComponentViewState> = listOf(
        NumericFieldInputViewState(
            fieldId = 0,
            name = "Red",
            minValue = 0f,
            maxValue = 25f,
            valueStep = 0.1f,
            currentValue = 18.1f,
            localValue = 18.1f,
        ),
        NumericFieldInputViewState(
            fieldId = 1,
            name = "Green",
            minValue = 0f,
            maxValue = 25f,
            valueStep = 0.1f,
            currentValue = 18.1f,
            localValue = 18.1f,
        ),
        NumericFieldInputViewState(
            fieldId = 2,
            name = "Blue",
            minValue = 0f,
            maxValue = 25f,
            valueStep = 0.1f,
            currentValue = 18.1f,
            localValue = 18.1f,
        )
    )
    val state1 = DeviceComplexGroupStateViewState(
        stateId = 0,
        stateName = "individual",
        fields = fields1
    )
    val fields2: List<BasicFieldComponentViewState> = listOf(
        MultipleChoiceFieldInputViewState(
            fieldId = 0,
            name = "Animations",
            choices = listOf("OFF", "Animation 1", "Animation 2", "Animation 3"),
            currentChoice = 1,
        ),
    )
    val state2 = DeviceComplexGroupStateViewState(
        stateId = 0,
        stateName = "animations",
        fields = fields2
    )
    val fields3: List<BasicFieldComponentViewState> = listOf(
        RGBFieldInputViewState(
            fieldId = 0,
            name = "RGB field 1",
            currentValue = RGBValue(70, 66, 104)
        ),
    )
    val state3 = DeviceComplexGroupStateViewState(
        stateId = 0,
        stateName = "rgb wheel",
        fields = fields3
    )
    return DeviceComplexGroupViewState(
        complexGroupId = 0,
        groupName = "RGB control",
        states = listOf(state1, state2, state3),
        currentState = 1,
        readOnly = !true,
    )
}


fun getDeviceListMockData(): MyDevicesViewState {
    return MyDevicesViewState(
        devices = listOf(
            DeviceNameAndStatusViewState(
                deviceId = 0,
                deviceName = "deviceName1",
                deviceStatus = true
            ),
            DeviceNameAndStatusViewState(
                deviceId = 1,
                deviceName = "deviceName2",
                deviceStatus = false
            ),
            DeviceNameAndStatusViewState(
                deviceId = 7,
                deviceName = "deviceName7",
                deviceStatus = true
            ),
            DeviceNameAndStatusViewState(
                deviceId = 11,
                deviceName = "deviceName11",
                deviceStatus = false
            ),
            DeviceNameAndStatusViewState(
                deviceId = 16,
                deviceName = "deviceName16",
                deviceStatus = false
            ),
            DeviceNameAndStatusViewState(
                deviceId = 99,
                deviceName = "deviceName99",
                deviceStatus = true
            ),
            DeviceNameAndStatusViewState(
                deviceId = 118,
                deviceName = "deviceName118",
                deviceStatus = false
            ),
        )
    )
}

fun getDevControlsMock(): DeviceControlsViewState{
    return DeviceControlsViewState(
        deviceId = 0,
        deviceName = "dEV1",
        groupsViewStates = listOf(
            getMockDeviceGroupViewState(),
            getMockDeviceGroupViewState(),
            getMockDeviceGroupViewState()),
        complexGroupsViewStates = listOf(
            getMockDeviceComplexGroupViewState(),
            getMockDeviceComplexGroupViewState(),
            getMockDeviceComplexGroupViewState()),
        deviceOnline = true,
    )
}
