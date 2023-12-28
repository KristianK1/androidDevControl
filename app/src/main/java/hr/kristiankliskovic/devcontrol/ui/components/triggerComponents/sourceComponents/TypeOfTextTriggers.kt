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
import hr.kristiankliskovic.devcontrol.model.ENumericTriggerType
import hr.kristiankliskovic.devcontrol.model.ETextTriggerType

@Composable
fun TypeOfTextSource(
    chooseType: (ETextTriggerType) -> Unit,
    typeSelected: ETextTriggerType,
){
    Column {
        Text(
            text = stringResource(id = R.string.addTriggerScreen_valueType_title_text),
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
                        chooseType(ETextTriggerType.StartsWith)
                    }
                    .background(
                        color = if (typeSelected == ETextTriggerType.StartsWith) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.6f
                        )
                    )
                    .padding(5.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.textTriggerType_startsWith),
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
                        chooseType(ETextTriggerType.EndsWith)
                    }
                    .background(
                        color = if (typeSelected == ETextTriggerType.EndsWith) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.6f
                        )
                    )
                    .padding(5.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.textTriggerType_endsWith),
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
                        chooseType(ETextTriggerType.Contains)
                    }
                    .background(
                        color = if (typeSelected == ETextTriggerType.Contains) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.6f
                        )
                    )
                    .padding(5.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.textTriggerType_contains),
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
                        chooseType(ETextTriggerType.IsEqualTo)
                    }
                    .background(
                        color = if (typeSelected == ETextTriggerType.IsEqualTo) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.6f
                        )
                    )
                    .padding(5.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.textTriggerType_isEqual),
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
                        chooseType(ETextTriggerType.IsNotEqualTo)
                    }
                    .background(
                        color = if (typeSelected == ETextTriggerType.IsNotEqualTo) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.6f
                        )
                    )
                    .padding(5.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.textTriggerType_isNotEqualTo),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.background,
                )
            }
        }
    }
}
