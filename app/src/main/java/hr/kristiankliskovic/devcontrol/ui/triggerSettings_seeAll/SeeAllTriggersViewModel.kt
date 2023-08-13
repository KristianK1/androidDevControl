package hr.kristiankliskovic.devcontrol.ui.triggerSettings_seeAll

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_seeAll.mapper.SeeAllTriggersMapper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SeeAllTriggersViewModel(
    val deviceRepository: DeviceRepository,
    mapper: SeeAllTriggersMapper,
) : ViewModel() {
    init {
        Log.i("ALLT", "init ViewModel")
        deviceRepository.clearAllTriggersResponse()
        Log.i("ALLT", "init ViewModel")
        viewModelScope.launch {
            deviceRepository.seeAllTriggers()
        }
    }

    val devices = deviceRepository.devices.stateIn(viewModelScope, SharingStarted.Lazily, listOf())
    val viewState: StateFlow<SeeAllTriggersViewState?> =
        deviceRepository.allTriggersForUserResponse.map {
            Log.i("ALLT", "startMAP")
            if (it == null) return@map null
            Log.i("ALLT", "MAP stop1")
            val x = mapper.triggersToSeeAllTriggersViewState(
                triggers = it.triggers,
                devices = devices.value,
            )
            Log.i("ALLT", "MAP stop2")
            x
        }.stateIn(viewModelScope, SharingStarted.Lazily, null)


    fun deleteTrigger(id: Int) {
        viewModelScope.launch {
            deviceRepository.deleteTrigger(id)
            deviceRepository.seeAllTriggers()
        }
    }
}
