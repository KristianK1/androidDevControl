package hr.kristiankliskovic.devcontrol.ui.triggerSettings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.navigation.ChangeDeviceAdminDestination
import hr.kristiankliskovic.devcontrol.navigation.seeAllPermissionsDestination
import hr.kristiankliskovic.devcontrol.ui.adminPanelDevice.Line
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.TextListOption

@Composable
fun TriggerSettingsRoute(
    navigateToAddTrigger: () -> Unit,
    navigateToSeeAllTriggers: () -> Unit,
) {
    TriggerSettingsScreen(
        navigateToAddTrigger = navigateToAddTrigger,
        navigateToSeeAllTriggers = navigateToSeeAllTriggers,
    )
}

@Composable
fun TriggerSettingsScreen(
    navigateToAddTrigger: () -> Unit,
    navigateToSeeAllTriggers: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextListOption(
            text = stringResource(id = R.string.triggerSettings_navToAddTrigger),
            onClick = {
                navigateToAddTrigger()
            }
        )
        Line()
        TextListOption(
            text = stringResource(id = R.string.triggerSettings_navToSeeAllTriggers),
            onClick = {
                navigateToSeeAllTriggers()
            }
        )
        Line()
    }
}
