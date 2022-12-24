package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.common.FieldTitle


data class ButtonFieldOutputViewState(
    val fieldId: Int,
    val name: String,
    val currentValue: Boolean,
) : BasicFieldComponentViewState()

@Composable
fun ButtonFieldOutput(
    item: ButtonFieldOutputViewState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .height(30.dp)
            .border(
                width = dimensionResource(id = R.dimen.fieldComponent_borderThickness),
                color = colorResource(id = R.color.fieldComponent_border)
            )
            .padding(dimensionResource(id = R.dimen.fieldComponent_padding))
            .fillMaxWidth()
    ) {
        FieldTitle(
            item.name
        )
        Text(
            text =
            if (item.currentValue) stringResource(id = R.string.buttonField_ON_text)
            else stringResource(id = R.string.buttonField_OFF_text),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
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
