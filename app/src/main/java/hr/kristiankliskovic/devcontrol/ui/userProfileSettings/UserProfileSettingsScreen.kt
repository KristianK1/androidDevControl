package hr.kristiankliskovic.devcontrol.ui.userProfileSettings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.TextListOption

@Composable
fun UserProfileSettingsRoute() {
    UserProfileSettingsScreen(
        navigateToChangePasswordScreen = {

        },
        deleteUserProfile = {

        },
        logout = {

        }
    )
}

@Composable
fun UserProfileSettingsScreen(
    navigateToChangePasswordScreen: () -> Unit,
    deleteUserProfile: () -> Unit,
    logout: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
//        ScreenTitle(
//            screenTitle = "User profile settings"
//        )
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
        navigateToChangePasswordScreen = {

        },
        deleteUserProfile = {

        },
        logout = {

        }
    )
}
