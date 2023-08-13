package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.triggerViewComponents

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents.TriggerItemLine
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.NotificationEmailViewState

data class EmailInfoViewState(
    val subject: String,
    val content: String,
)

@Composable
fun EmailInfo(
    viewState: EmailInfoViewState,
) {
    TriggerItemLine(
        propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseType_propertyName),
        propertyValue = stringResource(id = R.string.getAllTriggersScreen_triggerResponseType_email_propertyName)
    )
    TriggerItemLine(
        propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseType_email_subject_propertyName),
        propertyValue =viewState.subject,
    )
    TriggerItemLine(
        propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseType_email_content_propertyName),
        propertyValue =viewState.content,
    )
}
