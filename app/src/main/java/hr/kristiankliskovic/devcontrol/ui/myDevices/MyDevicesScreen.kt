package hr.kristiankliskovic.devcontrol.ui.myDevices

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.mock.getDeviceListMockData
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.DeviceNameAndStatus

@Composable
fun MyDevicesRoute() {
    val myDevices by remember {
        mutableStateOf(getDeviceListMockData())
    }
    MyDevicesScreen(
        state = myDevices,
        navigateToDevice = {

        }
    )
}

@Composable
fun MyDevicesScreen(
    state: MyDevicesViewState,
    navigateToDevice: (Int) -> Unit,
) {
    LazyColumn {
        items(
            items = state.devices
        ) { item ->
            DeviceNameAndStatus(
                item = item,
                onClick = {
                    navigateToDevice(item.deviceId)
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.myDevicesScreen_list_line_thickness))
                    .background(colorResource(id = R.color.myDevicesScreen_list_line))
            )
        }
    }
}


@Preview
@Composable
fun PreviewMyDeviceScreen() {
    MyDevicesScreen(
        state = getDeviceListMockData(),
        navigateToDevice = {
            Log.i("myDevicesScreen_click", "$it")
        }
    )
}
