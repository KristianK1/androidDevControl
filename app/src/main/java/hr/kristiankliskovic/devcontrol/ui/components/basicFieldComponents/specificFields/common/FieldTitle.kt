package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.common

import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R

@Composable
fun FieldTitle(
    title: String,
    modifier: Modifier = Modifier,
){
    Text(
        text = title,
        fontSize = 30.sp,
        color = MaterialTheme.colorScheme.inverseSurface,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
    )
}
