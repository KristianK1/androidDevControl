package hr.kristiankliskovic.devcontrol.ui.components.otherComponents

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R

@Composable
fun AppNameCursive(
    modifier: Modifier = Modifier
){
    Text(
        text = stringResource(id = R.string.login_register_screen_app_name),
        fontSize = 80.sp,
        fontFamily = FontFamily.Cursive,
        modifier = modifier
            .padding(
                top = dimensionResource(id = R.dimen.login_register_screen_app_name_topPadding),
                bottom = 0.dp,
                start = 0.dp,
                end = 0.dp
            ),
    )
}
