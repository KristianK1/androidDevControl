package hr.kristiankliskovic.devcontrol.ui.components.otherComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes

data class DeviceNameAndStatusViewState(
    val deviceId: Int,
    val deviceName: String,
    val deviceStatus: Boolean,
)

@Composable
fun DeviceNameAndStatus(
    item: DeviceNameAndStatusViewState,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(
                dimensionResource(id = R.dimen.deviceNameAndStatusComponent_Row_margin),
            )
            .clip(Shapes.small)
            .background(
                color = if(item.deviceStatus) Color.Green.copy(alpha = 0.5f) else Color.Red.copy(alpha = 0.5f)
            )
            .fillMaxWidth()
            .clickable {
                onClick(item.deviceId)
            }
            .padding(
                dimensionResource(id = R.dimen.deviceNameAndStatusComponent_Row_padding_horizontal),
            ),
    ) {
        Text(
            text = item.deviceName,
            fontSize = 25.sp,
            color = MaterialTheme.colorScheme.inverseSurface,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun PreviewDeviceNameAndStatus() {
    val state = DeviceNameAndStatusViewState(
        deviceId = 9,
        deviceName = "ghgg",
        deviceStatus = true,
    )
    DeviceNameAndStatus(
        item = state,
        onClick = {

        },
    )
}
