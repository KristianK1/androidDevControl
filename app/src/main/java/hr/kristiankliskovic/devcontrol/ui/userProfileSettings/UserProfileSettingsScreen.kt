package hr.kristiankliskovic.devcontrol.ui.userProfileSettings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.TextListOption
import hr.kristiankliskovic.devcontrol.ui.userProfileSettings.di.userProfileSettingsModule

@Composable
fun UserProfileSettingsRoute(
    navigateToChangePasswordScreen: () -> Unit,
    userProfileSettingsViewModel: UserProfileSettingsViewModel,
) {
    val viewState by userProfileSettingsViewModel.userProfileSettingsViewState.collectAsState()

    UserProfileSettingsScreen(
        viewState = viewState,
        navigateToChangePasswordScreen = navigateToChangePasswordScreen,
        deleteUserProfile = userProfileSettingsViewModel::deleteUser,
        logout = userProfileSettingsViewModel::logout,
        logoutAllSessions = userProfileSettingsViewModel::logoutAllSessions,
    )
}

@Composable
fun UserProfileSettingsScreen(
    viewState: UserProfileSettingsViewState,
    navigateToChangePasswordScreen: () -> Unit,
    deleteUserProfile: () -> Unit,
    logout: () -> Unit,
    logoutAllSessions: () -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
    ) {

        Text(
            text = "Username:\n${viewState.username}\nUser ID: ${viewState.userId}",
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.userProfileSettingsScreen_userData_padding))
        )
        Line()
        TextListOption(
            text = stringResource(id = R.string.userSettings_changePassword_button),
            onClick = navigateToChangePasswordScreen
        )
        Line()
        TextListOption(
            text = stringResource(id = R.string.userSettings_logout_button),
            onClick = logout
        )
        Line()
        TextListOption(
            text = stringResource(id = R.string.userSettings_logout_all_sessions_button),
            onClick = logoutAllSessions
        )
        Line()
        TextListOption(
            text = stringResource(id = R.string.userSettings_deleteProfile_button),
            onClick = deleteUserProfile
        )
        Line()
    }
}

@Composable
fun Line(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.userSettings_dividerLine_height))
            .background(colorResource(id = R.color.userSettings_dividerLine))
    )
}

@Preview
@Composable
fun PreviewUserProfileSettingsScreen() {
    UserProfileSettingsScreen(
        viewState = UserProfileSettingsViewState.getEmptyObject(),
        navigateToChangePasswordScreen = {

        },
        deleteUserProfile = {

        },
        logout = {

        },
        logoutAllSessions = {

        }
    )
}
