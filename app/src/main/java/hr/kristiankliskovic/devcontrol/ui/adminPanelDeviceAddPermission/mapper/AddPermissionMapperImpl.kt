package hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission.mapper

import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.adminPanelDeviceAddPermission.*

class AddPermissionMapperImpl : AddPermissionMapper {
    override fun toAddPermissionViewState(device: Device, users: List<User>): AddPermissionViewState{
        return AddPermissionViewState(
            deviceId = device.deviceId,
            device = AddPermissionDeviceViewState(
                groups = device.groups.map { group ->
                    AddPermissionGroupViewState(
                        groupId = group.groupId,
                        groupName = group.groupName,
                        fields = group.fields.map { field ->
                            when (field) {
                                is NumericDeviceField ->
                                    AddPermissionFieldViewState(
                                        fieldId = field.fieldId,
                                        fieldName = field.fieldName,
                                    )
                                is TextDeviceField ->
                                    AddPermissionFieldViewState(
                                        fieldId = field.fieldId,
                                        fieldName = field.fieldName,
                                    )
                                is RGBDeviceField ->
                                    AddPermissionFieldViewState(
                                        fieldId = field.fieldId,
                                        fieldName = field.fieldName,
                                    )
                                is MultipleChoiceDeviceField ->
                                    AddPermissionFieldViewState(
                                        fieldId = field.fieldId,
                                        fieldName = field.fieldName,
                                    )
                                is ButtonDeviceField ->
                                    AddPermissionFieldViewState(
                                        fieldId = field.fieldId,
                                        fieldName = field.fieldName,
                                    )
                            }

                        }
                    )
                },
                complexGroups = device.complexGroups.map { complexGroup ->
                    AddPermissionComplexGroupViewState(
                        complexGroupId = complexGroup.complexGroupId,
                        complexGroupName = complexGroup.groupName,
                    )
                }
            ),
            users = users.map{ user ->
                AddPermissionUserViewState(
                    userId = user.id,
                    username = user.username,
                )
            }
        )
    }
}
