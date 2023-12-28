package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.EMCTriggerType
import hr.kristiankliskovic.devcontrol.model.ETextTriggerType


@Composable
fun TypeOfMultipleChoiceSource(
    chooseType: (EMCTriggerType) -> Unit,
    typeSelected: EMCTriggerType,
){
    Column {
        Text(
            text = stringResource(id = R.string.addTriggerScreen_valueType_title_multipleChoice),
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
                        chooseType(EMCTriggerType.IsEqualTo)
                    }
                    .background(
                        color = if (typeSelected == EMCTriggerType.IsEqualTo) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.6f
                        )
                    )
                    .padding(5.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.MCTriggerType_equal),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.background,
                )
            }
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxHeight()
            )
            Box(
                modifier = Modifier
                    .weight(1f, true)
                    .fillMaxHeight()
                    .clickable {
                        chooseType(EMCTriggerType.IsNotEqualTo)
                    }
                    .background(
                        color = if (typeSelected == EMCTriggerType.IsNotEqualTo) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.6f
                        )
                    )
                    .padding(5.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.MCTriggerType_notEqual),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.background,
                )
            }
        }
    }
}
