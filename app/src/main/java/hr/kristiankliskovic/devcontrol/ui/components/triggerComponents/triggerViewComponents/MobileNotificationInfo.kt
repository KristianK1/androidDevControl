package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.triggerViewComponents

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents.TriggerItemLine

data class MobileNotificationInfoViewState(
    val title: String,
    val content: String,
)

@Composable
fun MobileNotificationInfo(
    viewState: MobileNotificationInfoViewState,
) {
    TriggerItemLine(
        propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseType_propertyName),
        propertyValue = stringResource(id = R.string.getAllTriggersScreen_triggerResponseType_mobileNotification_propertyName)
    )
    TriggerItemLine(
        propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseType_mobileNotification_title_propertyName),
        propertyValue = viewState.title,
    )
    TriggerItemLine(
        propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseType_mobileNotification_content_propertyName),
        propertyValue = viewState.content,
    )
}
