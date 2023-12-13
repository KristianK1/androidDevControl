package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
                color = MaterialTheme.colorScheme.inverseSurface
            )
            .padding(dimensionResource(id = R.dimen.fieldComponent_padding))
            .fillMaxWidth()
    ) {
        FieldTitle(item.name)
        Box(
            modifier = Modifier
                .height(50.dp)
                .clip(CircleShape)
                .background(
                    color = Color(
                        red = item.currentValue.R / 255f,
                        green = item.currentValue.G / 255f,
                        blue = item.currentValue.B / 255f,
                    )
                )
                .padding(
//                    vertical = 10.dp,
                    horizontal = 50.dp
                )
        ) {
            Text(
                text = item.currentValue.displayColorString(),
                color = if((item.currentValue.R + item.currentValue.G + item.currentValue.B) > 400) Color.Black else Color.White,
                fontSize = 30.sp,
                modifier = Modifier
                    .align(Alignment.Center)
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
