package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.TextListOption
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes

@Composable
fun ChooseNumericValuePopup(
    minValue: Float,
    maxValue: Float,
    step: Float,
    prefix: String,
    sufix: String,
    chosen: (Float) -> Unit,
) {
    var chosenValue by remember { mutableStateOf<Float?>(null) }

    var dialogOpen by remember { mutableStateOf(false) }
    val values: ArrayList<Float> = arrayListOf()
    var i = minValue
    while (i <= maxValue) {
        values.add(i)
        i += step
    }
    if (dialogOpen) {
        AlertDialog(
            onDismissRequest = {
                dialogOpen = false
            },
            buttons = {

                LazyColumn {
                    items(values) { value ->
                        TextListOption(
                            text = "$prefix$value$sufix",
                            onClick = {
                                Log.i("numericFset", "x1")
                                dialogOpen = false
                                Log.i("numericFset", "x2_$value")
                                chosen(value)
                                Log.i("numericFset", "x3")
                            }
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.addTriggerScreen_dialog_padding)),
            shape = Shapes.small,
            backgroundColor = Color.White,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxHeight()
            .padding(dimensionResource(id = R.dimen.addPermissionScreen_user_button_margin))
            .clip(Shapes.small)
            .background(colorResource(id = R.color.addTrigger_chooseValueButton_background))
            .clickable { dialogOpen = true }
    ) {
        Text(
            text =
            if (chosenValue != null)"$prefix$chosenValue$sufix"
            else stringResource(id = R.string.triggerNumericSelectValue_startText),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(id = R.dimen.addPermissionScreen_user_button_padding_horizontal),
                    vertical = dimensionResource(id = R.dimen.addPermissionScreen_user_button_padding_vertical)
                )
        )
    }
}
