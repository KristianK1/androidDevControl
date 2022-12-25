package hr.kristiankliskovic.devcontrol.ui.components.otherComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R

@Composable
fun TextListOption(
    text: String,
    func: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                func()
            }
            .padding(dimensionResource(id = R.dimen.userSettings_option_padding)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 25.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
