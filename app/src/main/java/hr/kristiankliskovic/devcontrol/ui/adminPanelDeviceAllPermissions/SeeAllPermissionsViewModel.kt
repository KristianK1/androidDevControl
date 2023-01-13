package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions

import androidx.lifecycle.ViewModel
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.mapper.SeeAllPermissionsMapper

class SeeAllPermissionsViewModel(
    val deviceId: Int,
    val deviceRepository: DeviceRepository,
    val mapper: SeeAllPermissionsMapper,
): ViewModel() {

}
