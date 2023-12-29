package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.OutlineTextWrapper
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents.*
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes
import java.time.DayOfWeek

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
        selectSourceDevice = {
            viewModel.selectSourceDevice(it)
        },
        selectSourceGroup = {
            viewModel.selectSourceGroup(it)
        },
        selectSourceState = {
            viewModel.selectSourceState(it)
        },
        selectSourceField = {
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
        selectDayOfWeek = { day, state ->
            viewModel.selectDayOfTheWeek(day, state)
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
        setNumericSourceFirstValue = {
            viewModel.setFirstNumericSourceValue(it)
        },
        setNumericSourceSecondValue = {
            viewModel.setSecondNumericSourceValue(it)
        },
        setTextSourceValue = {
            viewModel.setTextSourceValue(it)
        },
        setButtonSourceValue = {
            viewModel.setBooleanSourceType(it)
        },
        setMCSourcevalue = {
            viewModel.setMCTextSourceValue(it)
        },
        setRGBSourceFirstValue = {
            viewModel.setFirstRGBSourceValue(it)
        },
        setRGBSourceSecondValue = {
            viewModel.setSecondRGBSourceValue(it)
        },
        setNumericResponseValue = {
            viewModel.setNumericResponseValue(it)
        },
        setTextResponseValue = {
            viewModel.setTextResponseValue(it)
        },
        setButtonResponseValue = {
            viewModel.setBooleanResponseType(it)
        },
        setMCResponseValue = {
            viewModel.setMCResponseValue(it)
        },
        setRGBResponseValue = {
            viewModel.setRGBResponseValue(it)
        },
        selectResponseDevice = {
            viewModel.selectResponseDevice(it)
        },
        selectResponseGroup = {
            viewModel.selectResponseGroup(it)
        },
        selectResponseState = {
            viewModel.selectResponseState(it)
        },
        selectResponseField = {
            viewModel.selectResponseField(it)
        },
        changeResponseType = {
            viewModel.changeResponseType(it)
        },
        changeResponseText = {
            viewModel.changeResponseText(it)
        },
        changeResponseTitle = {
            viewModel.changeResponseTitle(it)
        },
        saveTrigger = {
            viewModel.saveTrigger()
        }
    )
}

@Composable
fun AddTriggerScreen(
    viewState: AddTriggerViewState,
    changeName: (String) -> Unit,
    changeSourceType: (ETriggerSourceType) -> Unit,

    selectSourceDevice: (Int) -> Unit,
    selectSourceGroup: (Int) -> Unit,
    selectSourceState: (Int) -> Unit,
    selectSourceField: (Int) -> Unit,

    setTimeTriggerType: (ETriggerTimeType) -> Unit,
    setTime: (Int, Int) -> Unit,
    setDate: (Int, Int, Int) -> Unit,
    selectDayOfWeek: (Int, Boolean) -> Unit,

    setNumericTriggerType: (ENumericTriggerType) -> Unit,
    setTextTriggerType: (ETextTriggerType) -> Unit,
    setMCTriggerType: (EMCTriggerType) -> Unit,
    setRGBTriggerType: (ERGBTriggerType_numeric) -> Unit,
    setRGBTriggerContext: (ERGBTriggerType_context) -> Unit,

    setNumericSourceFirstValue: (Float) -> Unit,
    setNumericSourceSecondValue: (Float) -> Unit,
    setTextSourceValue: (String) -> Unit,
    setButtonSourceValue: (Boolean) -> Unit,
    setMCSourcevalue: (Int) -> Unit,
    setRGBSourceFirstValue: (Int) -> Unit,
    setRGBSourceSecondValue: (Int) -> Unit,

    changeResponseType: (ETriggerResponseType) -> Unit,

    selectResponseDevice: (Int) -> Unit,
    selectResponseGroup: (Int) -> Unit,
    selectResponseState: (Int) -> Unit,
    selectResponseField: (Int) -> Unit,

    setNumericResponseValue: (Float) -> Unit,
    setTextResponseValue: (String) -> Unit,
    setButtonResponseValue: (Boolean) -> Unit,
    setMCResponseValue: (Int) -> Unit,
    setRGBResponseValue: (RGBValue) -> Unit,

    changeResponseTitle: (String) -> Unit,
    changeResponseText: (String) -> Unit,

    saveTrigger: () -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.addTrigger_screen_padding))
            .verticalScroll(state = scrollState)
    ) {
        OutlineTextWrapper(
            initValue = viewState.triggerName,
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
                title = stringResource(id = R.string.addTriggerScreen_sourceAddress_selectTitle),
                includeComplexGroups = viewState.sourceType.value == ETriggerSourceType.FieldInComplexGroup,
                viewState = viewState.sourceAddress.value,
                selectDevice = selectSourceDevice,
                selectGroup = selectSourceGroup,
                selectState = selectSourceState,
                selectField = selectSourceField,
            )
        } else {
            TypeOfTimeSource(
                typeSelected = viewState.timeTriggerType.value,
                chooseType = {
                    setTimeTriggerType(it)
                },
                timeSourceTime = viewState.timeSourceTime.value,
                timeSourceDate = viewState.timeSourceDate.value,
                setDate = { year, month, day ->
                    setDate(year, month, day)
                },
                setTime = { hour, minute ->
                    setTime(hour, minute)
                },
                daysOfTheWeek = viewState.daysOfTheWeek.map{ it.value },
                selectDayOfWeek = { day, state ->
                    selectDayOfWeek(day, state)
                }
            )
        }
        if (viewState.sourceSettings.value != null) {
            TriggerFieldSourceDataSettings(
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
                    setNumericSourceFirstValue(it)
                },
                setNumericSecondValue = {
                    setNumericSourceSecondValue(it)
                },
                setTextValue = {
                    setTextSourceValue(it)
                },
                setButtonValue = {
                    setButtonSourceValue(it)
                },
                setMCvalue = {
                    setMCSourcevalue(it)
                },
                setRGBFirstValue = {
                    setRGBSourceFirstValue(it)
                },
                setRGBSecondValue = {
                    setRGBSourceSecondValue(it)
                },
            )
        }
        TypeOfResponse(
            chooseType = {
                changeResponseType(it)
            },
            typeSelected = viewState.responseType.value
        )

        when (viewState.responseType.value) {
            ETriggerResponseType.SettingValue_fieldInGroup, ETriggerResponseType.SettingValue_fieldInComplexGroup -> {
                TriggerSourceAddress(
                    title = stringResource(id = R.string.addTriggerScreen_responseAddress_selectTitle),
                    includeComplexGroups = viewState.responseType.value == ETriggerResponseType.SettingValue_fieldInComplexGroup,
                    viewState = viewState.responseAddress.value,
                    selectDevice = selectResponseDevice,
                    selectGroup = selectResponseGroup,
                    selectState = selectResponseState,
                    selectField = selectResponseField,
                )
                TriggerFieldSourceDataSettings(
                    viewState = viewState.responseSettings.value,
                    setNumericValue = {
                        setNumericResponseValue(it)
                    },
                    setTextValue = {
                        setTextResponseValue(it)
                    },
                    setButtonValue = {
                        setButtonResponseValue(it)
                    },
                    setMCvalue = {
                        setMCResponseValue(it)
                    },
                    setRGBValue = {
                        setRGBResponseValue(it)
                    },
                )

            }
            ETriggerResponseType.Email -> {
                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .padding(dimensionResource(id = R.dimen.addTriggerBorderPadding))
                        .align(Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.addTrigger_emailSetupTitle),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 20.sp
                    )
                    OutlineTextWrapper(
                        label = stringResource(id = R.string.addTrigger_emailTitle_label),
                        placeholder = stringResource(id = R.string.addTrigger_emailTitle_placeholder),
                        onChange = {
                            changeResponseTitle(it)
                        }
                    )
                    OutlineTextWrapper(
                        label = stringResource(id = R.string.addTrigger_emailText_label),
                        placeholder = stringResource(id = R.string.addTrigger_emailText_placeholder),
                        onChange = {
                            changeResponseText(it)

                        }
                    )
                }
            }
            ETriggerResponseType.MobileNotification -> { //mobile
                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .padding(dimensionResource(id = R.dimen.addTriggerBorderPadding))
                        .align(Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.addTrigger_notificationSetupTitle),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 20.sp
                    )
                    OutlineTextWrapper(
                        label = stringResource(id = R.string.addTrigger_notificationTitle_label),
                        placeholder = stringResource(id = R.string.addTrigger_notificationTitle_placeholder),
                        onChange = {
                            changeResponseTitle(it)
                        }
                    )
                    OutlineTextWrapper(
                        label = stringResource(id = R.string.addTrigger_notificationText_label),
                        placeholder = stringResource(id = R.string.addTrigger_notificationText_placeholder),
                        onChange = {
                            changeResponseText(it)
                        }
                    )
                }
            }
        }

        Text(
            text = stringResource(id = R.string.addTrigger_saveTrigger),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.addTrigger_saveButton_margin))
                .clip(Shapes.medium)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                )
                .clickable {
                    saveTrigger()
                }
                .padding(dimensionResource(id = R.dimen.addTrigger_saveButton_padding))
        )
    }
}
