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
import hr.kristiankliskovic.devcontrol.mock.getMockDeviceComplexGroupViewState
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.BasicFieldComponentViewState
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.common.BasicField

data class DeviceComplexGroupStateViewState(
    val stateId: Int,
    val stateName: String,
    val fields: List<BasicFieldComponentViewState>,
)

data class DeviceComplexGroupViewState(
    val complexGroupId: Int,
    val groupName: String,
    val states: List<DeviceComplexGroupStateViewState>,
    val currentState: Int,
)

@Composable
fun DeviceComplexGroup(
    item: DeviceComplexGroupViewState,
    changeComplexGroupState: (Int, Int) -> Unit,
    onChange: (Int, Int, Int, Any) -> Unit,
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
        ComplexGroupStateChooser(
            items = item.states,
            currentState = item.currentState,
            changeState = {
                changeComplexGroupState(item.complexGroupId, it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        )
        for (fieldViewState in item.states[item.currentState].fields) {
            BasicField(
                item = fieldViewState,
                onChange = { fieldId, value ->
                    onChange(item.complexGroupId, item.currentState, fieldId, value)
                },
                modifier = Modifier.padding(10.dp, 3.dp)
            )
        }
    }
}


@Preview
@Composable
fun PreviewComplexGroup() {
    LazyColumn {
        items(1) {
            DeviceComplexGroup(
                item = getMockDeviceComplexGroupViewState(),
                changeComplexGroupState = { _, _ ->

                },
                onChange = { _, _, _, _ ->

                }
            )
        }
    }


}
