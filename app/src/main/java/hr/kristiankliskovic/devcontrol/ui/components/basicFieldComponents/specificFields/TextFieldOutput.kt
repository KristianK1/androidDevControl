package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.FieldTitle

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
            .height(30.dp)
            .border(2.dp, Color.Black)
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        FieldTitle(
            item.name
        )
        Text(
            text = item.currentValue,
            fontSize = 38.sp
        )
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
