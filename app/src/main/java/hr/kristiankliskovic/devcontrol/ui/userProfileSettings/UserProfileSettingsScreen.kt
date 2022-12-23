package hr.kristiankliskovic.devcontrol.ui.userProfileSettings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.ScreenTitle
import kotlin.math.log


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
        ScreenTitle(
            screenTitle = "User profile settings"
        )
        fText(
            text = "Change password",
            func = navigateToChangePasswordScreen
        )
        Line()
        fText(
            text = "Logout",
            func = logout
        )
        Line()
        fText(
            text = "delete User profile",
            func = deleteUserProfile
        )
        Line()
    }
}

@Composable
fun fText(
    text: String,
    func: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                func()
            }
            .padding(
                vertical = 0.dp,
                horizontal = 10.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 25.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Composable
fun Line(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Color.Black)
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
