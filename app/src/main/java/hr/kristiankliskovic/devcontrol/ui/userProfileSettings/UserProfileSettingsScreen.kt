package hr.kristiankliskovic.devcontrol.ui.userProfileSettings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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

@Composable
fun UserProfileSettingsRoute(
    navigateToChangePasswordScreen: () -> Unit,
    navigateToAddEmailScreen: () -> Unit,
    userProfileSettingsViewModel: UserProfileSettingsViewModel,
) {
    val viewState by userProfileSettingsViewModel.userProfileSettingsViewState.collectAsState()

    UserProfileSettingsScreen(
        viewState = viewState,
        navigateToChangePasswordScreen = navigateToChangePasswordScreen,
        deleteUserProfile = userProfileSettingsViewModel::deleteUser,
        logout = userProfileSettingsViewModel::logout,
        logoutAllSessions = userProfileSettingsViewModel::logoutAllSessions,
        navigateToAddEmailScreen = navigateToAddEmailScreen,
    )
}

@Composable
fun UserProfileSettingsScreen(
    viewState: UserProfileSettingsViewState,
    navigateToChangePasswordScreen: () -> Unit,
    navigateToAddEmailScreen: () -> Unit,
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
            text = "Username:  ${viewState.username}\n\nUser ID:  ${viewState.userId}${if(viewState.email.isNotEmpty()) "\n\nEmail:  ${viewState.email}" else ""}",
            fontSize = 25.sp,
            textAlign = TextAlign.Left,
            color = MaterialTheme.colorScheme.inverseSurface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.userProfileSettingsScreen_userData_padding))
        )
        TextListOption(
            text = stringResource(id = R.string.userSettings_changePassword_button),
            onClick = navigateToChangePasswordScreen
        )
        TextListOption(
            text = stringResource(id = R.string.userSettings_logout_button),
            onClick = logout
        )
        TextListOption(
            text = stringResource(id = R.string.userSettings_logout_all_sessions_button),
            onClick = logoutAllSessions
        )
        TextListOption(
            text = stringResource(id = R.string.userSettings_deleteProfile_button),
            onClick = deleteUserProfile
        )
        if(viewState.email.isEmpty()){
            TextListOption(
                text = stringResource(id = R.string.userSettings_addEmail_button),
                onClick = navigateToAddEmailScreen
            )
        }
    }
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

        },
        navigateToAddEmailScreen = {

        }
    )
}
