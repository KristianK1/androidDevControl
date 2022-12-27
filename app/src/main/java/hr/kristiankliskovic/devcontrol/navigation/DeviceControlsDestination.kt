package hr.kristiankliskovic.devcontrol.navigation

const val DEVICE_CONTROLS_ROUTE = "device_controls"
const val DEVICE_CONTROLS_ID_KEY = "device_id"
const val DEVICE_CONTROLS_ROUTE_WITH_PARAMS = "$DEVICE_CONTROLS_ROUTE/{$DEVICE_CONTROLS_ID_KEY}"

object DeviceControlsDestination : DevControlAppDestination(DEVICE_CONTROLS_ROUTE_WITH_PARAMS) {
    fun createNavigationRoute(deviceId: Int): String = "$DEVICE_CONTROLS_ROUTE/$deviceId"
}
