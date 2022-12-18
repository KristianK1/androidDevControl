package hr.kristiankliskovic.devcontrol.ui.components

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R


data class ButtonFieldOutputViewState(
    val fieldId: Int,
    val name: String,
    val currentValue: Boolean,
)

@Composable
fun ButtonFieldOutput(
    item: ButtonFieldOutputViewState,
    modifier: Modifier = Modifier,
) {
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
        Text(
            text =
            if (item.currentValue) stringResource(id = R.string.buttonField_ON_text)
            else stringResource(id = R.string.buttonField_OFF_text),
            fontSize = 38.sp,
            color =
            if (item.currentValue) colorResource(id = R.color.buttonField_ON)
            else colorResource(id = R.color.buttonField_OFF),
        )
    }
}

@Preview
@Composable
fun PreviewButtonFieldOutput() {
    val state = ButtonFieldOutputViewState(
        fieldId = 0,
        name = "BTN state 1",
        currentValue = !false,
    )
    ButtonFieldOutput(
        item = state,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
    )
}
