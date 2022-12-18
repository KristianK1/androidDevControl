package hr.kristiankliskovic.devcontrol.ui.components.fieldGroupsComponents

import androidx.compose.runtime.Composable
import hr.kristiankliskovic.devcontrol.ui.components.basicFieldComponents.BasicFieldComponentViewState

data class DeviceComplexGroupStateViewState(
    val stateId: Int,
    val stateName: String,
    val fields: BasicFieldComponentViewState,
)

data class DeviceComplexGroupViewState(
    val complexGroupId: Int,
    val groupName: String,
    val states: List<DeviceComplexGroupStateViewState>,
    val currentState: Int,
)

@Composable
fun ComplexGroup(
    item: DeviceComplexGroupViewState,
    changeComplexGroupState: (Int) -> Unit,
    emitValue: (Int, Int, Any) -> Unit,
) {

}
