package hr.kristiankliskovic.devcontrol.ui.login

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.OutlineTextWrapper

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
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
    ) {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

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

        OutlineTextWrapper(
            label = stringResource(id = R.string.loginRegisterScreen_usernameText),
            placeholder = stringResource(id = R.string.loginRegisterScreen_usernameHint),
            onChange = {
                username = it
            },
            modifier = Modifier
                .padding(
                    top = 5.dp,
                    bottom = 0.dp,
                    start = 0.dp,
                    end = 0.dp
                ),
        )

        OutlineTextWrapper(
            label = stringResource(id = R.string.loginRegisterScreen_passwordText),
            placeholder = stringResource(id = R.string.loginRegisterScreen_passwordHint),
            hidden = true,
            onChange = {
                password = it
            },
            modifier = Modifier
                .padding(
                    top = 5.dp,
                    bottom = 0.dp,
                    start = 0.dp,
                    end = 0.dp
                ),
        )

        Text(
            text = stringResource(id = R.string.loginScreen_login_button),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(20.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(
                    color = Color.LightGray,
                )
                .clickable {
                    login(username, password)
                }
                .padding(
                    horizontal = 60.dp,
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
