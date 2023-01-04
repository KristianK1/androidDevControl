package hr.kristiankliskovic.devcontrol.ui.userProfileSettingsChangePassword

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.OutlineTextWrapper
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes

@Composable
fun ChangePasswordRoute(
    viewModel: ChangePasswordViewModel,
    navigateBackToUserSettings: () -> Unit,
) {
//    val navigateBack = viewModel.navBack.collectAsState()
//
//    if(navigateBack.value){
//    }

    ChangePasswordScreen(
        changePassword = viewModel::changePassword,
    )
}

@Composable
fun ChangePasswordScreen(
    changePassword: (String, String, Boolean) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
    ) {
        var oldPassword by remember { mutableStateOf("") }
        var newPassword by remember { mutableStateOf("") }
        var newPasswordAgain by remember { mutableStateOf("") }
        var logoutCheckboxState by remember { mutableStateOf(false) }

//        ScreenTitle(screenTitle = "User profile settings")
//        ScreenSubtitle(subtitle = "Change password")
        Spacer(modifier = Modifier
            .height(dimensionResource(id = R.dimen.changePasswordScreen_top_bottom_spacer_height))
        )
        OutlineTextWrapper(
            label = stringResource(id = R.string.changePasswordScreen_oldPassword_label),
            onChange = {
                oldPassword = it
            }
        )
        Spacer(modifier = Modifier
            .height(dimensionResource(id = R.dimen.changePasswordScreen_textbox_spacer_height))
        )
        OutlineTextWrapper(
            label = stringResource(id = R.string.changePasswordScreen_newPassword_label),
            onChange = {
                newPassword = it
            }
        )
        Spacer(modifier = Modifier
            .height(dimensionResource(id = R.dimen.changePasswordScreen_textbox_spacer_height))
        )
        OutlineTextWrapper(
            label = stringResource(id = R.string.changePasswordScreen_newPasswordAgain_label),
            onChange = {
                newPasswordAgain = it
            }
        )
        Spacer(modifier = Modifier
            .height(dimensionResource(id = R.dimen.changePasswordScreen_textbox_spacer_height))
        )
        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.changePasswordScreen_logout_padding))
                .clickable {
                    logoutCheckboxState = !logoutCheckboxState
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = logoutCheckboxState,
                onCheckedChange = null
            )
            Spacer(
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.changePasswordScreen_checkbox_spacer_width))
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
                .padding(dimensionResource(id = R.dimen.changePasswordScreen_button_margin))
                .background(
                    color = Color.LightGray,
                    shape = Shapes.small,
                )
                .clickable {
                    if (newPassword == newPasswordAgain) {
                        changePassword(oldPassword, newPassword, logoutCheckboxState)
                    } else {
                        //toast
                    }
                }
                .padding(
                    horizontal = dimensionResource(id = R.dimen.changePasswordScreen_button_padding_horizontal),
                    vertical = dimensionResource(id = R.dimen.changePasswordScreen_button_padding_vertical)
                )
        )
        Spacer(modifier = Modifier
            .height(dimensionResource(id = R.dimen.changePasswordScreen_top_bottom_spacer_height))
        )
    }
}

@Preview
@Composable
fun PreviewChangePasswordScreen() {
    ChangePasswordScreen(
        changePassword = { _, _, _ ->
            
        },
    )
}
