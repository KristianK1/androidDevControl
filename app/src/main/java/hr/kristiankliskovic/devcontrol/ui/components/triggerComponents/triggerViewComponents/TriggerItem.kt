package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.triggerViewComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents.TriggerItemLine
import java.util.*

data class TriggerItemViewState(
    val id: Int,
    val name: String,
    val sourceType: ETriggerSourceType,

    val sourceAddressViewState: TriggerView_AddressViewState?,
    val sourceFieldInfo: SourceFieldInfoViewState?,

    val timeTriggerInfo: TimeTriggerInfoViewState?,

    val responseType: ETriggerResponseType,
    val responseAddressViewState: TriggerView_AddressViewState?,


//    val responseDataViewState: List<TriggerItemLineViewState>,
)

@Composable
fun TriggerItem(
    viewState: TriggerItemViewState,
    deleteTrigger: (Int) -> Unit,
) {
    Column {
        Row {
            Column {
                TriggerItemLine(
                    propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerName_propertyName),
                    propertyValue = viewState.name,
                    increasedFont = true,
                )
                TriggerItemLine(
                    propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerId_propertyName),
                    propertyValue = "${viewState.id}",
                )
            }

            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        deleteTrigger(viewState.id)
                    }
            )
        }

        if (viewState.sourceType == ETriggerSourceType.FieldInGroup || viewState.sourceType == ETriggerSourceType.FieldInComplexGroup) {
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerSourceType_propertyName),
                propertyValue = stringResource(id = R.string.getAllTriggersScreen_triggerSourceType_fieldValue_propertyName)
            )
        } else {
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerSourceType_propertyName),
                propertyValue = stringResource(id = R.string.getAllTriggersScreen_triggerSourceType_timeTrigger_propertyName)
            )
        }

        if (viewState.sourceFieldInfo != null) {
            SourceFieldInfo(viewState = viewState.sourceFieldInfo)
        }
        if (viewState.timeTriggerInfo != null) {
            TimeTriggerInfo(
                viewState = viewState.timeTriggerInfo
            )
        }

        when (viewState.responseType) {
            ETriggerResponseType.SettingValue_fieldInGroup, ETriggerResponseType.SettingValue_fieldInComplexGroup -> {
                TriggerItemLine(
                    propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseType_propertyName),
                    propertyValue = stringResource(id = R.string.getAllTriggersScreen_triggerResponseType_settingValue_propertyName)
                )
            }
            ETriggerResponseType.Email -> {
                TriggerItemLine(
                    propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseType_propertyName),
                    propertyValue = stringResource(id = R.string.getAllTriggersScreen_triggerResponseType_email_propertyName)
                )
            }
            else -> {
                TriggerItemLine(
                    propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseType_propertyName),
                    propertyValue = stringResource(id = R.string.getAllTriggersScreen_triggerResponseType_mobileNotification_propertyName)
                )
            }
        }
    }
}
