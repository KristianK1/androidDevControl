package hr.kristiankliskovic.devcontrol.navigation

const val SEE_ALL_PERMISSIONS_ROUTE = "see_all_permissions"
const val SEE_ALL_PERMISSIONS_ID_KEY = "device_key"
const val SEE_ALL_PERMISSIONS_ROUTE_WITH_PARAMS = "$SEE_ALL_PERMISSIONS_ROUTE/{$SEE_ALL_PERMISSIONS_ID_KEY}"

object seeAllPermissionsDestination : DevControlAppDestination(SEE_ALL_PERMISSIONS_ROUTE_WITH_PARAMS) {
    fun createNavigationRoute(deviceId: Int): String = "$SEE_ALL_PERMISSIONS_ROUTE/$deviceId";
}
