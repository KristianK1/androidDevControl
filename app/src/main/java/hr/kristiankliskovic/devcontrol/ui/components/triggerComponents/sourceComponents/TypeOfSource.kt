package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.ETriggerSourceType

@Composable
fun TypeOfSource(
    chooseType: (ETriggerSourceType) -> Unit,
    typeSelected: ETriggerSourceType,
) {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .border(
                width = 1.dp,
                shape = RectangleShape,
                color = Color.Gray,
            )
            .padding(dimensionResource(id = R.dimen.addTriggerBorderPadding))
    ) {
        Text(
            text = stringResource(id = R.string.triggerResponse_Type_title),
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 20.sp
        )
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
                text = stringResource(id = R.string.triggerSource_Type_fieldInGroup),
                modifier = Modifier
                    .weight(1f,true)
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
                text = stringResource(id = R.string.triggerSource_Type_fieldInComplexGroup),
                modifier = Modifier
                    .weight(1f,true)
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
                text = stringResource(id = R.string.triggerSource_Type_time),
                modifier = Modifier
                    .weight(1f,true)

            )
        }
    }
}

