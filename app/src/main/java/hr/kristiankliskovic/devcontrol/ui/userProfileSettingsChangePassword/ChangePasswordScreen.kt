package hr.kristiankliskovic.devcontrol.ui.userProfileSettingsChangePassword

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.OutlineTextWrapper
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.ScreenSubtitle
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.ScreenTitle
import kotlin.math.log

@Composable
fun ChangePasswordScreen(
    changePassword: (String, String) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(50.dp)
            .fillMaxWidth()
    ) {
        var oldPassword by remember { mutableStateOf("") }
        var newPassword by remember { mutableStateOf("") }
        var newPasswordAgain by remember { mutableStateOf("") }
        var logoutCheckboxState by remember { mutableStateOf(false) }

//        ScreenTitle(screenTitle = "User profile settings")
//        ScreenSubtitle(subtitle = "Change password")

        OutlineTextWrapper(
            label = stringResource(id = R.string.changePasswordScreen_oldPassword_label),
            onChange = {
                oldPassword = it
            }
        )
        Spacer(modifier = Modifier
            .height(dimensionResource(id = R.dimen.changePasswordScreen_spacer_height))
        )
        OutlineTextWrapper(
            label = stringResource(id = R.string.changePasswordScreen_newPassword_label),
            onChange = {
                oldPassword = it
            }
        )
        Spacer(modifier = Modifier
            .height(dimensionResource(id = R.dimen.changePasswordScreen_spacer_height))
        )
        OutlineTextWrapper(
            label = stringResource(id = R.string.changePasswordScreen_newPasswordAgain_label),
            onChange = {
                oldPassword = it
            }
        )
        Spacer(modifier = Modifier
            .height(dimensionResource(id = R.dimen.changePasswordScreen_spacer_height))
        )
        Row(
            modifier = Modifier
                .padding(15.dp)
                .clickable {
                    logoutCheckboxState = !logoutCheckboxState
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = logoutCheckboxState,
                onCheckedChange = null
            )
            Text(
                text = stringResource(id = R.string.changePasswordScreen_logout_from_other_devices_checkbox),
                fontSize = 15.sp
            )
        }
        Text(
            text = stringResource(id = R.string.changePasswordScreen_changePasswordButton),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(20.dp)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(5.dp)
                )
                .clickable {
                    if (newPassword == newPasswordAgain) {
                        changePassword(oldPassword, newPassword)
                    } else {
                        //toast
                    }
                }
                .padding(
                    horizontal = 20.dp,
                    vertical = 10.dp
                )
        )
    }
}

@Preview
@Composable
fun PreviewChangePasswordScreen() {
    ChangePasswordScreen(
        changePassword = { _, _ ->

        })
}
