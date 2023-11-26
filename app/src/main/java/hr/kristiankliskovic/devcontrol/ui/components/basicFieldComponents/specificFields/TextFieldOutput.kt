package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.common.FieldTitle

data class TextFieldOutputViewState(
    val fieldId: Int,
    val name: String,
    val currentValue: String,
) : BasicFieldComponentViewState()

@Composable
fun TextFieldOutput(
    item: TextFieldOutputViewState,
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
        FieldTitle(
            item.name
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = item.currentValue,
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewTextFieldOutput() {
    TextFieldOutput(
        item = TextFieldOutputViewState(
            fieldId = 0,
            name = "Text field 1",
            currentValue = "This is curr value"
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    )
}
