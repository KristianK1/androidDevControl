package hr.kristiankliskovic.devcontrol.ui.triggerSettings_seeAll

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.triggerViewComponents.TriggerItem

@Composable
fun SeeAllTriggersRoute(
    viewModel: SeeAllTriggersViewModel,
) {
    val viewState = viewModel.viewState.collectAsState()
    val devices by viewModel.devices.collectAsState() //dont use this
    SeeAllTriggersScreen(
        viewState = viewState.value,
        deleteTrigger = {
            viewModel.deleteTrigger(it)
        }
    )
}

@Composable
fun SeeAllTriggersScreen(
    viewState: SeeAllTriggersViewState?,
    deleteTrigger: (Int) -> Unit,
) {
    if(viewState == null){
        Text(text = "Couldn't get triggers from database")
        return
    }

    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
    ) {
        for(trigger in viewState.triggers){
            TriggerItem(
                viewState = trigger,
                deleteTrigger = {
                    deleteTrigger(it)
                }
            )
        }
    }
}
