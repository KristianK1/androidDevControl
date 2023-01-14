package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.mapper

import hr.kristiankliskovic.devcontrol.data.network.model.UserPermissionsForDeviceResponse
import hr.kristiankliskovic.devcontrol.model.AllUserRightsToDevice
import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.model.User
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAllPermissions.*

class SeeAllPermissionsMapperImpl : SeeAllPermissionsMapper {
    override fun toSeeAllPermissionViewState(
        device: Device,
        permissions: UserPermissionsForDeviceResponse?,
    ): SeeAllPermissionsViewState {
        if (permissions == null) return SeeAllPermissionsViewState.getEmptyObject()

        return SeeAllPermissionsViewState(
            deviceId = device.deviceId,
            deviceName = device.deviceName,
            permissions = permissions.userPermissions.map { permission ->
                UserPermissionViewState(
                    userId = permission.userId,
                    username = permission.username,
                    readOnly = permission.readOnly,
                )
            },
            groupPermissions = permissions.groups.map { groupPermission ->
                UserPermissionToGroupViewState(
                    groupId = groupPermission.groupId,
                    groupName = groupPermission.groupName,
                    permissions = groupPermission.userPermissions.map { permission ->
                        UserPermissionViewState(
                            userId = permission.userId,
                            username = permission.username,
                            readOnly = permission.readOnly,
                        )
                    },
                    fields = groupPermission.fields.map { fieldPermission ->
                        UserPermissionToFieldViewState(
                            fieldId = fieldPermission.fieldId,
                            fieldName = fieldPermission.fieldName,
                            fieldType = fieldPermission.fieldType,
                            permissions = fieldPermission.userPermissions.map { permission ->
                                UserPermissionViewState(
                                    userId = permission.userId,
                                    username = permission.username,
                                    readOnly = permission.readOnly,
                                )
                            }
                        )
                    }
                )
            },
            complexGroupPermissions = permissions.complexGroups.map { complexGroupPermission ->
                UserPermissionToComplexGroupViewState(
                    complexGroupId = complexGroupPermission.complexGroupId,
                    complexGroupName = complexGroupPermission.complexGroupName,
                    permissions = complexGroupPermission.userPermissions.map { permission ->
                        UserPermissionViewState(
                            userId = permission.userId,
                            username = permission.username,
                            readOnly = permission.readOnly,
                        )
                    }
                )
            }
        )
    }
}
