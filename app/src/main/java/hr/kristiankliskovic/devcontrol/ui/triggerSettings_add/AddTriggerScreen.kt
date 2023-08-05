package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.google.gson.Gson
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents.*
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes
import hr.kristiankliskovic.devcontrol.utils.*
import java.util.*

@Composable
fun AddTriggerRoute(
    viewModel: AddTriggerViewModel,
) {
    val addTriggerViewState by viewModel.devicesViewState.collectAsState()
    AddTriggerScreen(
        viewState = addTriggerViewState,
    )
}

@Composable
fun AddTriggerScreen(
    viewState: TriggerSourceDevicesViewState,
) {
    var newTriggerData = ITrigger.empty()

    var typeSeleted by remember { mutableStateOf(ETriggerSourceType.FieldInGroup) }
    val scrollState = rememberScrollState()

    var fieldData by remember {
        mutableStateOf<BasicDeviceField?>(null)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
    ) {
        TypeOfSource(
            chooseType = {
                typeSeleted = it
                newTriggerData.sourceType = it
            }
        )
        when (typeSeleted) {
            ETriggerSourceType.FieldInGroup -> {
                TriggerSourceAddressFieldInGroup(
                    state = viewState,
                    selectFieldInGroupData = {
                        newTriggerData.sourceData = it
                        fieldData = findFieldData(
                            sourceData = newTriggerData.sourceData,
                            viewState = viewState,
                        )
                    }

                )
            }
            ETriggerSourceType.FieldInComplexGroup -> {
                TriggerSourceAddressFieldInComplexGroup(
                    state = viewState,
                    selectFieldInComplexGroupData = {
                        newTriggerData.sourceData = it
                        fieldData = findFieldData(
                            sourceData = newTriggerData.sourceData,
                            viewState = viewState,
                        )
                    }
                )
            }
            ETriggerSourceType.TimeTrigger -> {
                var time: Int? = null
                var date: Calendar? = null

                TimeSelection(
                    saveTime = {
                        time = it
                        if (date != null) {
                            val timeStamp = valuesToCalendar(
                                year = date!!.get(Calendar.YEAR),
                                month = date!!.get(Calendar.MONTH),
                                day = date!!.get(Calendar.DAY_OF_MONTH),
                                hour = time!! / 60,
                                minute = time!! % 60
                            )
                            val rez = localCalendarToGMTISO(timeStamp)
                        }
                    }
                )
                DateSelection(
                    saveDate = {
                        date = it
                        if (time != null) {
                            val timeStamp = valuesToCalendar(
                                year = date!!.get(Calendar.YEAR),
                                month = date!!.get(Calendar.MONTH),
                                day = date!!.get(Calendar.DAY_OF_MONTH),
                                hour = time!! / 60,
                                minute = time!! % 60
                            )
                            val rez = localCalendarToGMTISO(timeStamp)
                        }
                    }
                )
            }
        }
        if (fieldData != null) {
            TriggerFieldDataSettings(
                field = fieldData!!,
                setTriggerSettings = {

                }
            )
        }
    }
}


private fun findFieldData(
    sourceData: TriggerSourceData,
    viewState: TriggerSourceDevicesViewState,
): BasicDeviceField? {
    when (sourceData) {
        is ITriggerSourceAdress_fieldInGroup -> {
            return try {
                viewState.devices.find {
                    it.id == sourceData.deviceId
                }!!.groups.find {
                    it.groupId == sourceData.groupId
                }!!.fields.find {
                    it.fieldId == sourceData.fieldId
                }!!.fieldData
            } catch (e: Throwable) {
                null
            }
        }
        is ITriggerSourceAdress_fieldInComplexGroup -> {
            return try {
                viewState.devices.find {
                    it.id == sourceData.deviceId
                }!!.complexGroups.find {
                    it.complexGroupId == sourceData.complexGroupId
                }!!.states.find {
                    it.stateId == sourceData.stateId
                }!!.fields.find {
                    it.fieldId == sourceData.fieldId
                }!!.fieldData
            } catch (e: Throwable) {
                null
            }
        }
        is ITriggerTimeSourceData -> {
            return null
        }
    }
}
