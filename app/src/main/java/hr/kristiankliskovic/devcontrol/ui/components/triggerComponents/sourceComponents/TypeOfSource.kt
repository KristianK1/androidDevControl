package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.ETriggerSourceType

@Composable
fun TypeOfSource(
    chooseType: (ETriggerSourceType) -> Unit,
) {
    Column() {

        var typeSelected by remember { mutableStateOf(ETriggerSourceType.FieldInGroup) }

        Row(
            modifier = Modifier
                .clickable {
                    chooseType(ETriggerSourceType.FieldInGroup)
                },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RadioButton(
                selected = typeSelected == ETriggerSourceType.FieldInGroup,
                onClick = null
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.addTriggerScreen_addTrigger_radiobutton_spacer_width)))
            Text(
                text = stringResource(id = R.string.triggerSource_Type_fieldInGroup)
            )
        }
        Row(
            modifier = Modifier
                .clickable {
                    chooseType(ETriggerSourceType.FieldInComplexGroup)

                },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RadioButton(
                selected = typeSelected == ETriggerSourceType.FieldInComplexGroup,
                onClick = null
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.addTriggerScreen_addTrigger_radiobutton_spacer_width)))
            Text(
                text = stringResource(id = R.string.triggerSource_Type_fieldInComplexGroup)
            )
        }
        Row(
            modifier = Modifier
                .clickable {
                    chooseType(ETriggerSourceType.TimeTrigger)

                },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RadioButton(
                selected = typeSelected == ETriggerSourceType.TimeTrigger,
                onClick = null
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.addTriggerScreen_addTrigger_radiobutton_spacer_width)))
            Text(
                text = stringResource(id = R.string.triggerSource_Type_time)

            )
        }
    }
}

