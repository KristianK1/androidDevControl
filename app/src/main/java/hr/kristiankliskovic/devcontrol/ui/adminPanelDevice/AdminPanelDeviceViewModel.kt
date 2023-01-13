package hr.kristiankliskovic.devcontrol.ui.adminPanelDevice

import androidx.lifecycle.ViewModel

class AdminPanelDeviceViewModel(
    val deviceId: Int,
): ViewModel() {
    val state = AdminPanelDeviceViewState(deviceId = deviceId)
}
