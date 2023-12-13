package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
                color = MaterialTheme.colorScheme.inverseSurface
            )
            .padding(dimensionResource(id = R.dimen.fieldComponent_padding))
            .fillMaxWidth()
    ) {
        FieldTitle(
            item.name
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.currentValue,
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f, true),
                fontWeight = FontWeight.Bold,
            )
            TextInputDialog(
                emitValue = emitValue,
                modifier = Modifier.weight(1f, false)
            )
        }
    }
}

@Composable
fun TextInputDialog(
    emitValue: (String) -> Unit,
    modifier: Modifier = Modifier
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
                                .background(
                                    color = MaterialTheme.colorScheme.primary
                                )
                                .padding(dimensionResource(id = R.dimen.dialog_confirm_button_padding))
                        ) {
                            Text(
                                text = stringResource(id = R.string.textFieldInput_confirm_text_buttonText),
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.background
                            )
                        }
                    }
                }
            },
            modifier = Modifier // Set the width and padding
                .fillMaxWidth()
                .padding(32.dp)
                .clip(Shapes.small)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.6f)
                ),
            shape = RoundedCornerShape(5.dp),
            backgroundColor = MaterialTheme.colorScheme.background,
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        )
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxHeight()
            .padding(dimensionResource(id = R.dimen.fieldComponent_button_padding))
            .clip(Shapes.small)
            .background(
                color = MaterialTheme.colorScheme.primary
            )
            .clickable {
                dialogOpen = true
            }
    ) {
        Text(
            text = stringResource(id = R.string.textInputComponent_open_dialog_button),
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.background,
            modifier = modifier
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
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
    )
}
