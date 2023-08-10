package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.OutlineTextWrapper
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents.*
import hr.kristiankliskovic.devcontrol.R

@Composable
fun AddTriggerRoute(
    viewModel: AddTriggerViewModel,
) {
    val addTriggerViewState by viewModel.viewState.collectAsState()
    val devices by viewModel.devices.collectAsState() //dont use this
    AddTriggerScreen(
        viewState = addTriggerViewState,
        changeName = {
            viewModel.changeTriggerName(it)
        },
        changeSourceType = {
            viewModel.changeSourceType(it)
        },
        selectDevice = {
            viewModel.selectSourceDevice(it)
        },
        selectGroup = {
            viewModel.selectSourceGroup(it)
        },
        selectState = {
            viewModel.selectSourceState(it)
        },
        selectField = {
            viewModel.selectSourceField(it)
        },
        setTimeTriggerType = {
            viewModel.setTimeTriggerType(it)
        },
        setTime = { hour, minute ->
            viewModel.setTimeTriggerTime(hour, minute)
        },
        setDate = { year, month, day ->
            viewModel.setDateTriggerDate(year, month, day)
        },
        setNumericTriggerType = {
            viewModel.setNumericSourceType(it)
        },
        setTextTriggerType = {
            viewModel.setTextSourceType(it)
        },
        setMCTriggerType = {
            viewModel.setMCSourceType(it)
        },
        setRGBTriggerType = {
            viewModel.setRGBSourceType(it)
        },
        setRGBTriggerContext = {
            viewModel.setRGBSourceContext(it)
        },
        setNumericFirstValue = {
            viewModel.setFirstNumericSourceValue(it)
        },
        setNumericSecondValue = {
            viewModel.setSecondNumericSourceValue(it)
        },
        setTextValue = {
            viewModel.setTextSourceValue(it)
        },
        setButtonValue = {
            viewModel.setBooleanSourceType(it)
        },
        setMCvalue = {
            viewModel.setMCTextSourceValue(it)
        },
        setRGBFirstValue = {
            viewModel.setFirstRGBSourceValue(it)
        },
        setRGBSecondValue = {
            viewModel.setSecondRGBSourceValue(it)
        },
    )
}

@Composable
fun AddTriggerScreen(
    viewState: AddTriggerViewState,
    changeName: (String) -> Unit,
    changeSourceType: (ETriggerSourceType) -> Unit,

    selectDevice: (Int) -> Unit,
    selectGroup: (Int) -> Unit,
    selectState: (Int) -> Unit,
    selectField: (Int) -> Unit,

    setTimeTriggerType: (ETriggerTimeType) -> Unit,
    setTime: (Int, Int) -> Unit,
    setDate: (Int, Int, Int) -> Unit,

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
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
    ) {
        OutlineTextWrapper(
            label = stringResource(id = R.string.addTrigger_triggerName_label),
            placeholder = stringResource(id = R.string.addTrigger_triggerName_placeholder),
            onChange = {
                changeName(it)
            },
            modifier = Modifier
                .padding(
                    horizontal = 0.dp,
                    vertical = dimensionResource(id = R.dimen.addTrigger_triggerNamePadding)
                )
        )

        TypeOfSource(
            typeSelected = viewState.sourceType.value,
            chooseType = {
                changeSourceType(it)
            }
        )
        if (viewState.sourceType.value == ETriggerSourceType.FieldInGroup || viewState.sourceType.value == ETriggerSourceType.FieldInComplexGroup) {
            TriggerSourceAddress(
                sourceType = viewState.sourceType.value,
                viewState = viewState.sourceAddress.value,
                selectDevice = selectDevice,
                selectGroup = selectGroup,
                selectState = selectState,
                selectField = selectField,
            )
        } else {
            TypeOfTimeSource(
                type = viewState.timeTriggerType.value,
                selectType = {
                    setTimeTriggerType(it)
                }
            )
            TimeSelection(
                time = viewState.timeSourceTime.value,
                saveTime = { hour, minute ->
                    setTime(hour, minute)
                }
            )
            DateSelection(
                date = viewState.timeSourceDate.value,
                saveDate = { year, month, day ->
                    setDate(year, month, day)
                }
            )
        }
        if (viewState.sourceSettings.value != null) {
            TriggerFieldDataSettings(
                viewState = viewState.sourceSettings.value,
                setNumericTriggerType = {
                    setNumericTriggerType(it)
                },
                setTextTriggerType = {
                    setTextTriggerType(it)
                },
                setMCTriggerType = {
                    setMCTriggerType(it)
                },
                setRGBTriggerType = {
                    setRGBTriggerType(it)
                },
                setRGBTriggerContext = {
                    setRGBTriggerContext(it)
                },
                setNumericFirstValue = {
                    setNumericFirstValue(it)
                },
                setNumericSecondValue = {
                    setNumericSecondValue(it)
                },
                setTextValue = {
                    setTextValue(it)
                },
                setButtonValue = {
                    setButtonValue(it)
                },
                setMCvalue = {
                    setMCvalue(it)
                },
                setRGBFirstValue = {
                    setRGBFirstValue(it)
                },
                setRGBSecondValue = {
                    setRGBSecondValue(it)
                },
            )
        }
    }
}
