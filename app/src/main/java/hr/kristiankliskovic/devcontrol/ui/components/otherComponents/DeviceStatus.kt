package hr.kristiankliskovic.devcontrol.ui.components.otherComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R

@Composable
fun DeviceStatus(
    state: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(colorResource(id = if(state) R.color.deviceStatusOnline else R.color.deviceStatusOffline)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = if (state) R.string.deviceStatusComponent_online_statusText else R.string.deviceStatusComponent_offline_statusText),
            fontSize = 30.sp,
        )
    }
}

@Preview
@Composable
fun PreviewDeviceStatus() {
    DeviceStatus(state = true)
}
