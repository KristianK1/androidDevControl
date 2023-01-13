package hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.ChangeDeviceAdminViewState
import hr.kristiankliskovic.devcontrol.ui.changeDeviceAdmin.mapper.ChangeDeviceAdminMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChangeDeviceAdminViewModel(
    mapper: ChangeDeviceAdminMapper,
    userRepository: UserRepository,
    val deviceRepository: DeviceRepository,
) : ViewModel() {

    val viewState: StateFlow<ChangeDeviceAdminViewState> =
        userRepository.getOtherUsers().mapLatest { users ->
            Log.i("chAdmin", "new users")
            Log.i("chAdmin", "${users.size}")
            mapper.toChangeDeviceAdminViewState(
                users = users,
            )
        }.stateIn(viewModelScope,
            SharingStarted.Lazily,
            ChangeDeviceAdminViewState.getEmptyViewState())


    fun changeDeviceAdmin(adminId: Int){
        viewModelScope.launch {
//            deviceRepository.changeDeviceAdmin(
//                deviceId = deviceRepository.adminPanelDeviceId!!,
//                adminId = adminId,
//            )
        }
    }
}
