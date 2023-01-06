package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes
import kotlin.math.round
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.common.FieldTitle

data class NumericFieldInputViewState(
    val fieldId: Int,
    val name: String,
    val minValue: Float,
    val maxValue: Float,
    val valueStep: Float,
    var currentValue: Float,
    var localValue: Float,
) : BasicFieldComponentViewState()

@Composable
fun NumericFieldInput(
    item: NumericFieldInputViewState,
    modifier: Modifier = Modifier,
    emitValue: (Float) -> Unit,
) {
    val nstepsSkip = ((item.maxValue - item.minValue) / item.valueStep / 10).toInt()
    val multipleStepsValue = nstepsSkip * item.valueStep
    val multipleStepsValueText = "%.2f".format(multipleStepsValue)

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
                text = "-$multipleStepsValueText",
                onClick = {
                    emitValue(item.localValue + it)
                },
                valueDiff = -1 * multipleStepsValue,
                modifier = Modifier
                    .weight(1f, true)
            )
            ChangeValueButton(
                text = "-${item.valueStep}",
                onClick = {
                    emitValue(item.localValue + it)
                },
                valueDiff = -1 * item.valueStep,
                modifier = Modifier
                    .weight(1f, true)
            )
            FieldValues(
                localValue = "%.2f".format(item.localValue),
                currentValue = "%.2f".format(item.currentValue),
            )
            ChangeValueButton(
                text = "+${item.valueStep}",
                onClick = {
                    emitValue(item.localValue + it)
                },
                valueDiff = item.valueStep,
                modifier = Modifier
                    .weight(1f, true)
            )
            ChangeValueButton(
                text = "+$multipleStepsValueText",
                onClick = {
                    emitValue(item.localValue + it)
                },
                valueDiff = multipleStepsValue,
                modifier = Modifier
                    .weight(1f, true)
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
    localValue: String,
    currentValue: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.numericField_middleColumn_padding))
            .width(IntrinsicSize.Max)
    ) {
        Text(
            text = currentValue,
            textAlign = TextAlign.Center,
            fontSize = 28.sp
        )
        LocalValue(
            localValue,
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.numericField_localValue_padding_top),
                    bottom = 0.dp,
                    start = 0.dp,
                    end = 0.dp
                )
        )
    }
}

@Composable
fun LocalValue(
    localValue: String,
    modifier: Modifier = Modifier
        .fillMaxSize(),
) {
    Text(
        text = stringResource(id = R.string.numericFieldInput_local_value),
        textAlign = TextAlign.Center,
        fontSize = 8.sp,
        modifier = Modifier.fillMaxWidth()
    )
    Text(
        text = localValue,
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun PreviewNumericFieldInput() {
    val viewState = NumericFieldInputViewState(
        fieldId = 0,
        name = "Field 2 Text",
        minValue = -1f,
        maxValue = 25f,
        valueStep = 0.1f,
        currentValue = 18.1f,
        localValue = 18.1f,
    )
    NumericFieldInput(
        item = viewState,
        emitValue = { value ->
            if (value < viewState.minValue) {
                viewState.currentValue = viewState.minValue
                viewState.localValue = viewState.currentValue

            } else if (value > viewState.maxValue) {
                viewState.currentValue = viewState.maxValue
                viewState.localValue = viewState.currentValue
            } else {
                val Nstep = round((value - viewState.minValue) / viewState.valueStep)
                viewState.currentValue = viewState.minValue + Nstep * viewState.valueStep
                Log.i("chVal", "" + viewState.currentValue)
            }
        },
        modifier = Modifier
            .height(130.dp)
            .fillMaxWidth()
    )
}
