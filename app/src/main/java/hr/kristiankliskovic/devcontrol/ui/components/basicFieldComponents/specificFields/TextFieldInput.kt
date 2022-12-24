package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.common.FieldTitle
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.OutlineTextWrapper
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes

data class TextFieldInputViewState(
    val fieldId: Int,
    val name: String,
    val currentValue: String,
) : BasicFieldComponentViewState()

@Composable
fun TextFieldInput(
    item: TextFieldInputViewState,
    emitValue: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .border(
                width = dimensionResource(id = R.dimen.fieldComponent_borderThickness),
                color = colorResource(id = R.color.fieldComponent_border)
            )
            .padding(dimensionResource(id = R.dimen.fieldComponent_padding))
            .fillMaxWidth()
    ) {
        FieldTitle(
            item.name
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = item.currentValue,
                    fontSize = 40.sp,
                )
            }
            TextInputDialog(emitValue = emitValue)
        }
    }
}

@Composable
fun TextInputDialog(
    emitValue: (String) -> Unit,
) {
    var dialogOpen by remember {
        mutableStateOf(false)
    }
    if (dialogOpen) {
        AlertDialog(
            onDismissRequest = {
                dialogOpen = false
            },
            buttons = {
                Column(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.fieldComponent_dialog_padding)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var value by remember { mutableStateOf("") }
                    OutlineTextWrapper(
                        label = stringResource(id = R.string.textFieldInput_enter_text_title),
                        onChange = {
                            value = it
                        }
                    )
                    Box(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.dialog_confirm_button_margin)),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clip(Shapes.small)
                                .clickable {
                                    emitValue(value)
                                    dialogOpen = false
                                }
                                .background(colorResource(id = R.color.fieldComponent_button_background))
                                .padding(dimensionResource(id = R.dimen.dialog_confirm_button_padding))
                        ) {
                            Text(
                                text = stringResource(id = R.string.textFieldInput_confirm_text_buttonText),
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            },
            shape = Shapes.small,
            backgroundColor = Color.White,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxHeight()
            .padding(dimensionResource(id = R.dimen.fieldComponent_button_padding))
            .clip(Shapes.small)
            .background(colorResource(id = R.color.fieldComponent_button_background))
            .clickable {
                dialogOpen = true
            }
    ) {
        Text(
            text = stringResource(id = R.string.textInputComponent_open_dialog_button),
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(id = R.dimen.fieldComponent_button_text_padding),
                    vertical = 0.dp
                )
        )
    }

}


@Preview
@Composable
fun PreviewTextFieldInput() {
    val state = TextFieldInputViewState(
        fieldId = 0,
        name = "Text field input 7",
        currentValue = "Hello1",
    )

    TextFieldInput(
        item = state,
        emitValue = {
            Log.i("TFdebug", it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
    )
}
