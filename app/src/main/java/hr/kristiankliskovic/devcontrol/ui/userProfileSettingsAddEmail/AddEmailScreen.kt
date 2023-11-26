package hr.kristiankliskovic.devcontrol.ui.userProfileSettingsAddEmail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.OutlineTextWrapper
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes
import hr.kristiankliskovic.devcontrol.ui.userProfileSettingsAddEmail.di.AddEmailViewModel

@Composable
fun AddEmailRoute(
    viewModel: AddEmailViewModel,
){
    AddEmailScreen(
        addEmail = viewModel::addEmail
    )
}

@Composable
fun AddEmailScreen(
    addEmail: (String) -> Unit,
){
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
    ) {
        var email by remember { mutableStateOf("") }

//        ScreenTitle(screenTitle = "User profile settings")
//        ScreenSubtitle(subtitle = "Add Email")
        Spacer(modifier = Modifier
            .height(dimensionResource(id = R.dimen.addEmailScreen_top_bottom_spacer_height))
        )
        OutlineTextWrapper(
            label = stringResource(id = R.string.addEmailScreen_email_label),
            onChange = {
                email = it
            }
        )
        Spacer(modifier = Modifier
            .height(dimensionResource(id = R.dimen.addEmailScreen_textbox_spacer_height))
        )
        Text(
            text = stringResource(id = R.string.addEmailScreen_changePasswordButton),
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.addEmailScreen_button_margin))
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = Shapes.small,
                )
                .clickable {
                    addEmail(email)
                }
                .padding(
                    horizontal = dimensionResource(id = R.dimen.addEmailScreen_button_padding_horizontal),
                    vertical = dimensionResource(id = R.dimen.addEmailScreen_button_padding_vertical)
                )
        )
        Spacer(modifier = Modifier
            .height(dimensionResource(id = R.dimen.addEmailScreen_top_bottom_spacer_height))
        )
    }
}
