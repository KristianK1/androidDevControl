package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import hr.kristiankliskovic.devcontrol.model.User
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission.mapper.AddPermissionMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AddPermissionViewModel(
    val deviceId: Int,
    val mapper: AddPermissionMapper,
    val deviceRepository: DeviceRepository,
    val userRepository: UserRepository,
) : ViewModel() {

    val viewState: StateFlow<AddPermissionViewState> = combine(
        userRepository.getOtherUsers(),
        deviceRepository.getDevice(deviceId)
    ) { users: List<User>, device: Device ->
        mapper.toAddPermissionViewState(device = device, users = users)
    }.stateIn(viewModelScope, SharingStarted.Lazily, AddPermissionViewState.getEmptyObject())

    fun addUserPermissionToDevice(
        userId: Int,
        deviceId: Int,
        readOnly: Boolean,
    ) {
        Log.i("perms", "two")
        viewModelScope.launch {
            deviceRepository.addUserPermissionToDevice(
                userId = userId,
                deviceId = deviceId,
                readOnly = readOnly,
            )
        }
    }

    fun addUserPermissionToGroup(
        userId: Int,
        deviceId: Int,
        groupId: Int,
        readOnly: Boolean,
    ) {
        Log.i("perms", "two")
        viewModelScope.launch {
            deviceRepository.addUserPermissionToGroup(
                userId = userId,
                deviceId = deviceId,
                groupId = groupId,
                readOnly = readOnly
            )
        }
    }

    fun addUserPermissionToField(
        userId: Int,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        readOnly: Boolean,
    ) {
        Log.i("perms", "two")
        viewModelScope.launch {
            deviceRepository.addUserPermissionToField(
                userId = userId,
                deviceId = deviceId,
                groupId = groupId,
                fieldId = fieldId,
                readOnly = readOnly
            )
        }
    }

    fun addUserPermissionToComplexGroup(
        userId: Int,
        deviceId: Int,
        complexGroupId: Int,
        readOnly: Boolean,
    ) {
        Log.i("perms", "two")
        viewModelScope.launch {
            deviceRepository.addUserPermissionToComplexGroup(
                userId = userId,
                deviceId = deviceId,
                complexGroupId = complexGroupId,
                readOnly = readOnly
            )
        }
    }
}
