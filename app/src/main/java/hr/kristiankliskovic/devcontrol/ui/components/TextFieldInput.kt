package hr.kristiankliskovic.devcontrol.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes
import kotlin.math.log

data class TextFieldInputViewState(
    val fieldId: Int,
    val name: String,
    val currentValue: String,
)

@Composable
fun TextFieldInput(
    item: TextFieldInputViewState,
    emitValue: (String) -> Unit,
    modifier: Modifier = Modifier,
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
                text = item.currentValue,
                fontSize = 38.sp
            )
            TextInputDialog(emitValue = emitValue)

        }
    }
}

@Composable
fun TextInputDialog(
    emitValue: (String) -> Unit,
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
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var text by remember { mutableStateOf(TextFieldValue("")) }
                    TextField(
                        value = text,
                        onValueChange = { newText ->
                            text = newText
                        }
                    )
                    Text(
                        text = stringResource(id = R.string.textFieldInput_confirm_text_buttonText),
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(15.dp)
                            .clickable {
                                emitValue(text.text)
                                dialogOpen = false
                            }
                    )
                }
            },
            title = {
                Text(text = stringResource(id = R.string.textFieldInput_enter_text_title))
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
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxHeight()
            .padding(2.dp)
            .clip(Shapes.small)
            .background(Color.LightGray)
            .clickable {
                dialogOpen = true
            }
    ) {
        Text(
            text = stringResource(id = R.string.multipleChoiceFieldInput_open_selector),
            modifier = Modifier
                .padding(12.dp)
        )
    }

}


@Preview
@Composable
fun PreviewTextFieldInput() {
    val state = TextFieldInputViewState(
        fieldId = 0,
        name = "Text field input 7",
        currentValue = "Hello1",
    )

    TextFieldInput(
        item = state,
        emitValue = {
            Log.i("TFdebug", it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
    )
}
