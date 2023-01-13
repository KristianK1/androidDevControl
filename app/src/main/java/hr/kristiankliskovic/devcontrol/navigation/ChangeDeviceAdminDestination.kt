package hr.kristiankliskovic.devcontrol.navigation

const val CHANGE_DEVICE_ADMIN_ROUTE = "change_device_admin"
const val CHANGE_DEVICE_ADMIN_ID_KEY = "device_id"
const val CHANGE_DEVICE_ADMIN_ROUTE_WITH_PARAMS = "$CHANGE_DEVICE_ADMIN_ROUTE/{$CHANGE_DEVICE_ADMIN_ID_KEY}"

object ChangeDeviceAdminDestination: DevControlAppDestination(CHANGE_DEVICE_ADMIN_ROUTE_WITH_PARAMS){
    fun createNavigationRoute(deviceId: Int): String = "$CHANGE_DEVICE_ADMIN_ROUTE/$deviceId"
}
