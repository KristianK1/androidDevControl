package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes
import java.util.*

@Composable
fun TimeSelection(
    saveTime: (Int, Int) -> Unit,
    time: Int?,
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        val time = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            saveTime(hourOfDay, minute)
            showDialog = false
        }
        TimePickerDialog(
            LocalContext.current,
            time,
            0,
            0,
            true
        ).show()
    }

    Text(
        text = if (time != null) String.format("%02d:%02d",
            time / 60,
            (time % 60) / 5 * 5) else stringResource(id = R.string.addTriggerScreen_setTime),
        color = MaterialTheme.colorScheme.background,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.addTriggerScreen_SelectedItem_text_margin))
            .fillMaxWidth()
            .clip(Shapes.small)
            .background(
                color = MaterialTheme.colorScheme.primary,
            )
            .clickable {
                if(showDialog){ //legit...
                    showDialog = false
                }
                showDialog = true
            }
            .padding(dimensionResource(id = R.dimen.addTriggerScreen_SelectedItem_text_padding)),
        textAlign = TextAlign.Center,
    )

}

@Composable
fun DateSelection(
    saveDate: (Int, Int, Int) -> Unit,
    date: Calendar?,
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        val calendar = Calendar.getInstance()
        val date = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            saveDate(year, monthOfYear, dayOfMonth)
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
        text = if (date != null) String.format("%02d/%02d/%04d",
            date.get(Calendar.DAY_OF_MONTH),
            date.get(Calendar.MONTH) + 1,
            date.get(Calendar.YEAR)) else stringResource(id = R.string.addTriggerScreen_setDate),
        color = MaterialTheme.colorScheme.background,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.addTriggerScreen_SelectedItem_text_margin))
            .fillMaxWidth()
            .clip(Shapes.small)
            .background(
                color = MaterialTheme.colorScheme.primary,
            ).clickable {
                showDialog = true
            }
            .padding(dimensionResource(id = R.dimen.addTriggerScreen_SelectedItem_text_padding)),
        textAlign = TextAlign.Center,
    )
}
