package hr.kristiankliskovic.devcontrol.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.TextListOption

@Composable
fun SettingsRoute(
    navigateToUserSettings: () -> Unit,
    navigateToAdminPanel: () -> Unit,
    navigateToTriggerSettings: () -> Unit,
) {
    SettingsScreen(
        navigateToUserSettings = navigateToUserSettings,
        navigateToAdminPanel = navigateToAdminPanel,
        navigateToTriggerSettings = navigateToTriggerSettings
    )
}

@Composable
fun SettingsScreen(
    navigateToUserSettings: () -> Unit,
    navigateToAdminPanel: () -> Unit,
    navigateToTriggerSettings: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextListOption(
            text = stringResource(id = R.string.settingsScreen_navToUserSettings),
            onClick = {
                navigateToUserSettings()
            }
        )
        TextListOption(
            text = stringResource(id = R.string.settingsScreen_navToAdminPanel),
            onClick = {
                navigateToAdminPanel()
            }
        )
        TextListOption(
            text = stringResource(id = R.string.settingsScreen_navToTriggerSettings),
            onClick = {
                navigateToTriggerSettings()
            }
        )
    }
}
