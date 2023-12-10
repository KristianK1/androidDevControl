package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.triggerViewComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents.TriggerItemLine
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes

data class TriggerItemViewState(
    val id: Int,
    val name: String,
    val sourceType: ETriggerSourceType,

    val sourceAddressViewState: TriggerView_AddressViewState?,
    val sourceFieldInfo: SourceFieldInfoViewState?,

    val timeTriggerInfo: TimeTriggerInfoViewState?,

    val responseType: ETriggerResponseType,
    val responseAddressViewState: TriggerView_AddressViewState?,
    val responseFieldInfoViewState: ResponseFieldInfoViewState?,
    val emailInfoViewState: EmailInfoViewState?,
    val mobileNotificationViewState: MobileNotificationInfoViewState?,
)

@Composable
fun TriggerItem(
    viewState: TriggerItemViewState,
    deleteTrigger: (Int) -> Unit,
) {
    Column(
        Modifier
            .padding(dimensionResource(id = R.dimen.triggerItem_margin))
            .clip(Shapes.small)
            .border(
                color = MaterialTheme.colorScheme.inverseSurface,
                width = dimensionResource(id = R.dimen.triggerItem_border_width)
            )
            .background(
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.8f)
            )
            .padding(dimensionResource(id = R.dimen.triggerItem_padding))
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            ) {
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerName_propertyName),
                propertyValue = viewState.name,
                increasedFont = true,
            )
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        deleteTrigger(viewState.id)
                    },
                tint = MaterialTheme.colorScheme.inverseSurface,
            )
        }

        TriggerItemLine(
            propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerId_propertyName),
            propertyValue = "${viewState.id}",
        )

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

        if (viewState.sourceAddressViewState != null) {
            TriggerView_SourceAddress(viewState = viewState.sourceAddressViewState)
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
                if (viewState.responseAddressViewState != null) {
                    TriggerView_ResponseAddress(viewState = viewState.responseAddressViewState)
                }
                if (viewState.responseFieldInfoViewState != null)
                    ResponseFieldInfo(viewState.responseFieldInfoViewState)
            }
            ETriggerResponseType.Email -> {
                if (viewState.emailInfoViewState != null)
                    EmailInfo(viewState.emailInfoViewState)
            }
            else -> {
                if (viewState.mobileNotificationViewState != null)
                    MobileNotificationInfo(viewState.mobileNotificationViewState)
            }
        }
    }
}
