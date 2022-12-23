package hr.kristiankliskovic.devcontrol.ui.userProfileSettingsChangePassword

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.ScreenSubtitle
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.ScreenTitle

@Composable
fun ChangePasswordScreen(
    changePassword: (String, String) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        var oldPassword by remember { mutableStateOf(TextFieldValue("")) }
        var newPassword by remember { mutableStateOf(TextFieldValue("")) }
        var newPasswordAgain by remember { mutableStateOf(TextFieldValue("")) }

        ScreenTitle(screenTitle = "User profile settings")
        ScreenSubtitle(subtitle = "Change password")
        
        OutlinedTextField(
            value = oldPassword,
            onValueChange = { newText: TextFieldValue ->
                oldPassword = newText
            },
            label = {
                Text(
                    text = "Old password"
                )
            },
            modifier = Modifier
                .padding(
                    top = 5.dp,
                    bottom = 0.dp,
                    start = 0.dp,
                    end = 0.dp
                )
        )
        OutlinedTextField(
            value = newPassword,
            onValueChange = { newText: TextFieldValue ->
                newPassword = newText
            },
            visualTransformation = PasswordVisualTransformation(),

            label = {
                Text(
                    text = stringResource(id = R.string.loginRegisterScreen_passwordText)
                )
            },
            modifier = Modifier
                .padding(
                    top = 5.dp,
                    bottom = 0.dp,
                    start = 0.dp,
                    end = 0.dp
                )
        )
        OutlinedTextField(
            value = newPasswordAgain,
            onValueChange = { newText: TextFieldValue ->
                newPasswordAgain = newText
            },
            visualTransformation = PasswordVisualTransformation(),

            label = {
                Text(
                    text = "Repeat new password"
                )
            },
            modifier = Modifier
                .padding(
                    top = 5.dp,
                    bottom = 0.dp,
                    start = 0.dp,
                    end = 0.dp
                )
        )

        Text(
            text = "Change password",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(20.dp)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(5.dp)
                )
                .clickable {
                    if (newPassword.text == newPasswordAgain.text) {
                        changePassword(oldPassword.text, newPassword.text)
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
