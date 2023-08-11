package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.mapper

import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.DeviceEntityViewState

interface AddTriggerMapper {
    fun devicesToEntityViewState(devices: List<Device>): List<DeviceEntityViewState>
    fun groupsToEntityViewState(groups: List<DeviceGroup>): List<DeviceEntityViewState>
    fun complexGroupsToEntityViewState(complexGroups: List<DeviceComplexGroup>, justWrites: Boolean): List<DeviceEntityViewState>
    fun complexGroupsStatesToEntityViewState(states: List<DeviceComplexGroupState>): List<DeviceEntityViewState>
    fun fieldsToEntityViewState(fields: List<BasicDeviceField>, justWrites: Boolean): List<DeviceEntityViewState>
}
