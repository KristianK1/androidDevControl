package hr.kristiankliskovic.devcontrol.ui.components.otherComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*

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
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick(item.deviceId)
            }
            .padding(
                horizontal = 15.dp,
                vertical = 10.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.deviceName,
            fontSize = 25.sp,
            modifier = Modifier
                .height(IntrinsicSize.Max)
        )
        DeviceStatus(state = item.deviceStatus)
    }
}

@Preview
@Composable
fun PreviewDeviceNameAndStatus() {
    val state = DeviceNameAndStatusViewState(
        deviceId = 0,
        deviceName = "esp32_1",
        deviceStatus = true,
    )
    DeviceNameAndStatus(
        item = state,
        onClick = {

        },
    )
}
