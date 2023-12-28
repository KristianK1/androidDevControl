package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import hr.kristiankliskovic.devcontrol.model.ETriggerResponseType

@Composable
fun TypeOfResponse(
    chooseType: (ETriggerResponseType) -> Unit,
    typeSelected: ETriggerResponseType,
) {
    Column {
        Text(
            text = stringResource(id = R.string.triggerResponse_Type_title),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
        )
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
        ) {
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
            ){
                Box(
                    modifier = Modifier
                        .weight(1f, true)
                        .fillMaxHeight()
                        .clickable {
                            chooseType(ETriggerResponseType.SettingValue_fieldInGroup)
                        }
                        .background(
                            color = if (typeSelected == ETriggerResponseType.SettingValue_fieldInGroup) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.6f
                            )
                        )
                        .padding(7.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    androidx.compose.material3.Text(
                        text = stringResource(id = R.string.triggerResponse_Type_fieldInGroup),
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
                            chooseType(ETriggerResponseType.SettingValue_fieldInComplexGroup)
                        }
                        .background(
                            color = if (typeSelected == ETriggerResponseType.SettingValue_fieldInComplexGroup) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.6f
                            )
                        )
                        .padding(7.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    androidx.compose.material3.Text(
                        text = stringResource(id = R.string.triggerResponse_Type_fieldInComplexGroup),
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.background,
                    )
                }
            }

            Box(
                modifier = Modifier
                    .height(5.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f, true)
                        .fillMaxHeight()
                        .clickable {
                            chooseType(ETriggerResponseType.Email)
                        }
                        .background(
                            color = if (typeSelected == ETriggerResponseType.Email) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.6f
                            )
                        )
                        .padding(7.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    androidx.compose.material3.Text(
                        text = stringResource(id = R.string.triggerResponse_Type_email),
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
                            chooseType(ETriggerResponseType.MobileNotification)
                        }
                        .background(
                            color = if (typeSelected == ETriggerResponseType.MobileNotification) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.6f
                            )
                        )
                        .padding(7.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(id = R.string.triggerResponse_Type_mobileNotification),
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.background,
                    )
                }
            }
        }
    }
}
