package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.ETriggerSourceType
import hr.kristiankliskovic.devcontrol.model.ETriggerTimeType
import java.util.Calendar

@Composable
fun TypeOfTimeSource(
    typeSelected: ETriggerTimeType,
    chooseType: (ETriggerTimeType) -> Unit,
    timeSourceTime: Int?,
    timeSourceDate: Calendar?,
    setTime: (Int, Int) -> Unit,
    setDate: (Int, Int, Int) -> Unit,
    daysOfTheWeek: List<Boolean>,
    selectDayOfWeek: (Int, Boolean) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.addTriggerScreen_timeSettings_selectTitle),
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
                        chooseType(ETriggerTimeType.Once)
                    }
                    .background(
                        color = if (typeSelected == ETriggerTimeType.Once) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.6f
                        )
                    )
                    .padding(10.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.triggerSource_TimeType_once),
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
                        chooseType(ETriggerTimeType.Daily)
                    }
                    .background(
                        color = if (typeSelected == ETriggerTimeType.Daily) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.6f
                        )
                    )
                    .padding(10.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.triggerSource_TimeType_daily),
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
                        chooseType(ETriggerTimeType.Weekly)
                    }
                    .background(
                        color = if (typeSelected == ETriggerTimeType.Weekly) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.6f
                        )
                    )
                    .padding(10.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.triggerSource_TimeType_weekly),
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
                        chooseType(ETriggerTimeType.DaysInWeek)
                    }
                    .background(
                        color = if (typeSelected == ETriggerTimeType.DaysInWeek) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.6f
                        )
                    )
                    .padding(10.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.triggerSource_TimeType_daysOfWeek),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.background,
                )
            }
        }
        if(typeSelected == ETriggerTimeType.DaysInWeek){
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(0.dp, 10.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .height(IntrinsicSize.Max)
            ) {
                for ((index, day) in daysOfTheWeek.withIndex()){
                    Box(
                        modifier = Modifier
                            .weight(1f, true)
                            .fillMaxHeight()
                            .clickable {
                                selectDayOfWeek(index, !day)
                            }
                            .background(
                                color = if (day) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                                    alpha = 0.6f
                                )
                            )
                            .padding(10.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = stringResource(id = when(index){
                                0 -> R.string.triggerSource_TimeType_Monday
                                1 -> R.string.triggerSource_TimeType_Tuesday
                                2 -> R.string.triggerSource_TimeType_Wednesday
                                3 -> R.string.triggerSource_TimeType_Thursday
                                4 -> R.string.triggerSource_TimeType_Friday
                                5 -> R.string.triggerSource_TimeType_Saturday
                                else -> R.string.triggerSource_TimeType_Sunday
                            }),
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.background,
                        )
                    }
                    if(index != daysOfTheWeek.lastIndex){
                        Box(
                            modifier = Modifier
                                .width(2.dp)
                                .background(MaterialTheme.colorScheme.background)
                                .fillMaxHeight()
                        )
                    }
                }
            }
        }
        TimeSelection(
            time = timeSourceTime,
            saveTime = { hour, minute ->
                setTime(hour, minute)
            }
        )
        if(typeSelected == ETriggerTimeType.Once || typeSelected == ETriggerTimeType.Weekly){
            DateSelection(
                date = timeSourceDate,
                saveDate = { year, month, day ->
                    setDate(year, month, day)
                }
            )
        }
    }
}
