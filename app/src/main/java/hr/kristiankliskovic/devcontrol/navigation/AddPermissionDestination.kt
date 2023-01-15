package hr.kristiankliskovic.devcontrol.navigation

const val ADD_PERMISSION_ROUTE = "add_permission"
const val ADD_PERMISSION_ID_KEY = "device_id"
const val ADD_PERMISSION_ROUTE_WITH_PARAMS = "$ADD_PERMISSION_ROUTE/{$ADD_PERMISSION_ID_KEY}"

object AddPermissionDestination: DevControlAppDestination(ADD_PERMISSION_ROUTE_WITH_PARAMS) {
    fun createNavigationRoute(deviceId: Int): String = "$ADD_PERMISSION_ROUTE/$deviceId"
}
