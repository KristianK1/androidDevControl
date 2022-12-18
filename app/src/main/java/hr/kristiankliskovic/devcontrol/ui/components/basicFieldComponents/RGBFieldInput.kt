package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.godaddy.android.colorpicker.ClassicColorPicker
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes

data class RGBValue(
    val R: Int,
    val G: Int,
    val B: Int,
) {
    fun displayColorString(): String {
        return "#${this.intToColorHexString(this.R)}${this.intToColorHexString(this.G)}${
            this.intToColorHexString(this.B)
        }"
    }

    private fun intToColorHexString(num: Int): String {
        var str = num.toString(16)
        if (str.length < 2) {
            str = "0$str";
        }
        return str.uppercase()
    }
}

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
    Column(modifier = modifier
        .height(30.dp)
        .border(2.dp, Color.Black)
        .padding(5.dp)
        .fillMaxWidth()) {
        FieldTitle(item.name)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = item.currentValue.displayColorString(),
                color = Color(item.currentValue.R, item.currentValue.G, item.currentValue.B),
                fontSize = 38.sp)
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(2.dp)
                    .clip(Shapes.small)
                    .background(Color.LightGray)
                    .clickable {

                    }) {
                RGBDialog(selectValue = emitValue)
            }
        }
    }
}

@Composable
fun RGBDialog(
    selectValue: (RGBValue) -> Unit,
) {

    var dialogOpen by remember {
        mutableStateOf(false)
    }

    if (dialogOpen) {
        AlertDialog(onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality,
            // simply leave this block empty.
            dialogOpen = false
        },
            buttons = {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    verticalArrangement = Arrangement.SpaceAround) {
                    ClassicColorPicker(onColorChanged = {
                        val color = it.toColor()
                        Log.i("rgbDebug", "${color.blue}|${color.blue.toInt()}")
                        selectValue(RGBValue(color.red.toInt(),
                            color.green.toInt(),
                            color.blue.toInt()))
                    }, modifier = Modifier.weight(0.8f))
                    Text(text = stringResource(id = R.string.RGBFieldInput_choose_value_confirm_buttonText),
                        modifier = Modifier
                            .background(Color.LightGray)
                            .clickable {
                                dialogOpen = false
                            }
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .padding(20.dp))
                }

            },
            title = {
                Text(text = stringResource(id = R.string.RGBFieldInput_choose_value_title))
            },
            modifier = Modifier // Set the width and padding
                .fillMaxWidth()
                .padding(32.dp),
            shape = RoundedCornerShape(5.dp),
            backgroundColor = Color.White,
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true))
    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxHeight()
            .padding(2.dp)
            .clip(Shapes.small)
            .background(Color.LightGray)
            .clickable {
                dialogOpen = true
            }) {
        Text(text = stringResource(id = R.string.RGBFieldInput_open_dialog),
            modifier = Modifier.padding(12.dp))
    }
}

@Preview
@Composable
fun PreviewRGBFieldInput() {
    val state = RGBFieldInputViewState(fieldId = 0,
        name = "RGB field 1",
        currentValue = RGBValue(255, 255, 255)
    )
    RGBFieldInput(item = state, emitValue = {
        Log.i("rgbDebug", it.displayColorString())
    }, modifier = Modifier
        .fillMaxWidth()
        .height(100.dp))
}
