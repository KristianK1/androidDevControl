package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission

import hr.kristiankliskovic.devcontrol.model.Device

data class AddPermissionViewState(
    val deviceId: Int,
    val device: AddPermissionDeviceViewState,
    val users: List<AddPermissionUserViewState>,
){
    companion object{
        fun getEmptyObject(): AddPermissionViewState{
            return AddPermissionViewState(
                deviceId = 0,
                device = AddPermissionDeviceViewState.getEmptyObject(),
                users = listOf(),
            )
        }
    }
}

data class AddPermissionDeviceViewState(
    val groups: List<AddPermissionGroupViewState>,
    val complexGroups: List<AddPermissionComplexGroupViewState>,
){
    companion object{
        fun getEmptyObject(): AddPermissionDeviceViewState{
            return AddPermissionDeviceViewState(
                groups = listOf(),
                complexGroups = listOf(),
            )
        }
    }
}

data class AddPermissionGroupViewState(
    val groupId: Int,
    val groupName: String,
    val fields: List<AddPermissionFieldViewState>,
)

data class AddPermissionFieldViewState(
    val fieldId: Int,
    val fieldName: String,
)

data class AddPermissionComplexGroupViewState(
    val complexGroupId: Int,
    val complexGroupName: String,
)

data class AddPermissionUserViewState(
    val userId: Int,
    val username: String,
)
