package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
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
            .padding(dimensionResource(id = R.dimen.addTriggerBorderPadding)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (viewState) {
            is NumericTriggerSourceViewState -> {
                TypeOfNumericSource(
                    typeSelected = viewState.type.value,
                    chooseType = {
                        setNumericTriggerType(it)
                    }
                )
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
                    if (viewState.type.value == ENumericTriggerType.Between || viewState.type.value == ENumericTriggerType.NotBetween) {
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
                TypeOfButtonSource(
                    chooseType = {
                        setButtonValue(it)
                    },
                    typeSelected = viewState.value.value
                )
            }
            is MCTriggerSourceViewState -> {
                TypeOfMultipleChoiceSource(
                    chooseType = {
                        setMCTriggerType(it)
                    },
                    typeSelected = viewState.type.value)
                ChooseTextValuePopup(
                    values = viewState.values,
                    valueChosen = if (viewState.value.value != null) "${viewState.value.value}: ${viewState.values[viewState.value.value!!]}" else null,
                    chosen = {
                        setMCvalue(it)
                    }
                )
            }
            is RGBTriggerSourceViewState -> {
                TypeOfRGBSource(
                    chooseType = {
                        setRGBTriggerType(it)
                    },
                    typeSelected = viewState.type.value,
                    chooseContext = {
                        setRGBTriggerContext(it)
                    },
                    contextSelected = viewState.contextType.value,
                )

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
                TypeOfTextSource(
                    chooseType = {
                                 setTextTriggerType(it)
                    },
                    typeSelected = viewState.type.value
                )
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
