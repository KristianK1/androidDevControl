package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.ETriggerSourceType

@Composable
fun TypeOfSource(
    chooseType: (ETriggerSourceType) -> Unit,
    typeSelected: ETriggerSourceType,
) {
    Column {
        Text(
            text = stringResource(id = R.string.triggerSource_Type_title),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .height(IntrinsicSize.Max)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f, true)
                    .fillMaxHeight()
                    .clickable {
                        chooseType(ETriggerSourceType.FieldInGroup)
                    }
                    .background(
                        color = if (typeSelected == ETriggerSourceType.FieldInGroup) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.6f
                        )
                    )
                    .padding(10.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.triggerSource_Type_fieldInGroup),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.background,
                )
            }
            Box(
                modifier = Modifier
                    .width(5.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxHeight()
            )
            Box(
                modifier = Modifier
                    .weight(1f, true)
                    .fillMaxHeight()
                    .clickable {
                        chooseType(ETriggerSourceType.FieldInComplexGroup)
                    }
                    .background(
                        color = if (typeSelected == ETriggerSourceType.FieldInComplexGroup) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.6f
                        )
                    )
                    .padding(10.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.triggerSource_Type_fieldInComplexGroup),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.background,
                )
            }
            Box(
                modifier = Modifier
                    .width(5.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxHeight()
            )
            Box(
                modifier = Modifier
                    .weight(1f, true)
                    .fillMaxHeight()
                    .clickable {
                        chooseType(ETriggerSourceType.TimeTrigger)
                    }
                    .background(
                        color = if (typeSelected == ETriggerSourceType.TimeTrigger) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.6f
                        )
                    )
                    .padding(10.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.triggerSource_Type_time),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.background,
                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewTypeOfTriggerSource(){
    var type: ETriggerSourceType by remember {
        mutableStateOf(ETriggerSourceType.FieldInGroup)
    }

        TypeOfSource(
        typeSelected =  type,
        chooseType = {
            type = it
        }
    )
}
