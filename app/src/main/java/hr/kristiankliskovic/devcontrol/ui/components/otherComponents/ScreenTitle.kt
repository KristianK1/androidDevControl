package hr.kristiankliskovic.devcontrol.ui.components.otherComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R

@Composable
fun ScreenTitle(
    screenTitle: String,
    connectedToWS: Boolean?,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
//            .background(Color.Green.copy(alpha = 0.2f))
            .height(dimensionResource(id = R.dimen.topBarHeight))
            .padding(
                vertical = 0.dp,
                horizontal = 10.dp
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = screenTitle,
            fontSize = 40.sp,
        )
    }
}
