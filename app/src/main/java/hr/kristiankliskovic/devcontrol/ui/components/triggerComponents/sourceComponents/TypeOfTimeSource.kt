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
import hr.kristiankliskovic.devcontrol.model.ETriggerSourceType
import hr.kristiankliskovic.devcontrol.model.ETriggerTimeType

@Composable
fun TypeOfTimeSource(
    type: ETriggerTimeType,
    selectType: (ETriggerTimeType) ->Unit
){
    Column {
        Row(
            modifier = Modifier
                .clickable {
                    selectType(ETriggerTimeType.Once)
                },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RadioButton(
                selected = type == ETriggerTimeType.Once,
                onClick = null
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.addTriggerScreen_addTrigger_radiobutton_spacer_width)))
            Text(
                text = stringResource(id = R.string.triggerSource_TimeType_once)
            )
        }
        Row(
            modifier = Modifier
                .clickable {
                    selectType(ETriggerTimeType.Daily)
                },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RadioButton(
                selected = type == ETriggerTimeType.Daily,
                onClick = null
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.addTriggerScreen_addTrigger_radiobutton_spacer_width)))
            Text(
                text = stringResource(id = R.string.triggerSource_TimeType_daily)
            )
        }
        Row(
            modifier = Modifier
                .clickable {
                    selectType(ETriggerTimeType.Weekly)
                },
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            RadioButton(
                selected = type == ETriggerTimeType.Weekly,
                onClick = null
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.addTriggerScreen_addTrigger_radiobutton_spacer_width)))
            Text(
                text = stringResource(id = R.string.triggerSource_TimeType_weekly)
            )
        }
    }
}
