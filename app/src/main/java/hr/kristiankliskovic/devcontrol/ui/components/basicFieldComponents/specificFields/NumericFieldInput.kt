package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields

import android.util.FloatMath
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Slider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
    val prefix: String,
    val sufix: String,
    var currentValue: Float,
) : BasicFieldComponentViewState()

@Composable
fun NumericFieldInput(
    item: NumericFieldInputViewState,
    modifier: Modifier = Modifier,
    emitValue: (Float) -> Unit,
) {
    val decimals = getNumberOfDecimalsNeeded(item.valueStep);

    Column(
        modifier = modifier
            .border(
                width = dimensionResource(id = R.dimen.fieldComponent_borderThickness),
                color = MaterialTheme.colorScheme.inverseSurface
            )
            .padding(dimensionResource(id = R.dimen.fieldComponent_padding))
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
        ) {
            FieldTitle(
                item.name,
                modifier = Modifier
                    .weight(1f, true)
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f, false)
                    .width(IntrinsicSize.Max),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "[ ${"%.${decimals}f".format(item.minValue)}, ${"%.${decimals}f".format(item.maxValue)} ]",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.inverseSurface
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
//                .height(dimensionResource(id = R.dimen.numericFieldInputComponent_height))
        ) {
            ChangeValueButton(
                text = "- ${"%.${decimals}f".format(item.valueStep)}",
                onClick = {
                    emitValue(item.currentValue + it)
                },
                valueDiff = -1 * item.valueStep,
                modifier = Modifier
                    .heightIn(min = dimensionResource(id = R.dimen.numericFieldInputComponent_height))
            )
            DecimalInputDialog(
                minValue = item.minValue,
                maxValue = item.maxValue,
                stepValue = item.valueStep,
                startValue = item.currentValue,
                prefix = item.prefix,
                sufix = item.sufix,
                emitValue = {
                    emitValue(it)
                },
                modifier = Modifier
                    .weight(1f, true)
                    .heightIn(min = dimensionResource(id = R.dimen.numericFieldInputComponent_height))
            )
            ChangeValueButton(
                text = "+ ${"%.${decimals}f".format(item.valueStep)}",
                onClick = {
                    emitValue(item.currentValue + it)
                },
                valueDiff = item.valueStep,
                modifier = Modifier
                    .heightIn(min = dimensionResource(id = R.dimen.numericFieldInputComponent_height))
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
            .padding(dimensionResource(id = R.dimen.numericField_changeValueButton_margin))
            .clip(Shapes.small)
            .background(
                color = MaterialTheme.colorScheme.primary
            )
            .clickable {
                onClick(valueDiff)
            }
            .padding(dimensionResource(id = R.dimen.numericField_changeValueButton_padding))
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            color = MaterialTheme.colorScheme.background,
        )
    }
}

@Composable
fun DecimalInputDialog(
    minValue: Float,
    maxValue: Float,
    stepValue: Float,
    startValue: Float,
    prefix: String,
    sufix: String,
    emitValue: (Float) -> Unit,
    modifier: Modifier,
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
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.inverseSurface,
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
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                )
                                .padding(dimensionResource(id = R.dimen.dialog_confirm_button_padding))
                        ) {
                            Text(
                                text = stringResource(id = R.string.textFieldInput_confirm_text_buttonText),
                                color = MaterialTheme.colorScheme.inverseSurface,
                                fontSize = 20.sp
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
        modifier = modifier
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
        val decimals = getNumberOfDecimalsNeeded(stepValue)
        Text(
            text = "$prefix ${"%.${decimals}f".format(startValue)} $sufix",
            fontSize = 25.sp,
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(id = R.dimen.numericField_middleColumn_padding),
                    vertical = 0.dp
                ),
            softWrap = true,
            color = MaterialTheme.colorScheme.background
        )
    }

}

fun getNumberOfDecimalsNeeded(stepp: Float): Int{
    var step = stepp
    var rez = 0
    while(step - step.toInt() != 0f){
        step *= 10
        rez++;
        if(rez >= 3) break;
    }
    return rez
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
        prefix = "T=",
        sufix = "°C",
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
