package hr.kristiankliskovic.devcontrol.ui.register

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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


private const val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
@Composable
fun RegisterRoute(
    registerViewModel: RegisterViewModel,
    loginInstead: () -> Unit,
) {
    RegisterScreen(
        register = registerViewModel::register,
        loginInstead = loginInstead
    )
}

@Composable
fun RegisterScreen(
    register: (String, String, String) -> Unit,
    loginInstead: () -> Unit,
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
        var passwordAgain by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        AppNameCursive()

        Text(
            text = stringResource(id = R.string.registerScreen_title),
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.primary,
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

        OutlineTextWrapper(
            initValue = passwordAgain,
            label = stringResource(id = R.string.registerScreen_passwordAgainHint),
            placeholder = stringResource(id = R.string.registerScreen_passwordAgainText),
            hidden = true,
            onChange = {
                passwordAgain = it
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
            initValue = email,
            label = stringResource(id = R.string.registerScreen_emailHint),
            placeholder = stringResource(id = R.string.registerScreen_emailText),
            onChange = {
                email = it
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
            text = stringResource(id = R.string.registerScreen_register_button),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.login_register_screen_padding_outside_button))
                .clip(CircleShape)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                )
                .clickable {
                    if(password != passwordAgain){
                        Log.i("regiterErrors","unequal passwords")
                    }
                    else if(email.isNotEmpty() && !EMAIL_REGEX.toRegex().matches(email)){
                        Log.i("regiterErrors","not an email")
                    }else{
                        register(username, password, email)
                    }
                }
                .padding(
                    horizontal = dimensionResource(id = R.dimen.login_register_screen_padding_outside_button_horizontal),
                    vertical = dimensionResource(id = R.dimen.login_register_screen_padding_outside_button_vertical)
                )
        )

        Text(
            text = stringResource(id = R.string.registerScreen_login_instead),
            color = MaterialTheme.colorScheme.tertiary,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(
                    horizontal = 0.dp,
                    vertical = dimensionResource(id = R.dimen.login_register_screen_linkToOtherScreen_padding_vertical)
                )
                .clickable {
                    loginInstead()
                }
        )
    }
}

@Preview
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen(
        register = { u, p, e ->
        },
        loginInstead = {
        })
}
