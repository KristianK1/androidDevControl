package hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.kristiankliskovic.devcontrol.mock.getMockDeviceGroupViewState
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.*

data class DeviceGroupViewState(
    val groupId: Int,
    val groupName: String,
    val fields: List<BasicFieldComponentViewState>,
)

@Composable
fun DeviceGroup(
    item: DeviceGroupViewState,
    onChange: (Int, Int, Any) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier
            .border(5.dp, Color.Gray, RoundedCornerShape(5.dp))
    ) {
        GroupTitle(
            name = item.groupName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        for (fieldViewState in item.fields) {
            BasicField(
                item = fieldViewState,
                onChange = { fieldId, value ->
                    onChange(item.groupId, fieldId, value)
                },
                modifier = Modifier.padding(10.dp, 3.dp)
            )
        }

    }

}


@Composable
fun BasicField(
    item: BasicFieldComponentViewState,
    onChange: (Int, Any) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (item) {
        is ButtonFieldInputViewState -> {
            ButtonFieldInput(
                item = item,
                emitValue = { value ->
                    onChange(item.fieldId, value)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        is ButtonFieldOutputViewState -> {
            ButtonFieldOutput(
                item = item,
                modifier = modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        is TextFieldInputViewState -> {
            TextFieldInput(
                item = item,
                emitValue = { value ->
                    onChange(item.fieldId, value)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        is TextFieldOutputViewState -> {
            TextFieldOutput(
                item = item,
                modifier = modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        is MultipleChoiceFieldInputViewState -> {
            MultipleChoiceFieldInput(
                item = item,
                emitValue = { value ->
                    onChange(item.fieldId, value)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        is RGBFieldInputViewState -> {
            RGBFieldInput(
                item = item,
                emitValue = { value ->
                    onChange(item.fieldId, value)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
        is NumericFieldInputViewState -> {
            NumericFieldInput(
                item = item,
                emitValue = { value ->
                    onChange(item.fieldId, value)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
    }
}


@Preview
@Composable
fun PreviewDeviceGroup() {
    LazyColumn {
        items(1) {
            DeviceGroup(
                item = getMockDeviceGroupViewState(),
                onChange = { groupId, fieldId, value ->
                }
            )
        }
    }
}
