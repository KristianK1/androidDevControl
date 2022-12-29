package hr.kristiankliskovic.devcontrol.ui.login

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.AppNameCursive
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.OutlineTextWrapper

@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel,
    registerInstead: () -> Unit,
) {
    LoginScreen(
        login = loginViewModel::login,
        registerInstead = registerInstead,
    )
}

@Composable
fun LoginScreen(
    login: (String, String) -> Unit,
    registerInstead: () -> Unit,
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

        AppNameCursive()

        Text(
            text = stringResource(id = R.string.loginScreen_title),
            fontSize = 40.sp,
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.login_register_screen_title_top_padding),
                    bottom = 0.dp,
                    start = 0.dp,
                    end = 0.dp
                ),
        )

        OutlineTextWrapper(
            initValue = username,
            label = stringResource(id = R.string.loginRegisterScreen_usernameText),
            placeholder = stringResource(id = R.string.loginRegisterScreen_usernameHint),
            onChange = {
                username = it
            },
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.login_register_screen_paddingAbove_text_input),
                    bottom = 0.dp,
                    start = 0.dp,
                    end = 0.dp
                ),
        )

        OutlineTextWrapper(
            initValue = password,
            label = stringResource(id = R.string.loginRegisterScreen_passwordText),
            placeholder = stringResource(id = R.string.loginRegisterScreen_passwordHint),
            hidden = true,
            onChange = {
                password = it
            },
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.login_register_screen_paddingAbove_text_input),
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
                .padding(dimensionResource(id = R.dimen.login_register_screen_padding_outside_button))
                .clip(CircleShape)
                .background(
                    color = colorResource(id = R.color.loginRegisterScreen_button_color),
                )
                .clickable {
                    login(username, password)
                }
                .padding(
                    horizontal = dimensionResource(id = R.dimen.login_register_screen_padding_outside_button_horizontal),
                    vertical = dimensionResource(id = R.dimen.login_register_screen_padding_outside_button_vertical)
                )
        )

        Text(
            text = stringResource(id = R.string.loginScreen_register_instead),
            color = colorResource(id = R.color.text_link_color),
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(
                    horizontal = 0.dp,
                    vertical = dimensionResource(id = R.dimen.login_register_screen_linkToOtherScreen_padding_vertical)
                )
                .clickable {
                    registerInstead()
                }
        )
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(
        login = { u, p ->
            Log.i("login", "$u|$p")
        },
        registerInstead = {
            Log.i("login", "register")
        }
    )
}
