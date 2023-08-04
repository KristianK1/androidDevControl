package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.model.ITrigger
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.mapper.AddTriggerMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AddTriggerViewModel(
    private val deviceRepository: DeviceRepository,
    private val addTriggerMapper: AddTriggerMapper,
) : ViewModel() {
    val devicesViewState: StateFlow<TriggerSourceDevicesViewState> =
        deviceRepository.devices.map { devices ->
            addTriggerMapper.toAddTriggerViewState(devices)
        }.stateIn(viewModelScope, SharingStarted.Lazily, TriggerSourceDevicesViewState.empty())

    fun addTrigger(triggerData: ITrigger){
        viewModelScope.launch {

        }
    }
}
