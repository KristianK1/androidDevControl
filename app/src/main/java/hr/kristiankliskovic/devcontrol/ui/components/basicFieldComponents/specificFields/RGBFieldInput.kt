package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
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
import com.godaddy.android.colorpicker.ClassicColorPicker
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.RGBValue
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.common.FieldTitle
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes

data class RGBFieldInputViewState(
    val fieldId: Int,
    val name: String,
    val currentValue: RGBValue,
) : BasicFieldComponentViewState()

@Composable
fun RGBFieldInput(
    item: RGBFieldInputViewState,
    emitValue: (RGBValue) -> Unit,
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
        FieldTitle(item.name)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clip(CircleShape)
                    .background(
                        color = Color(
                            red = item.currentValue.R / 255f,
                            green = item.currentValue.G / 255f,
                            blue = item.currentValue.B / 255f,
                        )
                    )
                    .padding(
                        vertical = 10.dp,
                        horizontal = 50.dp
                    )
            ) {
                Text(
                    text = item.currentValue.displayColorString(),
                    color = if((item.currentValue.R + item.currentValue.G + item.currentValue.B) > 400) Color.Black else Color.White,
                    fontSize = 30.sp,
                )
            }
            RGBDialog(selectValue = emitValue)
        }
    }
}

@Composable
fun RGBDialog(
    selectValue: (RGBValue) -> Unit,
) {
    var rgb by remember {
        mutableStateOf(RGBValue(0, 0, 0))
    }
    var dialogOpen by remember {
        mutableStateOf(false)
    }

    if (dialogOpen) {
        AlertDialog(onDismissRequest = {
            dialogOpen = false
        },
            buttons = {
                Column(
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    ClassicColorPicker(
                        onColorChanged = {
                            val color = it.toColor()
                            rgb = RGBValue(
                                (color.red * 255).toInt(),
                                (color.green * 255).toInt(),
                                (color.blue * 255).toInt()
                            )
                        },
                        modifier = Modifier
                            .weight(0.8f)
                    )
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.background,
                            )
                            .padding(dimensionResource(id = R.dimen.dialog_confirm_button_margin))
                            .fillMaxWidth()
                            .clip(Shapes.small)
                            .clickable {
                                selectValue(rgb)
                                dialogOpen = false
                            }
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                            )
                            .padding(dimensionResource(id = R.dimen.dialog_confirm_button_padding))
                    ) {
                        Text(
                            text = "${stringResource(id = R.string.RGBFieldInput_set_value_button)} (${rgb.displayColorString()})",
                            color = MaterialTheme.colorScheme.background,
                            fontSize = 20.sp
                        )
                    }
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.fieldComponent_dialog_padding)),
            shape = Shapes.small,
            backgroundColor = Color.White,
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true))
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxHeight()
            .padding(dimensionResource(id = R.dimen.fieldComponent_button_padding))
            .clip(Shapes.small)
            .background(
                color = MaterialTheme.colorScheme.primary,
            )
            .clickable {
                dialogOpen = true
            }
    ) {
        Text(
            text = stringResource(id = R.string.RGBFieldInput_open_dialog),
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.background,
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
fun PreviewRGBFieldInput() {
    val state = RGBFieldInputViewState(fieldId = 0,
        name = "RGB field 1",
        currentValue = RGBValue(55, 55, 155)
    )
    RGBFieldInput(item = state, emitValue = {
    }, modifier = Modifier
        .fillMaxWidth()
        .height(100.dp))
}
