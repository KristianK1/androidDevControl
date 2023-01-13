package hr.kristiankliskovic.devcontrol.ui.addNewDevice

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.OutlineTextWrapper
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes

@Composable
fun AddNewDeviceRoute(
    viewModel: AddNewDeviceViewModel,
) {
    AddNewDevice(
        addNewDevice = { deviceName, deviceKey ->
            viewModel.addNewDevice(
                deviceName = deviceName,
                deviceKey = deviceKey,
            )
        }
    )
}

@Composable
fun AddNewDevice(
    addNewDevice: (String, String?) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
    ) {
        var deviceName by remember { mutableStateOf("") }
        var deviceKey by remember { mutableStateOf("") }
        var autoGenerateKey by remember { mutableStateOf(true) }

        Spacer(modifier = Modifier
            .height(dimensionResource(id = R.dimen.addNewDeviceScreen_top_bottom_screen_padding))
        )
        OutlineTextWrapper(
            label = stringResource(id = R.string.addNewDeviceScreen_deviceNameHint),
            onChange = {
                deviceName = it
            }
        )
        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.addNewDeviceScreen_autoGenerateKeyBox_padding))
                .clickable {
                    autoGenerateKey = !autoGenerateKey
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = autoGenerateKey,
                onCheckedChange = null
            )
            Spacer(
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.addNewDeviceScreen_checkbox_spacer_width))
            )
            Text(
                text = stringResource(id = R.string.addNewDeviceScreen_autoGenerateKey),
                fontSize = 15.sp
            )
        }
        if (!autoGenerateKey) {
            OutlineTextWrapper(
                label = stringResource(id = R.string.addNewDeviceScreen_deviceKeyHint),
                onChange = {
                    deviceKey = it
                }
            )
        }

        Text(
            text = stringResource(id = R.string.addNewDeviceScreen_addDevice_button),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.changePasswordScreen_button_margin))
                .background(
                    color = Color.LightGray,
                    shape = Shapes.small,
                )
                .clickable {
                    if (autoGenerateKey) {
                        addNewDevice(deviceName, null)
                    } else {
                        addNewDevice(deviceName, deviceKey)
                    }
                }
                .padding(
                    horizontal = dimensionResource(id = R.dimen.addNewDeviceScreen_addDevice_button_padding_horizontal),
                    vertical = dimensionResource(id = R.dimen.addNewDeviceScreen_addDevice_button_padding_vertical)
                )
        )

        Spacer(modifier = Modifier
            .height(dimensionResource(id = R.dimen.addNewDeviceScreen_top_bottom_screen_padding))
        )
    }
}

@Preview
@Composable
fun PreviewAddNewDeviceScreen() {
    AddNewDevice(
        addNewDevice = { _, _ ->

        }
    )
}
