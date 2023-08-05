package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes
import java.util.*

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
fun DateSelection(
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
            Log.i("devCalendar", "zzz")
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
