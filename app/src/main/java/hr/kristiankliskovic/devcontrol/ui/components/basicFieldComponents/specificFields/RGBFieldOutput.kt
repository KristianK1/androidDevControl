package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.RGBValue
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.common.FieldTitle

data class RGBFieldOutputViewState(
    val fieldId: Int,
    val name: String,
    val currentValue: RGBValue,
) : BasicFieldComponentViewState()

@Composable
fun RGBFieldOutput(
    item: RGBFieldOutputViewState,
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
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Text(
                text = item.currentValue.displayColorString(),
                color = Color(item.currentValue.R, item.currentValue.G, item.currentValue.B),
                fontSize = 40.sp,
            )
        }
    }
}


@Preview
@Composable
fun PreviewRGBFieldOutput() {
    val state = RGBFieldOutputViewState(fieldId = 0,
        name = "RGB field 1",
        currentValue = RGBValue(55, 55, 155)
    )
    RGBFieldOutput(
        item = state,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp))
}
