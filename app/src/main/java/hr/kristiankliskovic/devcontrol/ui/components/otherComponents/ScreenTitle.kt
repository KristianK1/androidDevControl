package hr.kristiankliskovic.devcontrol.ui.components.otherComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R

@Composable
fun ScreenTitle(
    screenTitle: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Green.copy(alpha = 0.2f))
            .height(50.dp)
            .padding(
                vertical = 0.dp,
                horizontal = 10.dp
            ),
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.screenTitleComponent_elevation)),
//        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = screenTitle,
            fontSize = 30.sp,
        )
    }
}
