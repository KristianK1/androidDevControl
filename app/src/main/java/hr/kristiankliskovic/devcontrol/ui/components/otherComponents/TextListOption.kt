package hr.kristiankliskovic.devcontrol.ui.components.otherComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes

@Composable
fun TextListOption(
    text: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.textListOption_margin))
            .clip(Shapes.small)
            .background(
                MaterialTheme.colorScheme.primary.copy(alpha = 0.45f)
            )
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(dimensionResource(id = R.dimen.textListOption_padding)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 25.sp,
            color = MaterialTheme.colorScheme.inverseSurface,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
