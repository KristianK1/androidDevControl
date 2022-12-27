package hr.kristiankliskovic.devcontrol.navigation

const val DEVICE_CONTROLS_ROUTE = "DeviceControls"
const val DEVICE_CONTROLS_ID_KEY = "deviceId"
const val DEVICE_CONTROLS_ROUTE_WITH_PARAMS  ="$DEVICE_CONTROLS_ROUTE/$DEVICE_CONTROLS_ID_KEY"

object DeviceControlsDestination: DevControlAppDestination(DEVICE_CONTROLS_ROUTE_WITH_PARAMS){
    fun createNavigationRoute(deviceId: Int): String ="$DEVICE_CONTROLS_ROUTE/$deviceId"
}
