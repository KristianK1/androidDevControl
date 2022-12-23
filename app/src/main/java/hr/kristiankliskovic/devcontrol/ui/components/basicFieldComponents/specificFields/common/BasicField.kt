package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.*


@Composable
fun BasicField(
    item: BasicFieldComponentViewState,
    onChange: (Int, Any) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (item) {
        is ButtonFieldInputViewState -> {
            ButtonFieldInput(
                item = item,
                emitValue = { value ->
                    onChange(item.fieldId, value)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        is ButtonFieldOutputViewState -> {
            ButtonFieldOutput(
                item = item,
                modifier = modifier
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
                modifier = modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        is TextFieldOutputViewState -> {
            TextFieldOutput(
                item = item,
                modifier = modifier
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
                modifier = modifier
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
                modifier = modifier
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
                modifier = modifier
                    .fillMaxWidth()
                    .height(130.dp)
            )
        }
    }
}
