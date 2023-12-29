package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.godaddy.android.colorpicker.ClassicColorPicker
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.OutlineTextWrapper
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.RadioButtonRow
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.*
import io.ktor.http.websocket.*

@Composable
fun TriggerFieldSourceDataSettings(
    viewState: TriggerResponseSettingsViewState?,

    setNumericValue: (Float) -> Unit,
    setTextValue: (String) -> Unit,
    setButtonValue: (Boolean) -> Unit,
    setMCvalue: (Int) -> Unit,
    setRGBValue: (RGBValue) -> Unit,
) {
    if (viewState == null) return
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
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
                TypeOfButtonResponse(
                    chooseType = {
                        setButtonValue(it)
                    },
                    typeSelected = viewState.value.value
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
                    text = stringResource(id = R.string.addTriggerScreen_valueType_title_RGB_response),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp
                )
                RGBDialog(
                    chosenValue = viewState.value.value,
                    selectValue = {
                        setRGBValue(it)
                    }
                )
            }
        }
    }
}


@Composable
fun TypeOfButtonResponse(
    chooseType: (Boolean) -> Unit,
    typeSelected: Boolean,
){
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .height(IntrinsicSize.Max)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f, true)
                    .fillMaxHeight()
                    .clickable {
                        chooseType(true)
                    }
                    .background(
                        color = if (typeSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.6f
                        )
                    )
                    .padding(5.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.buttonTriggerType_true),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.background,
                )
            }
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxHeight()
            )
            Box(
                modifier = Modifier
                    .weight(1f, true)
                    .fillMaxHeight()
                    .clickable {
                        chooseType(false)
                    }
                    .background(
                        color = if (!typeSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.6f
                        )
                    )
                    .padding(5.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.buttonTriggerType_false),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.background,
                )
            }
        }
    }
}


@Composable
fun RGBDialog(
    chosenValue: RGBValue?,
    selectValue: (RGBValue) -> Unit,
) {
    var rgb by remember {
        mutableStateOf(RGBValue(0, 0, 0))
    }
    var dialogOpen by remember {
        mutableStateOf(false)
    }

    if (dialogOpen) {
        AlertDialog(onDismissRequest = {
            dialogOpen = false
        },
            buttons = {
                Column(
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    ClassicColorPicker(
                        onColorChanged = {
                            val color = it.toColor()
                            rgb = RGBValue(
                                (color.red * 255).toInt(),
                                (color.green * 255).toInt(),
                                (color.blue * 255).toInt()
                            )
                        },
                        modifier = Modifier
                            .weight(0.8f)
                    )
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.background,
                            )
                            .padding(dimensionResource(id = R.dimen.dialog_confirm_button_margin))
                            .fillMaxWidth()
                            .clip(Shapes.small)
                            .clickable {
                                selectValue(rgb)
                                dialogOpen = false
                            }
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                            )
                            .padding(dimensionResource(id = R.dimen.dialog_confirm_button_padding))
                    ) {
                        Text(
                            text = "${stringResource(id = R.string.RGBFieldInput_set_value_button)} (${rgb.displayColorString()})",
                            color = MaterialTheme.colorScheme.background,
                            fontSize = 20.sp
                        )
                    }
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.fieldComponent_dialog_padding)),
            shape = Shapes.small,
            backgroundColor = Color.White,
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        )
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxHeight()
            .padding(dimensionResource(id = R.dimen.fieldComponent_button_padding))
            .clip(Shapes.small)
            .background(
                color = MaterialTheme.colorScheme.primary,
            )
            .clickable {
                dialogOpen = true
            }
    ) {
        Text(
            text = chosenValue?.displayColorString() ?: stringResource(id = R.string.RGBFieldInput_open_dialog),
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(id = R.dimen.fieldComponent_button_text_padding),
                    vertical = dimensionResource(id = R.dimen.fieldComponent_button_text_padding)
                )
        )
    }
}
