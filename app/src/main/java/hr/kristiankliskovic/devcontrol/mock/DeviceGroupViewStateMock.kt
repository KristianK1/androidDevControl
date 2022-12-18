package hr.kristiankliskovic.devcontrol.mock

import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.*
import hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents.DeviceGroupViewState

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
            currentValue = RGBValue(255, 255, 255)
        ),
        RGBFieldInputViewState(fieldId = 0,
            name = "RGB field 1",
            currentValue = RGBValue(255, 255, 255)
        ),
        RGBFieldInputViewState(fieldId = 0,
            name = "RGB field 1",
            currentValue = RGBValue(255, 255, 255)
        ),
        RGBFieldInputViewState(fieldId = 0,
            name = "RGB field 1",
            currentValue = RGBValue(255, 255, 255)
        ),
        RGBFieldInputViewState(fieldId = 0,
            name = "RGB field 1",
            currentValue = RGBValue(255, 255, 255)
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
