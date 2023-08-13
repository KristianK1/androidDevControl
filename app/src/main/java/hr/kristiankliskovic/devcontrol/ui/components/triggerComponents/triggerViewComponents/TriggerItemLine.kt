package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun TriggerItemLine(
    propertyName: String,
    propertyValue: String,
    increasedFont: Boolean = false,
) {
    Row {
        Text(
            text = propertyName,
            fontSize = if (increasedFont) 20.sp else 14.sp
        )
        Text(
            text = propertyValue,
            fontSize = if (increasedFont) 20.sp else 14.sp
        )
    }
}
