package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.RGBValue
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.*

@Composable
fun BasicField(
    item: BasicFieldComponentViewState,
    onChangeNumeric: (Int, Float) -> Unit,
    onChangeText: (Int, String) -> Unit,
    onChangeButton: (Int, Boolean) -> Unit,
    onChangeMultipleChoice: (Int, Int) -> Unit,
    onChangeRGB: (Int, RGBValue) -> Unit,

    modifier: Modifier = Modifier,
) {
    when (item) {
        is ButtonFieldInputViewState -> {
            ButtonFieldInput(
                item = item,
                emitValue = { value ->
                    onChangeButton(item.fieldId, value)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.buttonFieldInputComponent_height))
            )
        }
        is ButtonFieldOutputViewState -> {
            ButtonFieldOutput(
                item = item,
                modifier = modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.buttonFieldOutputComponent_height))
            )
        }
        is TextFieldInputViewState -> {
            TextFieldInput(
                item = item,
                emitValue = { value ->
                    onChangeText(item.fieldId, value)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.textFieldInputComponent_height))
            )
        }
        is TextFieldOutputViewState -> {
            TextFieldOutput(
                item = item,
                modifier = modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.textFieldOutputComponent_height))
            )
        }
        is MultipleChoiceFieldInputViewState -> {
            MultipleChoiceFieldInput(
                item = item,
                emitValue = { value ->
                    onChangeMultipleChoice(item.fieldId, value)
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
                    onChangeRGB(item.fieldId, value)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.rgbFieldInputComponent_height))
            )
        }
        is RGBFieldOutputViewState -> {
            RGBFieldOutput(
                item = item,
                modifier = modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.rgbFieldOutputComponent_height))
            )
        }
        is NumericFieldInputViewState -> {
            NumericFieldInput(
                item = item,
                emitValue = { value ->
                    onChangeNumeric(item.fieldId, value)
                },
                modifier = modifier
                    .fillMaxWidth()
            )
        }
    }
}
