package hr.kristiankliskovic.devcontrol.ui.components.otherComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.res.dimensionResource
import hr.kristiankliskovic.devcontrol.R

@Composable
fun ScreenSubtitle(
    subtitle: String,
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Black)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Green.copy(alpha = 0.2f))
                .height(30.dp)
                .padding(
                    vertical = 0.dp,
                    horizontal = 10.dp
                ),
            elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.screenSubTitleComponent_elevation)),
        ) {

            Text(
                text = subtitle,
                fontSize = 20.sp,
            )
        }
    }
}
