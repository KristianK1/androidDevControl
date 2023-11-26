package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
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
import androidx.compose.ui.window.DialogProperties
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.common.FieldTitle
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes

data class MultipleChoiceFieldInputViewState(
    val fieldId: Int,
    val name: String,
    val choices: List<String>,
    val currentChoice: Int,
) : BasicFieldComponentViewState()

@Composable
fun MultipleChoiceFieldInput(
    item: MultipleChoiceFieldInputViewState,
    modifier: Modifier = Modifier,
    emitValue: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .border(
                width = dimensionResource(id = R.dimen.fieldComponent_borderThickness),
                color = MaterialTheme.colorScheme.inverseSurface
            )
            .padding(dimensionResource(id = R.dimen.fieldComponent_padding))
            .fillMaxWidth()
    ) {
        FieldTitle(item.name)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = item.choices[item.currentChoice],
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            MultipleChoiceSelector(
                choices = item.choices,
                selectValue = {
                    emitValue(it)
                }
            )
        }
    }
}

@Composable
fun MultipleChoiceSelector(
    choices: List<String>,
    selectValue: (Int) -> Unit,
) {

    var dialogOpen by remember {
        mutableStateOf(false)
    }

    if (dialogOpen) {
        AlertDialog(
            onDismissRequest = {
                dialogOpen = false
            },
            buttons = {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    itemsIndexed(
                        items = choices
                    ) { index, item ->
                        Text(
                            text = item,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.inverseSurface,
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.Multiple_choice_field_dialog_choices_padding))
                                .clickable {
                                    selectValue(index)
                                    dialogOpen = false
                                }
                        )
                    }
                }
            },
            title = {
                Text(
                    text = stringResource(id = R.string.multipleChoiceFieldInput_choose_value_title),
                    color = MaterialTheme.colorScheme.inverseSurface,
                )
            },
            modifier = Modifier // Set the width and padding
                .fillMaxWidth()
                .padding(32.dp)
                .clip(Shapes.small)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.6f)
                ),
            shape = RoundedCornerShape(5.dp), //TODO
            backgroundColor = MaterialTheme.colorScheme.background,
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        )
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxHeight()
            .padding(dimensionResource(id = R.dimen.fieldComponent_button_padding))
            .clip(Shapes.small)
            .background(
                color = MaterialTheme.colorScheme.primary
            )
            .clickable { dialogOpen = true }
    ) {
        Text(
            text = stringResource(id = R.string.multipleChoiceFieldInput_open_selector),
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(id = R.dimen.fieldComponent_button_text_padding),
                    vertical = 0.dp
                )
        )
    }
}

@Preview
@Composable
fun PreviewMultipleChoiceFieldInput() {
    val state = MultipleChoiceFieldInputViewState(
        fieldId = 0,
        name = "MC field 1",
        choices = listOf("Choice1", "Choice2", "Choice3", "Choice4"),
        currentChoice = 1,
    )
    MultipleChoiceFieldInput(
        item = state,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        emitValue = {
        }
    )
}
