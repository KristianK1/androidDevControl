package hr.kristiankliskovic.devcontrol.ui.components.otherComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.ETriggerSourceType

@Composable
fun RadioButtonRow(
    selected: Boolean,
    text: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        RadioButton(
            selected = selected,
            onClick = null
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.radiobuttonRow_spacer_width)))
        Text(
            text = text
        )
    }
}
