package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R

@Composable
fun TriggerItemLine(
    propertyName: String,
    propertyValue: String,
    increasedFont: Boolean = false,
) {
    Row(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.triggerItem_padding_betweenLines))
    ) {
        Text(
            text = propertyName,
            fontSize = if (increasedFont) 22.sp else 16.sp,
        )
        Text(
            text = propertyValue,
            fontSize = if (increasedFont) 22.sp else 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
