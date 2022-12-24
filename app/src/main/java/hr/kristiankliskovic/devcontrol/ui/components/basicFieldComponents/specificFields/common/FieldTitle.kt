package hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.R

@Composable
fun FieldTitle(
    title: String
){
    Text(
        text = title,
        fontSize = 30.sp,
        color = colorResource(id = R.color.fieldComponent_title)
    )
}
