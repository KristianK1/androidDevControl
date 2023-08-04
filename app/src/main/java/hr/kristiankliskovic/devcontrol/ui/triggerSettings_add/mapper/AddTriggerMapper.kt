package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.mapper

import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.TriggerSourceDevicesViewState

interface AddTriggerMapper {
    fun toAddTriggerViewState(devices: List<Device>): TriggerSourceDevicesViewState
}
