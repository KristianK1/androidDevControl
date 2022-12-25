package hr.kristiankliskovic.devcontrol.ui.deviceControls.mapper

import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.ui.deviceControls.DeviceControlsViewState

interface DeviceControlsMapper {
    fun toDeviceControlsViewState(device: Device): DeviceControlsViewState
}
