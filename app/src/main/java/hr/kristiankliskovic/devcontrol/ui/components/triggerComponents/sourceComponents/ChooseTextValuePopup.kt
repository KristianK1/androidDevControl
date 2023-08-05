package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.OutlineTextWrapper
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.TextListOption
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes

@Composable
fun ChooseTextValuePopup(
    values: List<String>,
    chosen: (Int) -> Unit,
) {
    var chosenValue by remember { mutableStateOf<Int?>(null) }

    var dialogOpen by remember { mutableStateOf(false) }

    if (dialogOpen) {
        AlertDialog(
            onDismissRequest = {
                dialogOpen = false
            },
            buttons = {

                LazyColumn {
                    itemsIndexed(values) { index, value ->
                        TextListOption(
                            text = "$index$: $value",
                            onClick = {
                                dialogOpen = false
                                chosen(index)
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
            if (chosenValue != null)"$chosenValue"
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
