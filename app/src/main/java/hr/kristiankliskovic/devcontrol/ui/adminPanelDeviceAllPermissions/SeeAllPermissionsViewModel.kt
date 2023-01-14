package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.network.model.UserPermissionsForDeviceResponse
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission.AddPermissionViewState
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.mapper.SeeAllPermissionsMapper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SeeAllPermissionsViewModel(
    deviceId: Int,
    private val deviceRepository: DeviceRepository,
    private val mapper: SeeAllPermissionsMapper,
) : ViewModel() {
    init {
        viewModelScope.launch {
            deviceRepository.getUserPermissionsForDevice(deviceId)
        }
    }
    val viewState: StateFlow<SeeAllPermissionsViewState> = combine(
        deviceRepository.getDevice(deviceId),
        deviceRepository.allPermissionsForDeviceResponse,
    ) { device: Device, userPermissionsForDeviceResponse: UserPermissionsForDeviceResponse? ->
        Log.i("seeAllPerms", "viewstate mapping")
        mapper.toSeeAllPermissionViewState(device, userPermissionsForDeviceResponse)
    }.stateIn(viewModelScope, SharingStarted.Lazily, SeeAllPermissionsViewState.getEmptyObject())

    fun deleteUserPermissionToDevice(userId: Int, deviceId: Int){
        viewModelScope.launch {
            deviceRepository.deleteUserPermissionToDevice(
                userId = userId,
                deviceId = deviceId,
            )
            deviceRepository.getUserPermissionsForDevice(deviceId)
        }
    }

    fun deleteUserPermissionToGroup(
        userId: Int,
        deviceId: Int,
        groupId: Int,
    ){
        viewModelScope.launch {
            deviceRepository.deleteUserPermissionToGroup(
                userId = userId,
                deviceId = deviceId,
                groupId = groupId
            )
            deviceRepository.getUserPermissionsForDevice(deviceId)
        }
    }

    fun deleteUserPermissionToField(
        userId: Int,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
    ){
        viewModelScope.launch {
            deviceRepository.deleteUserPermissionToField(
                userId = userId,
                deviceId = deviceId,
                groupId = groupId,
                fieldId = fieldId,
            )
            deviceRepository.getUserPermissionsForDevice(deviceId)
        }
    }

    fun deleteUserPermissionToComplexGroup(
        userId: Int,
        deviceId: Int,
        complexGroupId: Int,
    ){
        viewModelScope.launch {
            deviceRepository.deleteUserPermissionToComplexGroup(
                userId = userId,
                deviceId = deviceId,
                complexGroupId = complexGroupId
            )
            deviceRepository.getUserPermissionsForDevice(deviceId)
        }
    }
}
