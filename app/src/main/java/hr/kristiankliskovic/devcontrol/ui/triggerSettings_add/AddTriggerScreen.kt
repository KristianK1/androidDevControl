package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents.*
import hr.kristiankliskovic.devcontrol.utils.*
import java.util.*
import kotlin.math.min

@Composable
fun AddTriggerRoute(
    viewModel: AddTriggerViewModel,
) {
    val addTriggerViewState by viewModel.viewState.collectAsState()
    AddTriggerScreen(
        viewState = addTriggerViewState,
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
        setTime = { hour, minute ->
            viewModel.setTimeTriggerTime(hour, minute)
        },
        setDate = { year, month, day ->
            viewModel.setDateTriggerDate(year, month, day)
        }
    )
}

@Composable
fun AddTriggerScreen(
    viewState: AddTriggerViewState,
    changeSourceType: (ETriggerSourceType) -> Unit,
    selectDevice: (Int) -> Unit,
    selectGroup: (Int) -> Unit,
    selectState: (Int) -> Unit,
    selectField: (Int) -> Unit,
    setTime: (Int, Int) -> Unit,
    setDate: (Int, Int, Int) -> Unit,
) {

    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
    ) {
        TypeOfSource(
            typeSelected = viewState.sourceType.value,
            chooseType = {
                Log.i("devCAL", "changeState - $it")
                changeSourceType(it)
            }
        )
        if (viewState.sourceType.value == ETriggerSourceType.FieldInGroup || viewState.sourceType.value == ETriggerSourceType.FieldInComplexGroup) {
//            TriggerSourceAddress(
//                sourceType = viewState.sourceType,
//                viewState = viewState.sourceAddress,
//                selectDevice = selectDevice,
//                selectGroup = selectGroup,
//                selectState = selectState,
//                selectField = selectField,
//            )
        } else {
            Log.i("timeCAL", "calendar opened")
            TimeSelection(
                time = viewState.timeSourceTime,
                saveTime = { hour, minute ->
                    setTime(hour, minute)
                }
            )
            DateSelection(
                date = viewState.timeSourceDate,
                saveDate = { year, month, day ->
                    setDate(year, month, day)
                }
            )
        }
    }
//        if (fieldData != null) {
//            TriggerFieldDataSettings(
//                field = fieldData!!,
//                setTriggerSettings = {
//
//                }
//            )
//        }
//}
}
