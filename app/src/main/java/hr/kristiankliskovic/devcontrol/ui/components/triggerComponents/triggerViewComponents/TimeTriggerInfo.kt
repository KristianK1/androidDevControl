package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.triggerViewComponents

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.ETriggerTimeType
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents.TriggerItemLine
import java.util.Calendar

data class TimeTriggerInfoViewState(
    val timeStamp: String,
    val type: ETriggerTimeType,
    val lastFired: String,
)

@Composable
fun TimeTriggerInfo(
    viewState: TimeTriggerInfoViewState,
){
    TriggerItemLine(
        propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerTimeSourceType_propertyName),
        propertyValue = stringResource(id = if(viewState.type == ETriggerTimeType.Once) R.string.triggerSource_TimeType_once else if(viewState.type == ETriggerTimeType.Daily) R.string.triggerSource_TimeType_daily else R.string.triggerSource_TimeType_weekly)
    )
    TriggerItemLine(
        propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerTimeSourceFirstTimeStamp_propertyName),
        propertyValue = viewState.timeStamp
    )
    TriggerItemLine(
        propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerTimeSourceLastTimeFired_propertyName),
        propertyValue = viewState.lastFired
    )
}
