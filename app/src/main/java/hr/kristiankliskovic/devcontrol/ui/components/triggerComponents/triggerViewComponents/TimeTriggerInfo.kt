package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.triggerViewComponents

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.ETriggerTimeType
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents.TriggerItemLine
import java.time.DayOfWeek
import java.util.Calendar

data class TimeTriggerInfoViewState(
    val timeStamp: String,
    val type: ETriggerTimeType,
    val lastFired: String,
    val daysOfWeek: List<Boolean>
)

@Composable
fun TimeTriggerInfo(
    viewState: TimeTriggerInfoViewState,
){
    TriggerItemLine(
        propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerTimeSourceType_propertyName),
        propertyValue = stringResource(id = if(viewState.type == ETriggerTimeType.Once) R.string.triggerSource_TimeType_once else if(viewState.type == ETriggerTimeType.Daily) R.string.triggerSource_TimeType_daily else if(viewState.type == ETriggerTimeType.Weekly) R.string.triggerSource_TimeType_weekly else R.string.triggerSource_TimeType_daysOfWeek)
    )
    if(viewState.type == ETriggerTimeType.DaysInWeek){
        TriggerItemLine(
            propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerTimeDaysOfTheWeek_propertyName),
            propertyValue = getDaysOfTheWeekText(viewState.daysOfWeek)
        )
    }
    TriggerItemLine(
        propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerTimeSourceFirstTimeStamp_propertyName),
        propertyValue = viewState.timeStamp
    )
    TriggerItemLine(
        propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerTimeSourceLastTimeFired_propertyName),
        propertyValue = viewState.lastFired
    )
}

@Composable
fun getDaysOfTheWeekText(daysOfWeek: List<Boolean>): String{
    var result = ""
    var length = 0;
    val days: MutableList<Int> = mutableListOf()
    for((index,value) in daysOfWeek.withIndex()){
        if(value) {
            length++;
            when (index) {
                0 -> {
                    result += stringResource(id = R.string.triggerSource_TimeType_Monday) + ", "
                }
                1 -> {
                    result += stringResource(id = R.string.triggerSource_TimeType_Tuesday) + ", "
                }
                2 -> {
                    result += stringResource(id = R.string.triggerSource_TimeType_Wednesday) + ", "
                }
                3 -> {
                    result += stringResource(id = R.string.triggerSource_TimeType_Thursday) + ", "
                }
                4 -> {
                    result += stringResource(id = R.string.triggerSource_TimeType_Friday) + ", "
                }
                5 -> {
                    result += stringResource(id = R.string.triggerSource_TimeType_Saturday) + ", "
                }
                6 -> {
                    result += stringResource(id = R.string.triggerSource_TimeType_Sunday) + ", "
                }
            }
        }
    }
    Log.i("ertz", result)
    if(length > 0)
        result = result.replaceRange(result.length -2, result.length , "")
    if(length >= 2){
        Log.i("ertz", result)
        val start = result.lastIndexOf(',')
        val end = start + 1
        Log.i("ertz", ""+start)
        Log.i("ertz", ""+end)

        result = result.replaceRange(
            start,
            end ,
            " and"
        )
    }
    return result
}
