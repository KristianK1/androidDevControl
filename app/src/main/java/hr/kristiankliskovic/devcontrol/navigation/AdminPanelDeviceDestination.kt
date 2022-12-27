package hr.kristiankliskovic.devcontrol.navigation

const val ADMIN_PANEL_DEVICE_ROUTE = "AdminPanelDevice"
const val ADMIN_PANEL_DEVICE_ID_KEY = "deviceId"
const val ADMIN_PANEL_DEVICE_ROUTE_WITH_PARAMS  ="$DEVICE_CONTROLS_ROUTE/$ADMIN_PANEL_DEVICE_ID_KEY"

object AdminPanelDeviceDestination: DevControlAppDestination(ADMIN_PANEL_DEVICE_ROUTE_WITH_PARAMS){
    fun createNavigationRoute(deviceId: Int): String ="$ADMIN_PANEL_DEVICE_ROUTE/$deviceId"
}
