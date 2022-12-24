package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields

import android.util.Log
import android.view.Gravity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.common.FieldTitle
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes

data class ButtonFieldInputViewState(
    val fieldId: Int,
    val name: String,
    val currentValue: Boolean,
) : BasicFieldComponentViewState()

@Composable
fun ButtonFieldInput(
    item: ButtonFieldInputViewState,
    emitValue: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .height(30.dp)
            .border(
                width = dimensionResource(id = R.dimen.fieldComponent_borderThickness),
                color = colorResource(id = R.color.fieldComponent_border)
            )
            .padding(dimensionResource(id = R.dimen.fieldComponent_padding))
            .fillMaxWidth()
    ) {
        FieldTitle(item.name)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text =
                    if (item.currentValue) stringResource(id = R.string.buttonField_ON_text)
                    else stringResource(id = R.string.buttonField_OFF_text),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color =
                    if (item.currentValue) colorResource(id = R.color.buttonField_ON)
                    else colorResource(id = R.color.buttonField_OFF),
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(dimensionResource(id = R.dimen.fieldComponent_button_padding))
                    .clip(Shapes.small)
                    .background(colorResource(id = R.color.fieldComponent_button_background))
                    .clickable {
                        emitValue(!item.currentValue)
                    }
            ) {
                Text(
                    text =
                    if (item.currentValue) stringResource(id = R.string.buttonField_change_to_OFF_text)
                    else stringResource(id = R.string.buttonField_change_to_ON_text),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.fieldComponent_button_text_padding),
                            vertical = 0.dp
                        )
                )
            }
        }

    }
}

@Preview
@Composable
fun PreviewButtonFieldInput() {
    val state = ButtonFieldInputViewState(
        fieldId = 0,
        name = "BTN state 1",
        currentValue = !false,
    )
    ButtonFieldInput(
        item = state,
        emitValue = {
            Log.i("btn State", "" + it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
    )
}
