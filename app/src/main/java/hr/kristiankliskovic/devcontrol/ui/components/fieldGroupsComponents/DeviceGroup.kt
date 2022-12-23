package hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hr.kristiankliskovic.devcontrol.mock.getMockDeviceGroupViewState
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.common.BasicField
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.BasicFieldComponentViewState

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
