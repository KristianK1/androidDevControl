package hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.mock.getMockDeviceComplexGroupViewState
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.BasicFieldComponentViewState
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.common.BasicField
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes

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
    val readOnly: Boolean,
)

@Composable
fun DeviceComplexGroup(
    item: DeviceComplexGroupViewState,
    changeComplexGroupState: (Int, Int) -> Unit,
    onChange: (Int, Int, Int, Any) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.deviceGroup_fields_spaced_by)),
        modifier = modifier
            .border(
                dimensionResource(id = R.dimen.deviceGroup_border_thickness),
                colorResource(id = R.color.deviceGroup_border),
                Shapes.small
            )
            .padding(dimensionResource(id = R.dimen.deviceGroup_padding))
    ) {
        GroupTitle(
            name = item.groupName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.deviceGroup_title_padding))
        )
        ComplexGroupStateChooser(
            items = item.states,
            readOnly = item.readOnly,
            currentState = item.currentState,
            changeState = {
                changeComplexGroupState(item.complexGroupId, it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.complexGroup_state_chooser_height))
        )
        for (fieldViewState in item.states[item.currentState].fields) {
            BasicField(
                item = fieldViewState,
                onChange = { fieldId, value ->
                    onChange(item.complexGroupId, item.currentState, fieldId, value)
                }
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
