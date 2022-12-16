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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes

data class NumericFieldInputViewState(
    val fieldId: Int,
    val name: String,
    val minValue: Float,
    val maxValue: Float,
    val valueStep: Float,
    var currentValue: Float,
) : BasicField()

@Composable
fun NumericFieldInput(
    item: NumericFieldInputViewState,
    modifier: Modifier = Modifier,
    changeValue: (Float) -> Unit,
) {
    var localValue: Float = item.currentValue
    val nstepsSkip = ((item.maxValue - item.minValue) / item.valueStep / 10).toInt()
    val multipleStepsValue = nstepsSkip * item.valueStep
    val multipleStepsValueText = "%.2f".format(multipleStepsValue)
    Log.i("debugValues", multipleStepsValueText)

    Column(
        modifier = modifier.height(30.dp)
    ) {
        Text(
            text = item.name,
            fontSize = 35.sp
        )
        Row {
            ChangeValueButton(
                text = "- $multipleStepsValueText",
                onClick = {
                    localValue -= multipleStepsValue
                    changeValue(localValue)
                },
                modifier = Modifier
                    .weight(1f)
            )
            ChangeValueButton(
                text = "- ${item.valueStep}",
                onClick = {
                    localValue -= multipleStepsValue
                    changeValue(localValue)
                },
                modifier = Modifier
                    .weight(1f)
            )


            FieldValues(
                localValue = ".2f".format(localValue),
                currentValue = ".2f".format(item.currentValue),
                modifier = Modifier
                    .weight(2f),
            )



            ChangeValueButton(
                text = "+ ${item.valueStep}",
                onClick = {
                    localValue += multipleStepsValue
                    changeValue(localValue)
                },
                modifier = Modifier
                    .weight(1f)
            )
            ChangeValueButton(
                text = "+ $multipleStepsValueText",
                onClick = {
                    localValue -= multipleStepsValue
                    changeValue(localValue)
                },
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Composable
fun ChangeValueButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "$text",
        modifier = modifier
            .padding(2.dp)
            .clip(Shapes.small)
            .background(Color.LightGray)
            .clickable {
                onClick()
            },
        textAlign = TextAlign.Center,
        fontSize = 22.sp
    )
}

@Composable
fun FieldValues(
    localValue: String,
    currentValue: String,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = currentValue,
            textAlign = TextAlign.Center,
            fontSize = 25.sp
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
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Local value:",
        textAlign = TextAlign.Center,
        fontSize = 8.sp
    )
    Text(
        text = localValue,
        textAlign = TextAlign.Center,
        fontSize = 18.sp
    )
}

@Preview
@Composable
fun PreviewNumericFieldInput() {
    val viewState = NumericFieldInputViewState(
        fieldId = 0,
        name = "Field 1 Text",
        minValue = -1f,
        maxValue = 25f,
        valueStep = 0.1f,
        currentValue = 18.1f,
    )
    NumericFieldInput(
        item = viewState,
        changeValue = { value ->
            viewState.currentValue = value
        },
        modifier = Modifier.size(width = 280.dp, height = 120.dp)
    )
}
