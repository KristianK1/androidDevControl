package hr.kristiankliskovic.devcontrol.ui.components.otherComponents

import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import hr.kristiankliskovic.devcontrol.R

@Composable
fun OutlineTextWrapper(
    hidden: Boolean = false,
    label: String,
    placeholder: String = "",
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var str by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = str,
        onValueChange = { newText: TextFieldValue ->
            str = newText
            onChange(newText.text)
        },
        visualTransformation = if (hidden) PasswordVisualTransformation() else VisualTransformation.None,
        placeholder = {
            Text(
                text = placeholder,
            )
        },
        label = {
            Text(
                text = label
            )
        },
        singleLine = true,
        modifier = modifier,
    )
}
