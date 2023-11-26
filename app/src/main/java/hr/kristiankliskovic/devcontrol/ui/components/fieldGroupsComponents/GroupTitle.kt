package hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun GroupTitle(
    name: String,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ){
        Text(
            text = name,
            fontSize = 35.sp,
            color = MaterialTheme.colorScheme.inverseSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
