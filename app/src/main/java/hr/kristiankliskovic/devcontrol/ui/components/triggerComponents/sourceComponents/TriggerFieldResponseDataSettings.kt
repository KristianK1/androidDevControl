package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.OutlineTextWrapper
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.RadioButtonRow
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.*
import io.ktor.http.websocket.*

@Composable
fun TriggerFieldSourceDataSettings(
    viewState: TriggerResponseSettingsViewState?,

    setNumericValue: (Float) -> Unit,
    setTextValue: (String) -> Unit,
    setButtonValue: (Boolean) -> Unit,
    setMCvalue: (Int) -> Unit,
    setRGBValue: (Int) -> Unit,
    setRGBTriggerContext: (ERGBTriggerType_context) -> Unit,
) {
    if (viewState == null) return

    when (viewState) {
        is NumericTriggerResponseViewState -> {
            ChooseNumericValuePopup(
                minValue = viewState.minimum,
                maxValue = viewState.maximum,
                step = viewState.step,
                prefix = viewState.prefix,
                sufix = viewState.sufix,
                chosenValue = viewState.value.value,
                chosen = {
                    setNumericValue(it)
                }
            )
        }
        is TextTriggerResponseViewState -> {
            OutlineTextWrapper(
                label = stringResource(id = R.string.textTriggerValue_label),
                placeholder = stringResource(id = R.string.textTriggerValue_placeholder),
                onChange = {
                    setTextValue(it)
                }
            )
        }
        is BooleanTriggerResponseViewState -> {
            RadioButtonRow(
                selected = viewState.value.value,
                text = stringResource(id = R.string.buttonTriggerResponseType_true),
                onClick = {
                    setButtonValue(true)
                }
            )
            RadioButtonRow(
                selected = !viewState.value.value,
                text = stringResource(id = R.string.buttonTriggerResponseType_false),
                onClick = {
                    setButtonValue(false)
                }
            )
        }
        is MCTriggerResponseViewState -> {
            ChooseTextValuePopup(
                values = viewState.values,
                valueChosen = if (viewState.value.value != null) "${viewState.value.value}: ${viewState.values[viewState.value.value!!]}" else null,
                chosen = {
                    setMCvalue(it)
                }
            )
        }
        is RGBTriggerResponseViewState -> {
            RadioButtonRow(
                selected = viewState.contextType.value == ERGBTriggerType_context.R,
                text = stringResource(id = R.string.rgbTriggerContext_R),
                onClick = {
                    setRGBTriggerContext(ERGBTriggerType_context.R)
                }
            )
            RadioButtonRow(
                selected = viewState.contextType.value == ERGBTriggerType_context.G,
                text = stringResource(id = R.string.rgbTriggerContext_G),
                onClick = {
                    setRGBTriggerContext(ERGBTriggerType_context.G)
                }
            )
            RadioButtonRow(
                selected = viewState.contextType.value == ERGBTriggerType_context.B,
                text = stringResource(id = R.string.rgbTriggerContext_B),
                onClick = {
                    setRGBTriggerContext(ERGBTriggerType_context.B)
                }
            )

            ChooseNumericValuePopup(
                minValue = 0f,
                maxValue = 255f,
                step = 1f,
                prefix = "",
                sufix = "",
                chosenValue = if (viewState.value.value != null) viewState.value.value!!.toFloat() else null,
                chosen = {
                    setRGBValue(it.toInt())
                }
            )
        }
    }
}
