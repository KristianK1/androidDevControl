package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .border(
                width = 1.dp,
                shape = RectangleShape,
                color = Color.Gray,
            )
            .padding(dimensionResource(id = R.dimen.addTriggerBorderPadding)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (viewState) {
            is NumericTriggerResponseViewState -> {
                Text(
                    text = stringResource(id = R.string.addTriggerScreen_valueType_title_numeric_response),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp
                )
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
                Text(
                    text = stringResource(id = R.string.addTriggerScreen_valueType_title_text_response),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp
                )
                OutlineTextWrapper(
                    label = stringResource(id = R.string.textTriggerValue_label),
                    placeholder = stringResource(id = R.string.textTriggerValue_placeholder),
                    onChange = {
                        setTextValue(it)
                    }
                )
            }
            is BooleanTriggerResponseViewState -> {
                Text(
                    text = stringResource(id = R.string.addTriggerScreen_valueType_title_button_response),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp
                )
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
                Text(
                    text = stringResource(id = R.string.addTriggerScreen_valueType_title_multipleC_response),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp
                )
                ChooseTextValuePopup(
                    values = viewState.values,
                    valueChosen = if (viewState.value.value != null) "${viewState.value.value}: ${viewState.values[viewState.value.value!!]}" else null,
                    chosen = {
                        setMCvalue(it)
                    }
                )
            }
            is RGBTriggerResponseViewState -> {
                Text(
                    text = stringResource(id = R.string.addTriggerScreen_valueType_title_RGB_context),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp
                )
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
                Text(
                    text = stringResource(id = R.string.addTriggerScreen_valueType_title_RGB_response),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp
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
}
