package hr.kristiankliskovic.devcontrol.ui.components.otherComponents

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlineTextWrapper(
    initValue: String = "",
    hidden: Boolean = false,
    label: String,
    placeholder: String = "",
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var str by remember { mutableStateOf(TextFieldValue(initValue)) }
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
        colors = outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.primary,
            disabledLabelColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = MaterialTheme.colorScheme.outline,
            placeholderColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.outline,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
        )
    )
}
