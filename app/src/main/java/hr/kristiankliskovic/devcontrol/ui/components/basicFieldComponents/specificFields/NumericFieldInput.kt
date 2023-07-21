package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields

import android.util.FloatMath
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes
import kotlin.math.round
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.common.FieldTitle
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.OutlineTextWrapper
import kotlin.math.roundToInt

data class NumericFieldInputViewState(
    val fieldId: Int,
    val name: String,
    val minValue: Float,
    val maxValue: Float,
    val valueStep: Float,
    var currentValue: Float,
) : BasicFieldComponentViewState()

@Composable
fun NumericFieldInput(
    item: NumericFieldInputViewState,
    modifier: Modifier = Modifier,
    emitValue: (Float) -> Unit,
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
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
        ) {
            FieldTitle(
                item.name
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "[ ${"%.1f".format(item.minValue)}, ${"%.1f".format(item.maxValue)} ]",
                    fontSize = 20.sp,
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ChangeValueButton(
                text = "-${item.valueStep}",
                onClick = {
                    emitValue(item.currentValue + it)
                },
                valueDiff = -1 * item.valueStep,
                modifier = Modifier
                    .weight(1f, true)
            )
            FieldValues(
                currentValue = "%.2f".format(item.currentValue),
            )
            ChangeValueButton(
                text = "+${item.valueStep}",
                onClick = {
                    emitValue(item.currentValue + it)
                },
                valueDiff = item.valueStep,
                modifier = Modifier
                    .weight(1f, true)
            )
            DecimalInputDialog(
                minValue = item.minValue,
                maxValue = item.maxValue,
                stepValue = item.valueStep,
                startValue = item.currentValue,
                emitValue = {
                    emitValue(it)
                }
            )
        }
    }
}

@Composable
fun ChangeValueButton(
    text: String,
    valueDiff: Float,
    onClick: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .padding(dimensionResource(id = R.dimen.numericField_changeValueButton_padding))
            .clip(Shapes.small)
            .background(colorResource(id = R.color.fieldComponent_button_background))
            .clickable {
                onClick(valueDiff)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
        )
    }
}

@Composable
fun FieldValues(
    currentValue: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.numericField_middleColumn_padding))
            .width(IntrinsicSize.Max)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = currentValue,
            textAlign = TextAlign.Center,
            fontSize = 28.sp
        )
    }
}

//@Composable
//fun LocalValue(
//    localValue: String,
//    modifier: Modifier = Modifier
//        .fillMaxSize(),
//) {
//    Text(
//        text = stringResource(id = R.string.numericFieldInput_local_value),
//        textAlign = TextAlign.Center,
//        fontSize = 8.sp,
//        modifier = Modifier.fillMaxWidth()
//    )
//    Text(
//        text = localValue,
//        textAlign = TextAlign.Center,
//        fontSize = 18.sp,
//        modifier = Modifier.fillMaxWidth()
//    )
//}

@Composable
fun DecimalInputDialog(
    minValue: Float,
    maxValue: Float,
    stepValue: Float,
    startValue: Float,
    emitValue: (Float) -> Unit,
) {
    var dialogOpen by remember {
        mutableStateOf(false)
    }
    if (dialogOpen) {
        Log.i("numericSlider_onValue_minmax", "$minValue $maxValue $stepValue ${((maxValue - minValue)/stepValue).toInt()-1}")
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
                    var value by remember { mutableStateOf(startValue) }

                    Text(
                        text = "$value",
                        fontSize = 30.sp
                    )

                    Slider(
                        onValueChangeFinished = {
                            Log.i("numericSlider_onValue", "finished $value")
                        },
                        steps = ((maxValue - minValue)/stepValue).toInt()-1,
                        valueRange =  minValue..maxValue,
                        enabled = true,
                        value = value,
                        onValueChange = {
                            value = ((it - minValue)/stepValue).roundToInt()*stepValue + minValue
                            Log.i("numericSlider_onValue", "$it $value")
                        },
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
            text = stringResource(id = R.string.numericFieldInput_open_dialog_button),
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
fun PreviewNumericFieldInput() {
    val viewState = NumericFieldInputViewState(
        fieldId = 0,
        name = "Field 2 Text",
        minValue = -1f,
        maxValue = 3f,
        valueStep = 1f,
        currentValue = 2f,
//        localValue = 18.1f,
    )
    NumericFieldInput(
        item = viewState,
        emitValue = { value ->
            if (value < viewState.minValue) {
                viewState.currentValue = viewState.minValue
            } else if (value > viewState.maxValue) {
                viewState.currentValue = viewState.maxValue
            } else {
//                val Nstep = round((value - viewState.minValue) / viewState.valueStep)
//                viewState.currentValue = viewState.minValue + Nstep * viewState.valueStep
                viewState.currentValue = value
            }
        },
        modifier = Modifier
            .height(130.dp)
            .fillMaxWidth()
    )
}
