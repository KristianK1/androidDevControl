package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import hr.kristiankliskovic.devcontrol.model.ERGBTriggerType_context
import hr.kristiankliskovic.devcontrol.model.ERGBTriggerType_numeric

@Composable
fun TypeOfRGBSource(
    chooseType: (ERGBTriggerType_numeric) -> Unit,
    typeSelected: ERGBTriggerType_numeric,
    chooseContext: (ERGBTriggerType_context) -> Unit,
    contextSelected: ERGBTriggerType_context
){
    Text(
        text = stringResource(id = R.string.addTriggerScreen_valueType_title_RGB),
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
                    chooseType(ERGBTriggerType_numeric.Bigger)
                }
                .background(
                    color = if (typeSelected == ERGBTriggerType_numeric.Bigger) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.6f
                    )
                )
                .padding(5.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(id = R.string.numericTriggerType_bigger),
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
                    chooseType(ERGBTriggerType_numeric.Smaller)
                }
                .background(
                    color = if (typeSelected == ERGBTriggerType_numeric.Smaller) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.6f
                    )
                )
                .padding(5.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(id = R.string.numericTriggerType_smaller),
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
                    chooseType(ERGBTriggerType_numeric.Equal)
                }
                .background(
                    color = if (typeSelected == ERGBTriggerType_numeric.Equal) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.6f
                    )
                )
                .padding(5.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(id = R.string.numericTriggerType_equal),
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
                    chooseType(ERGBTriggerType_numeric.Inbetween)
                }
                .background(
                    color = if (typeSelected == ERGBTriggerType_numeric.Inbetween) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.6f
                    )
                )
                .padding(5.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(id = R.string.numericTriggerType_between),
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
                    chooseType(ERGBTriggerType_numeric.NotInBetween)
                }
                .background(
                    color = if (typeSelected == ERGBTriggerType_numeric.NotInBetween) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.6f
                    )
                )
                .padding(5.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(id = R.string.numericTriggerType_NotBetween),
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.background,
            )
        }
    }
    Text(
        text = stringResource(id = R.string.addTriggerScreen_valueType_title_RGB_context),
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
                    chooseContext(ERGBTriggerType_context.R)
                }
                .background(
                    color = if (contextSelected == ERGBTriggerType_context.R) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.6f
                    )
                )
                .padding(5.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(id = R.string.rgbTriggerContext_R),
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
                    chooseContext(ERGBTriggerType_context.G)
                }
                .background(
                    color = if (contextSelected == ERGBTriggerType_context.G) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.6f
                    )
                )
                .padding(5.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(id = R.string.rgbTriggerContext_G),
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
                    chooseContext(ERGBTriggerType_context.B)
                }
                .background(
                    color = if (contextSelected == ERGBTriggerType_context.B) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.6f
                    )
                )
                .padding(5.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(id = R.string.rgbTriggerContext_B),
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.background,
            )
        }
    }
}
