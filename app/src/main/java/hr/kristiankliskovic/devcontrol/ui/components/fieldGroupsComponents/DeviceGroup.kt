package hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.mock.getMockDeviceGroupViewState
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.common.BasicField
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.specificFields.BasicFieldComponentViewState
import hr.kristiankliskovic.devcontrol.ui.theme.Shapes

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
        for (fieldViewState in item.fields) {
            BasicField(
                item = fieldViewState,
                onChange = { fieldId, value ->
                    onChange(item.groupId, fieldId, value)
                },
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
