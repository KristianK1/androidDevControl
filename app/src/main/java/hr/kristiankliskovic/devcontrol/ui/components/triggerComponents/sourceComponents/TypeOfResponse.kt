package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.ETriggerResponseType

@Composable
fun TypeOfResponse(
    chooseType: (ETriggerResponseType) -> Unit,
    typeSelected: ETriggerResponseType,
) {
    Column {
        Row(
            modifier = Modifier
                .clickable {
                    chooseType(ETriggerResponseType.SettingValue_fieldInGroup)
                },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RadioButton(
                selected = typeSelected == ETriggerResponseType.SettingValue_fieldInGroup,
                onClick = null
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.addTriggerScreen_addTrigger_radiobutton_spacer_width)))
            Text(
                text = stringResource(id = R.string.triggerResponse_Type_fieldInGroup)
            )
        }
        Row(
            modifier = Modifier
                .clickable {
                    chooseType(ETriggerResponseType.SettingValue_fieldInComplexGroup)
                },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RadioButton(
                selected = typeSelected == ETriggerResponseType.SettingValue_fieldInComplexGroup,
                onClick = null
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.addTriggerScreen_addTrigger_radiobutton_spacer_width)))
            Text(
                text = stringResource(id = R.string.triggerResponse_Type_fieldInComplexGroup)
            )
        }
        Row(
            modifier = Modifier
                .clickable {
                    chooseType(ETriggerResponseType.Email)
                },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RadioButton(
                selected = typeSelected == ETriggerResponseType.Email,
                onClick = null
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.addTriggerScreen_addTrigger_radiobutton_spacer_width)))
            Text(
                text = stringResource(id = R.string.triggerResponse_Type_email)
            )
        }
        Row(
            modifier = Modifier
                .clickable {
                    chooseType(ETriggerResponseType.MobileNotification)
                },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RadioButton(
                selected = typeSelected == ETriggerResponseType.MobileNotification,
                onClick = null
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.addTriggerScreen_addTrigger_radiobutton_spacer_width)))
            Text(
                text = stringResource(id = R.string.triggerResponse_Type_mobileNotification)
            )
        }
    }
}