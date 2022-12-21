package hr.kristiankliskovic.devcontrol.ui.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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

@Composable
fun LoginRoute(
    login: (String, String) -> Unit,
) {
    LoginScreen(
        login = login
    )
}

@Composable
fun LoginScreen(
    login: (String, String) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        var username by remember { mutableStateOf(TextFieldValue("")) }
        var password by remember { mutableStateOf(TextFieldValue("")) }

        Text(
            text = stringResource(id = R.string.login_register_screen_app_name),
            fontSize = 80.sp,
            fontFamily = FontFamily.Cursive,
            modifier = Modifier
                .padding(
                    top = 30.dp,
                    bottom = 0.dp,
                    start = 0.dp,
                    end = 0.dp
                ),
        )

        Text(
            text = stringResource(id = R.string.loginScreen_title),
            fontSize = 40.sp,
            modifier = Modifier
                .padding(
                    top = 30.dp,
                    bottom = 0.dp,
                    start = 0.dp,
                    end = 0.dp
                ),
        )

        OutlinedTextField(
            value = username,
            onValueChange = { newText: TextFieldValue ->
                username = newText
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.loginScreen_usernameHint)
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.loginScreen_usernameText)
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
            value = password,
            onValueChange = { newText: TextFieldValue ->
                password = newText
            },
            visualTransformation = PasswordVisualTransformation(),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.loginScreen_passwordHint),
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.loginScreen_passwordText)
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
            text = stringResource(id = R.string.loginScreen_login_button),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(20.dp)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(5.dp)
                )
                .clickable {
                    login(username.text, password.text)
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
fun PreviewLoginScreen() {
    LoginScreen(login = { u, p ->
        Log.i("login", "$u|$p")
    })
}
