package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.RadioButtonRow
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.OutlineTextWrapper
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.*

@Composable
fun TriggerFieldSourceDataSettings(
    viewState: TriggerSourceSettingsViewState?,

    setNumericTriggerType: (ENumericTriggerType) -> Unit,
    setTextTriggerType: (ETextTriggerType) -> Unit,
    setMCTriggerType: (EMCTriggerType) -> Unit,
    setRGBTriggerType: (ERGBTriggerType_numeric) -> Unit,
    setRGBTriggerContext: (ERGBTriggerType_context) -> Unit,

    setNumericFirstValue: (Float) -> Unit,
    setNumericSecondValue: (Float) -> Unit,
    setTextValue: (String) -> Unit,
    setButtonValue: (Boolean) -> Unit,
    setMCvalue: (Int) -> Unit,
    setRGBFirstValue: (Int) -> Unit,
    setRGBSecondValue: (Int) -> Unit,
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
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (viewState) {
            is NumericTriggerSourceViewState -> {
                Column {
                    Text(
                        text = stringResource(id = R.string.addTriggerScreen_valueType_title_numeric),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        fontSize = 16.sp
                    )
                    RadioButtonRow(
                        selected = viewState.type.value == ENumericTriggerType.Bigger,
                        text = stringResource(id = R.string.numericTriggerType_bigger),
                        onClick = {
                            setNumericTriggerType(ENumericTriggerType.Bigger)
                        }
                    )
                    RadioButtonRow(
                        selected = viewState.type.value == ENumericTriggerType.Smaller,
                        text = stringResource(id = R.string.numericTriggerType_smaller),
                        onClick = {
                            setNumericTriggerType(ENumericTriggerType.Smaller)
                        }
                    )
                    RadioButtonRow(
                        selected = viewState.type.value == ENumericTriggerType.Equal,
                        text = stringResource(id = R.string.numericTriggerType_equal),
                        onClick = {
                            setNumericTriggerType(ENumericTriggerType.Equal)
                        }
                    )
                    RadioButtonRow(
                        selected = viewState.type.value == ENumericTriggerType.Inbetween,
                        text = stringResource(id = R.string.numericTriggerType_between),
                        onClick = {
                            setNumericTriggerType(ENumericTriggerType.Inbetween)
                        }
                    )
                    RadioButtonRow(
                        selected = viewState.type.value == ENumericTriggerType.NotInBetween,
                        text = stringResource(id = R.string.numericTriggerType_NotBetween),
                        onClick = {
                            setNumericTriggerType(ENumericTriggerType.NotInBetween)
                        }
                    )
                }
                Row {
                    ChooseNumericValuePopup(
                        minValue = viewState.minimum,
                        maxValue = viewState.maximum,
                        step = viewState.step,
                        prefix = viewState.prefix,
                        sufix = viewState.sufix,
                        chosenValue = viewState.value.value,
                        chosen = {
                            setNumericFirstValue(it)
                        }
                    )
                    if (viewState.type.value == ENumericTriggerType.Inbetween || viewState.type.value == ENumericTriggerType.NotInBetween) {
                        ChooseNumericValuePopup(
                            minValue = viewState.minimum,
                            maxValue = viewState.maximum,
                            step = viewState.step,
                            prefix = viewState.prefix,
                            sufix = viewState.sufix,
                            chosenValue = viewState.second_value.value,
                            chosen = {
                                setNumericSecondValue(it)
                            }
                        )
                    }
                }
            }
            is BooleanTriggerSourceViewState -> {
                Column {
                    Text(
                        text = stringResource(id = R.string.addTriggerScreen_valueType_title_button),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        fontSize = 16.sp
                    )
                    RadioButtonRow(
                        selected = viewState.value.value,
                        text = stringResource(id = R.string.buttonTriggerType_true),
                        onClick = {
                            setButtonValue(true)
                        }
                    )
                    RadioButtonRow(
                        selected = !viewState.value.value,
                        text = stringResource(id = R.string.buttonTriggerType_false),
                        onClick = {
                            setButtonValue(false)
                        }
                    )
                }
            }
            is MCTriggerSourceViewState -> {
                Column {
                    Text(
                        text = stringResource(id = R.string.addTriggerScreen_valueType_title_multipleC),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        fontSize = 16.sp
                    )
                    RadioButtonRow(
                        selected = viewState.type.value == EMCTriggerType.IsEqualTo,
                        text = stringResource(id = R.string.MCTriggerType_equal),
                        onClick = {
                            setMCTriggerType(EMCTriggerType.IsEqualTo)
                        }
                    )
                    RadioButtonRow(
                        selected = viewState.type.value == EMCTriggerType.IsNotEqualTo,
                        text = stringResource(id = R.string.MCTriggerType_notEqual),
                        onClick = {
                            setMCTriggerType(EMCTriggerType.IsNotEqualTo)
                        }
                    )
                }
                ChooseTextValuePopup(
                    values = viewState.values,
                    valueChosen = if (viewState.value.value != null) "${viewState.value.value}: ${viewState.values[viewState.value.value!!]}" else null,
                    chosen = {
                        setMCvalue(it)
                    }
                )
            }
            is RGBTriggerSourceViewState -> {
                Column {
                    Text(
                        text = stringResource(id = R.string.addTriggerScreen_valueType_title_RGB),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        fontSize = 16.sp
                    )
                    RadioButtonRow(
                        selected = viewState.type.value == ERGBTriggerType_numeric.Bigger,
                        text = stringResource(id = R.string.numericTriggerType_bigger),
                        onClick = {
                            setRGBTriggerType(ERGBTriggerType_numeric.Bigger)
                        }
                    )
                    RadioButtonRow(
                        selected = viewState.type.value == ERGBTriggerType_numeric.Smaller,
                        text = stringResource(id = R.string.numericTriggerType_smaller),
                        onClick = {
                            setRGBTriggerType(ERGBTriggerType_numeric.Smaller)
                        }
                    )
                    RadioButtonRow(
                        selected = viewState.type.value == ERGBTriggerType_numeric.Equal,
                        text = stringResource(id = R.string.numericTriggerType_equal),
                        onClick = {
                            setRGBTriggerType(ERGBTriggerType_numeric.Equal)
                        }
                    )
                    RadioButtonRow(
                        selected = viewState.type.value == ERGBTriggerType_numeric.Inbetween,
                        text = stringResource(id = R.string.numericTriggerType_between),
                        onClick = {
                            setRGBTriggerType(ERGBTriggerType_numeric.Inbetween)
                        }
                    )
                    RadioButtonRow(
                        selected = viewState.type.value == ERGBTriggerType_numeric.NotInBetween,
                        text = stringResource(id = R.string.numericTriggerType_NotBetween),
                        onClick = {
                            setRGBTriggerType(ERGBTriggerType_numeric.NotInBetween)
                        }
                    )

                    Text(
                        text = stringResource(id = R.string.addTriggerScreen_valueType_title_RGB_context),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
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
                }

                Row {
                    ChooseNumericValuePopup(
                        minValue = 0f,
                        maxValue = 255f,
                        step = 1f,
                        prefix = "",
                        sufix = "",
                        chosenValue = if (viewState.value.value != null) viewState.value.value!!.toFloat() else null,
                        chosen = {
                            setRGBFirstValue(it.toInt())
                        }
                    )
                    if (viewState.type.value == ERGBTriggerType_numeric.Inbetween || viewState.type.value == ERGBTriggerType_numeric.NotInBetween) {
                        ChooseNumericValuePopup(
                            minValue = 0f,
                            maxValue = 255f,
                            step = 1f,
                            prefix = "",
                            sufix = "",
                            chosenValue = if (viewState.second_value.value != null) viewState.second_value.value!!.toFloat() else null,
                            chosen = {
                                setRGBSecondValue(it.toInt())
                            }
                        )
                    }
                }
            }
            is TextTriggerSourceViewState -> {
                Column {
                    Text(
                        text = stringResource(id = R.string.addTriggerScreen_valueType_title_text),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        fontSize = 16.sp
                    )

                    RadioButtonRow(
                        selected = viewState.type.value == ETextTriggerType.StartsWith,
                        text = stringResource(id = R.string.textTriggerType_startsWith),
                        onClick = {
                            setTextTriggerType(ETextTriggerType.StartsWith)
                        }
                    )
                    RadioButtonRow(
                        selected = viewState.type.value == ETextTriggerType.EndsWith,
                        text = stringResource(id = R.string.textTriggerType_endsWith),
                        onClick = {
                            setTextTriggerType(ETextTriggerType.EndsWith)
                        }
                    )
                    RadioButtonRow(
                        selected = viewState.type.value == ETextTriggerType.Contains,
                        text = stringResource(id = R.string.textTriggerType_contains),
                        onClick = {
                            setTextTriggerType(ETextTriggerType.Contains)
                        }
                    )
                    RadioButtonRow(
                        selected = viewState.type.value == ETextTriggerType.IsEqualTo,
                        text = stringResource(id = R.string.textTriggerType_isEqual),
                        onClick = {
                            setTextTriggerType(ETextTriggerType.IsEqualTo)
                        }
                    )
                    RadioButtonRow(
                        selected = viewState.type.value == ETextTriggerType.IsNotEqualTo,
                        text = stringResource(id = R.string.textTriggerType_isEqualTo),
                        onClick = {
                            setTextTriggerType(ETextTriggerType.IsNotEqualTo)
                        }
                    )
                }
                OutlineTextWrapper(
                    label = stringResource(id = R.string.textTriggerValue_label),
                    placeholder = stringResource(id = R.string.textTriggerValue_placeholder),
                    onChange = {
                        setTextValue(it)
                    }
                )
            }
        }
    }
}
