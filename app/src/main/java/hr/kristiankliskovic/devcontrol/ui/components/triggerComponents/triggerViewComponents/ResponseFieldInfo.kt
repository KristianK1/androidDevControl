package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.triggerViewComponents

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.ERGBTriggerType_context
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents.TriggerItemLine

sealed class ResponseFieldInfoViewState

data class ResponseNumericFieldInfoViewState(
    val fieldValue: Float,
    val prefix: String,
    val sufix: String,
) : ResponseFieldInfoViewState()

data class ResponseTextFieldInfoViewState(
    val fieldValue: String,
) : ResponseFieldInfoViewState()

data class ResponseButtonFieldInfoViewState(
    val fieldValue: Boolean,
) : ResponseFieldInfoViewState()

data class ResponseMCFieldInfoViewState(
    val stateId: Int,
    val stateName: String,
) : ResponseFieldInfoViewState()

data class ResponseRGBFieldInfoViewState(
    val fieldValue: Int,
    val context: Int,
) : ResponseFieldInfoViewState()

@Composable
fun ResponseFieldInfo(
    viewState: ResponseFieldInfoViewState,
) {
    when (viewState) {
        is ResponseNumericFieldInfoViewState -> {
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseFieldType_propertyName),
                propertyValue = stringResource(id = R.string.getAllTriggersScreen_triggerFieldType_numeric),
            )
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseFieldValue_propertyName),
                propertyValue = "${viewState.prefix} ${viewState.fieldValue} ${viewState.sufix}"
            )
        }
        is ResponseTextFieldInfoViewState -> {
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseFieldType_propertyName),
                propertyValue = stringResource(id = R.string.getAllTriggersScreen_triggerFieldType_text)
            )
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseFieldValue_propertyName),
                propertyValue = "\"${viewState.fieldValue}\""
            )
        }
        is ResponseButtonFieldInfoViewState -> {
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseFieldType_propertyName),
                propertyValue = stringResource(id = R.string.getAllTriggersScreen_triggerFieldType_button)
            )
        }
        is ResponseMCFieldInfoViewState -> {
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseFieldType_propertyName),
                propertyValue = stringResource(id = R.string.getAllTriggersScreen_triggerFieldType_MC)
            )
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseFieldValue_propertyName),
                propertyValue = "${viewState.stateName} (id: ${viewState.stateId})",
            )
        }
        is ResponseRGBFieldInfoViewState -> {
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseFieldType_propertyName),
                propertyValue = stringResource(id = R.string.getAllTriggersScreen_triggerFieldType_RGB)
            )
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_rgbTriggerType_equalPropertyName),
                propertyValue = stringResource(id = if (viewState.context == ERGBTriggerType_context.R.ordinal) R.string.rgbTriggerContext_R else if (viewState.context == ERGBTriggerType_context.G.ordinal) R.string.rgbTriggerContext_G else R.string.rgbTriggerContext_B)
            )
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerResponseFieldValue_propertyName),
                propertyValue = "${viewState.fieldValue}"
            )
        }
    }
}

