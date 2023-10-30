package hr.kristiankliskovic.devcontrol.ui.components.otherComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
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
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(
                dimensionResource(id = R.dimen.deviceNameAndStatusComponent_Row_margin),
            )
            .clip(Shapes.small)
            .background(
                color = Color.LightGray
            )
            .fillMaxWidth()
            .clickable {
                onClick(item.deviceId)
            }
            .padding(
                horizontal = dimensionResource(id = R.dimen.deviceNameAndStatusComponent_Row_padding_horizontal),
                vertical = dimensionResource(id = R.dimen.deviceNameAndStatusComponent_Row_padding_vertical)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.deviceName,
            fontSize = 25.sp,
            modifier = Modifier
                .weight(
                    weight = 1f,
                    fill = true
                )
                .height(IntrinsicSize.Max)
        )
        DeviceStatus(
            state = item.deviceStatus,
            modifier = Modifier
                .weight(
                    weight = 1f,
                    fill = false
                )
                .size(
                    width = dimensionResource(id = R.dimen.DeviceStatusComponent_width),
                    height = dimensionResource(id = R.dimen.DeviceStatusComponent_height)
                )
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
