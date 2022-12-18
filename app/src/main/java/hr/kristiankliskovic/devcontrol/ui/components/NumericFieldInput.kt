package hr.kristiankliskovic.devcontrol.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes
import kotlin.math.round

data class NumericFieldInputViewState(
    val fieldId: Int,
    val name: String,
    val minValue: Float,
    val maxValue: Float,
    val valueStep: Float,
    var currentValue: Float,
    var localValue: Float,
)

@Composable
fun NumericFieldInput(
    item: NumericFieldInputViewState,
    modifier: Modifier = Modifier,
    changeValue: (Float) -> Unit,
) {
    val nstepsSkip = ((item.maxValue - item.minValue) / item.valueStep / 10).toInt()
    val multipleStepsValue = nstepsSkip * item.valueStep
    val multipleStepsValueText = "%.2f".format(multipleStepsValue)
    Log.i("debugValues", multipleStepsValueText)

    Column(
        modifier = modifier
            .height(30.dp)
            .border(2.dp, Color.Black)
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        FieldTitle(
            item.name
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ChangeValueButton(
                text = "-\n$multipleStepsValueText",
                onClick = {
                    changeValue(item.localValue + it)
                },
                valueDiff = -1 * multipleStepsValue,
                modifier = Modifier
                    .weight(1f, true)
            )
            ChangeValueButton(
                text = "-\n${item.valueStep}",
                onClick = {
                    changeValue(item.localValue + it)
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
                text = "+\n${item.valueStep}",
                onClick = {
                    changeValue(item.localValue + it)
                },
                valueDiff = item.valueStep,
                modifier = Modifier
                    .weight(1f, true)
            )
            ChangeValueButton(
                text = "+\n$multipleStepsValueText",
                onClick = {
                    changeValue(item.localValue + it)
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
    Text(
        text = "$text",
        modifier = modifier
            .fillMaxHeight()
            .padding(2.dp)
            .clip(Shapes.small)
            .background(Color.LightGray)
            .clickable {
                onClick(valueDiff)
            },
        textAlign = TextAlign.Center,
        fontSize = 22.sp
    )
}

@Composable
fun FieldValues(
    localValue: String,
    currentValue: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(2.dp)
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
                .padding(2.dp)
        )
    }
}

@Composable
fun LocalValue(
    localValue: String,
    modifier: Modifier = Modifier
        .fillMaxSize()
) {
    Text(
        text = "Local value:",
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
        changeValue = { value ->
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
            .height(120.dp)
            .fillMaxWidth()
    )
}
