package hr.kristiankliskovic.devcontrol.navigation

const val ADMIN_PANEL_DEVICE_ROUTE = "Admin_panel_device"
const val ADMIN_PANEL_DEVICE_ID_KEY = "device_id"
const val ADMIN_PANEL_DEVICE_ROUTE_WITH_PARAMS  ="$ADMIN_PANEL_DEVICE_ROUTE/{$ADMIN_PANEL_DEVICE_ID_KEY}"

object AdminPanelDeviceDestination: DevControlAppDestination(ADMIN_PANEL_DEVICE_ROUTE_WITH_PARAMS){
    fun createNavigationRoute(deviceId: Int): String ="$ADMIN_PANEL_DEVICE_ROUTE/$deviceId"
}
