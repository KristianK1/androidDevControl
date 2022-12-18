package hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.*

data class DeviceGroupViewState(
    val groupId: Int,
    val fields: List<BasicFieldComponentViewState>,
)

@Composable
fun DeviceGroup(
    item: DeviceGroupViewState,
    onChange: (Int, Int, Any) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = PaddingValues(5.dp)
    ) {
        items(
            items = item.fields
        ) { fieldViewState ->
            BasicField(
                item = fieldViewState,
                onChange = { fieldId, value ->
                    onChange(item.groupId, fieldId, value)
                }
            )
        }
    }
}

@Composable
fun BasicField(
    item: BasicFieldComponentViewState,
    onChange: (Int, Any) -> Unit,
) {
    when (item) {
        is ButtonFieldInputViewState -> {
            ButtonFieldInput(
                item = item,
                emitValue = { value ->
                    onChange(item.fieldId, value)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        is ButtonFieldOutputViewState -> {
            ButtonFieldOutput(
                item = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        is TextFieldInputViewState -> {
            TextFieldInput(
                item = item,
                emitValue = { value ->
                    onChange(item.fieldId, value)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        is TextFieldOutputViewState -> {
            TextFieldOutput(
                item = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        is MultipleChoiceFieldInputViewState -> {
            MultipleChoiceFieldInput(
                item = item,
                emitValue = { value ->
                    onChange(item.fieldId, value)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        is RGBFieldInputViewState -> {
            RGBFieldInput(
                item = item,
                emitValue = { value ->
                    onChange(item.fieldId, value)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        is NumericFieldInputViewState -> {
            NumericFieldInput(
                item = item,
                emitValue = { value ->
                    onChange(item.fieldId, value)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
    }
}


@Preview
@Composable
fun PreviewDeviceGroup() {
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
    val state = DeviceGroupViewState(
        fields = list,
        groupId = 0,
    )
    DeviceGroup(item = state, onChange = { groupId, fieldId, value ->

    }
    )
}
