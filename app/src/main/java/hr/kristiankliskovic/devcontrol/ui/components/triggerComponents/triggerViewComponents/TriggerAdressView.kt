package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.triggerViewComponents

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents.TriggerItemLine

data class TriggerView_AddressViewState(
    val sourceDeviceId: Int,
    val sourceDeviceName: String,

    val sourceGroupId: Int,
    val sourceGroupName: String,

    val sourceStateId: Int? = null,
    val sourceStateName: String? = null,

    val sourceFieldId: Int,
    val sourceFieldName: String,
)

@Composable
fun TriggerView_SourceAddress(
    viewState: TriggerView_AddressViewState
){
    TriggerItemLine(
        propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerSourceFieldAddress_device_propertyName),
        propertyValue = "${viewState.sourceDeviceName} (id: ${viewState.sourceDeviceId})"
    )

    TriggerItemLine(
        propertyName = stringResource(id = if(viewState.sourceStateId == null) R.string.getAllTriggersScreen_triggerSourceFieldAddress_group_propertyName else R.string.getAllTriggersScreen_triggerSourceFieldAddress_complexGroup_propertyName),
        propertyValue = "${viewState.sourceGroupName} (id: ${viewState.sourceGroupId})"
    )
    if(viewState.sourceStateId != null){
        TriggerItemLine(
            propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerSourceFieldAddress_state_propertyName),
            propertyValue = "${viewState.sourceStateName} (id: ${viewState.sourceStateId})"
        )
    }
    TriggerItemLine(
        propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerSourceFieldAddress_field_propertyName),
        propertyValue = "${viewState.sourceFieldName} (id: ${viewState.sourceFieldId})"
    )
}

@Composable
fun TriggerView_ResponseAddress(
    viewState: TriggerView_AddressViewState
){
    TriggerItemLine(
        propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseFieldAddress_device_propertyName),
        propertyValue = "${viewState.sourceDeviceName} (id: ${viewState.sourceDeviceId})"
    )

    TriggerItemLine(
        propertyName = stringResource(id = if(viewState.sourceStateId == null) R.string.getAllTriggersScreen_triggerResponseFieldAddress_group_propertyName else R.string.getAllTriggersScreen_triggerResponseFieldAddress_complexGroup_propertyName),
        propertyValue = "${viewState.sourceGroupName} (id: ${viewState.sourceGroupId})"
    )
    if(viewState.sourceStateId != null){
        TriggerItemLine(
            propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseFieldAddress_state_propertyName),
            propertyValue = "${viewState.sourceStateName} (id: ${viewState.sourceStateId})"
        )
    }
    TriggerItemLine(
        propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseFieldAddress_field_propertyName),
        propertyValue = "${viewState.sourceFieldName} (id: ${viewState.sourceFieldId})"
    )
}