package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.TimePicker
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
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.ETriggerSourceType
import hr.kristiankliskovic.devcontrol.model.ITrigger
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents.TriggerSourceAddressFieldInComplexGroup
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents.TriggerSourceAddressFieldInGroup
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents.TypeOfSource
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes
import java.text.SimpleDateFormat
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
                    }
                )
            }
            ETriggerSourceType.FieldInComplexGroup -> {
                TriggerSourceAddressFieldInComplexGroup(
                    state = viewState,
                    selectFieldInComplexGroupData = {
                        newTriggerData.sourceData = it
                    }
                )
            }
            ETriggerSourceType.TimeTrigger -> {
                val time: Int?
                val date: Calendar?
                TimeSelection(
                    saveTime = {

                    }
                )
                DateSelection(
                    saveDate = {

                    }
                )
            }
        }
    }
}

@Composable
fun TimeSelection(
    saveTime: (Int) -> Unit,
) {
    val textStart = stringResource(id = R.string.addTriggerScreen_setTime)
    var text by remember { mutableStateOf(textStart) }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        val time = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            saveTime((hourOfDay * 60 + minute) / 5 * 5)
            text = String.format("%02d:%02d", hourOfDay, minute / 5 * 5)
            showDialog = false
        }
        TimePickerDialog(
            LocalContext.current,
            time,
            10,
            10,
            true
        ).show()
    }

    Text(
        text = text,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.addTriggerScreen_SelectedItem_text_margin))
            .clip(Shapes.small)
            .background(colorResource(id = R.color.addTrigger_setDateTime_background))
            .clickable {
                showDialog = true
            }
            .padding(dimensionResource(id = R.dimen.addTriggerScreen_SelectedItem_text_padding))
    )

}

@Composable
private fun DateSelection(
    saveDate: (Calendar) -> Unit,
) {
    val textStart = stringResource(id = R.string.addTriggerScreen_setDate)
    var text by remember { mutableStateOf(textStart) }

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        val calendar = Calendar.getInstance()
        val date = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            calendar.set(year, monthOfYear, dayOfMonth)
            text = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear, year)
            saveDate(calendar)
        }
        DatePickerDialog(
            LocalContext.current,
            date,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
        showDialog = false
    }

    Text(
        text = text,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.addTriggerScreen_SelectedItem_text_margin))
            .clip(Shapes.small)
            .background(colorResource(id = R.color.addTrigger_setDateTime_background))
            .clickable {
                showDialog = true
            }
            .padding(dimensionResource(id = R.dimen.addTriggerScreen_SelectedItem_text_padding))
    )
}
