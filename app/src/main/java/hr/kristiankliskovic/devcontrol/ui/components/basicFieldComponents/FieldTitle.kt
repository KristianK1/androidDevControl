package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun FieldTitle(
    title: String
){
    Text(
        text = title,
        fontSize = 30.sp,
        color = Color.Gray
    )
}
