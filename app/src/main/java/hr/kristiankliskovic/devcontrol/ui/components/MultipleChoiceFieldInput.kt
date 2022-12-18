package hr.kristiankliskovic.devcontrol.ui.components

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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import hr.kristiankliskovic.devcontrol.R
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
            .height(30.dp)
            .border(2.dp, Color.Black)
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        FieldTitle(
            item.name
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.choices[item.currentChoice],
                fontSize = 38.sp
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(2.dp)
                    .clip(Shapes.small)
                    .background(Color.LightGray)
                    .clickable {

                    }
            ) {

                MultipleChoiceSelector(
                    choices = item.choices,
                    selectValue = {
                        emitValue(it)
                    })
            }
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
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality,
                // simply leave this block empty.
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
                            modifier = Modifier
                                .padding(15.dp)
                                .clickable {
                                    selectValue(index)
                                    dialogOpen = false
                                }
                        )
                    }
                }
            },
            title = {
                Text(text = stringResource(id = R.string.multipleChoiceFieldInput_choose_value_title))
            },
            modifier = Modifier // Set the width and padding
                .fillMaxWidth()
                .padding(32.dp),
            shape = RoundedCornerShape(5.dp),
            backgroundColor = Color.White,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }

    Text(
        text = stringResource(id = R.string.multipleChoiceFieldInput_open_selector),
        modifier = Modifier
            .padding(12.dp)
            .clickable { dialogOpen = true }
    )
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
            Log.i("MCfield", "" + it)
        }
    )
}
