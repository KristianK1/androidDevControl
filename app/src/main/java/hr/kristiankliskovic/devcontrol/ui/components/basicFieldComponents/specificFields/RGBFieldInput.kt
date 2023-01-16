package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
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
                color = colorResource(id = R.color.fieldComponent_border)
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
            ) {
                Text(
                    text = item.currentValue.displayColorString(),
                    color = Color(item.currentValue.R, item.currentValue.G, item.currentValue.B),
                    fontSize = 40.sp,
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
                            Log.i("rgbDebug", "${color.blue}|${color.blue.toInt()}")
                            rgb = RGBValue(
                                (color.red * 256).toInt(),
                                (color.green * 256).toInt(),
                                (color.blue * 256).toInt()
                            )
                        },
                        modifier = Modifier
                            .weight(0.8f)
                    )

                    Box(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.dialog_confirm_button_margin))
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clip(Shapes.small)
                                .clickable {
                                    selectValue(rgb)
                                    dialogOpen = false
                                }
                                .background(colorResource(id = R.color.fieldComponent_button_background))
                                .padding(dimensionResource(id = R.dimen.dialog_confirm_button_padding))
                        ) {
                            Text(
                                text = "${stringResource(id = R.string.RGBFieldInput_set_value_button)} (${rgb.displayColorString()})",
                                fontSize = 20.sp
                            )
                        }
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
            .background(colorResource(id = R.color.fieldComponent_button_background))
            .clickable {
                dialogOpen = true
            }
    ) {
        Text(
            text = stringResource(id = R.string.RGBFieldInput_open_dialog),
            fontSize = 20.sp,
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
        Log.i("rgbDebug", it.displayColorString())
    }, modifier = Modifier
        .fillMaxWidth()
        .height(100.dp))
}
