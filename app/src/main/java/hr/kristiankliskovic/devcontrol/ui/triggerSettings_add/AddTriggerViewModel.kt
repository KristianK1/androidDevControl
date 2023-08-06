package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.mapper.AddTriggerMapper
import hr.kristiankliskovic.devcontrol.utils.valuesToCalendar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.concurrent.CopyOnWriteArrayList

class AddTriggerViewModel(
    deviceRepository: DeviceRepository,
    val addTriggerMapper: AddTriggerMapper,
) : ViewModel() {

    var viewState: StateFlow<AddTriggerViewState> =
        MutableStateFlow(AddTriggerViewState())

    val devices: StateFlow<List<Device>> =
        deviceRepository.devices.map {
            Log.i("deviceMapX", "HELLO")
            Log.i("deviceMapX", Gson().toJson(it.toList()))
            setDevices(it)
            it
        }.stateIn(viewModelScope, SharingStarted.Lazily, mutableListOf())

    private suspend fun setDevices(it: CopyOnWriteArrayList<Device>) {
        viewModelScope.launch {
            viewState.value.sourceAddress.value.sourceDevicesChoices = addTriggerMapper.devicesToEntityViewState(it).toMutableList()
        }
    }

    fun changeTriggerName(name: String) {
        viewState.value.triggerName = name;
    }

    fun changeSourceType(type: ETriggerSourceType) {
        viewModelScope.launch {
            Log.i("deviceMapX", "SIZEX"+ devices.value.size)

            if (viewState.value.sourceType.value != type) {
                when (viewState.value.sourceType.value) {
                    ETriggerSourceType.FieldInGroup -> {
                        viewState.value.sourceAddress.value.selectedDevice.value = null
                        viewState.value.sourceAddress.value.selectedGroup.value = null
                        viewState.value.sourceAddress.value.selectedField.value = null
                        viewState.value.sourceSettings = null
                        viewState.value.sourceAddress.value.sourceDevicesChoices = addTriggerMapper.devicesToEntityViewState(devices.value).toMutableList()
                    }
                    ETriggerSourceType.FieldInComplexGroup -> {
                        viewState.value.sourceAddress.value.selectedDevice.value = null
                        viewState.value.sourceAddress.value.selectedGroup.value = null
                        viewState.value.sourceAddress.value.selectedState.value = null
                        viewState.value.sourceAddress.value.selectedField.value = null
                        viewState.value.sourceSettings = null
                        viewState.value.sourceAddress.value.sourceDevicesChoices = addTriggerMapper.devicesToEntityViewState(devices.value).toMutableList()
                    }
                    ETriggerSourceType.TimeTrigger -> {
                        viewState.value.timeSourceTime.value = null
                        viewState.value.timeSourceDate.value = null
                    }
                }
                Log.i("devCAL", "CHANGED TYPE")
                viewState.value.sourceType.value = type
            }
        }
    }

    fun setTimeTriggerType(type: ETriggerTimeType) {
        viewState.value.timeTriggerType.value = type
    }

    fun setTimeTriggerTime(
        hour: Int,
        minute: Int,
    ) {
        viewState.value.timeSourceTime.value = hour * 60 + minute / 5 * 5
    }

    fun setDateTriggerDate(
        year: Int,
        month: Int,
        day: Int,
    ) {
        viewState.value.timeSourceDate.value = valuesToCalendar(
            year = year,
            month = month,
            day = day,
        )
    }

    fun setNumericSourceType(type: ENumericTriggerType) {
        if (viewState.value.sourceSettings != null && viewState.value.sourceSettings is NumericTriggerViewState) {
            (viewState.value.sourceSettings as NumericTriggerViewState).type = type
        }
    }

    fun setMCSourceType(type: EMCTriggerType) {
        if (viewState.value.sourceSettings != null && viewState.value.sourceSettings is MCTriggerViewState) {
            (viewState.value.sourceSettings as MCTriggerViewState).type = type
        }
    }

    fun setRGBSourceType(type: ERGBTriggerType_numeric) {
        if (viewState.value.sourceSettings != null && viewState.value.sourceSettings is RGBTriggerViewState) {
            (viewState.value.sourceSettings as RGBTriggerViewState).type = type
        }
    }

    fun setRGBSourceContext(context: ERGBTriggerType_context) {
        if (viewState.value.sourceSettings != null && viewState.value.sourceSettings is RGBTriggerViewState) {
            (viewState.value.sourceSettings as RGBTriggerViewState).contextType = context
        }
    }

    fun setTextSourceType(type: ETextTriggerType) {
        if (viewState.value.sourceSettings != null && viewState.value.sourceSettings is TextTriggerViewState) {
            (viewState.value.sourceSettings as TextTriggerViewState).type = type
        }
    }

    ///////////////////////////
    fun setFirstNumericSourceValue(value: Float) {
        if (viewState.value.sourceSettings != null && viewState.value.sourceSettings is NumericTriggerViewState) {
            (viewState.value.sourceSettings as NumericTriggerViewState).value = value
        }
    }

    fun setSecondNumericSourceValue(value: Float) {
        if (viewState.value.sourceSettings != null && viewState.value.sourceSettings is NumericTriggerViewState) {
            (viewState.value.sourceSettings as NumericTriggerViewState).second_value =
                value
        }
    }

    fun setTextSourceValue(text: String) {
        if (viewState.value.sourceSettings != null && viewState.value.sourceSettings is TextTriggerViewState) {
            (viewState.value.sourceSettings as TextTriggerViewState).value = text
        }
    }

    fun setMCTextSourceValue(value: Int) {
        if (viewState.value.sourceSettings != null && viewState.value.sourceSettings is MCTriggerViewState) {
            (viewState.value.sourceSettings as MCTriggerViewState).value = value
        }
    }

    fun setBooleanSourceType(type: Boolean) {
        if (viewState.value.sourceSettings != null && viewState.value.sourceSettings is BooleanTriggerViewState) {
            (viewState.value.sourceSettings as BooleanTriggerViewState).value = type
        }
    }

    fun setFirstRGBSourceValue(value: Int) {
        if (viewState.value.sourceSettings != null && viewState.value.sourceSettings is RGBTriggerViewState) {
            (viewState.value.sourceSettings as RGBTriggerViewState).value = value
        }
    }

    fun setSecondRGBSourceValue(value: Int) {
        if (viewState.value.sourceSettings != null && viewState.value.sourceSettings is RGBTriggerViewState) {
            (viewState.value.sourceSettings as RGBTriggerViewState).second_value = value
        }
    }

    fun selectSourceDevice(deviceId: Int) {
        if (viewState.value.sourceAddress.value.selectedDevice.value?.id == deviceId) return

        val device = devices.value.find { it.deviceId == deviceId }
        if (device == null) return

        viewState.value.sourceAddress.value.selectedDevice.value = DeviceEntityViewState(
            id = deviceId,
            name = device.deviceName
        )

        when (viewState.value.sourceType.value) {
            ETriggerSourceType.FieldInGroup -> {
                viewState.value.sourceAddress.value.sourceGroupsChoices =
                    addTriggerMapper.groupsToEntityViewState(device.groups).toMutableList()
                viewState.value.sourceAddress.value.sourceFieldChoices = mutableListOf()
            }
            ETriggerSourceType.FieldInComplexGroup -> {
                viewState.value.sourceAddress.value.sourceGroupsChoices =
                    addTriggerMapper.complexGroupsToEntityViewState(device.complexGroups, false)
                        .toMutableList()
                viewState.value.sourceAddress.value.sourceComplexGroupStatesChoices =
                    mutableListOf()
                viewState.value.sourceAddress.value.sourceFieldChoices = mutableListOf()
            }
            ETriggerSourceType.TimeTrigger -> {

            }
        }
    }

    fun selectSourceGroup(groupId: Int) {
        if (viewState.value.sourceAddress.value.selectedGroup.value?.id == groupId) return
        when (viewState.value.sourceType.value) {
            ETriggerSourceType.FieldInGroup -> {
                val device = devices.value.find {
                    it.deviceId == viewState.value.sourceAddress.value.selectedDevice.value?.id
                }
                if (device == null) return

                val group = device.groups.find {
                    it.groupId == groupId
                }
                if (group == null) return

                viewState.value.sourceAddress.value.sourceFieldChoices =
                    addTriggerMapper.fieldsToEntityViewState(group.fields, false).toMutableList()
            }
            ETriggerSourceType.FieldInComplexGroup -> {
                val device = devices.value.find {
                    it.deviceId == viewState.value.sourceAddress.value.selectedDevice.value?.id
                }
                if (device == null) return

                val group = device.complexGroups.find {
                    it.complexGroupId == groupId
                }
                if (group == null) return

                viewState.value.sourceAddress.value.sourceComplexGroupStatesChoices =
                    addTriggerMapper.complexGroupsStatesToEntityViewState(group.states, false)
                        .toMutableList()
            }
            ETriggerSourceType.TimeTrigger -> {

            }
        }
    }

    fun selectSourceState(stateId: Int) {
        when (viewState.value.sourceType.value) {
            ETriggerSourceType.FieldInGroup -> {

            }
            ETriggerSourceType.FieldInComplexGroup -> {
                val device = devices.value.find {
                    it.deviceId == viewState.value.sourceAddress.value.selectedDevice.value?.id
                }
                if (device == null) return

                val group = device.complexGroups.find {
                    it.complexGroupId == viewState.value.sourceAddress.value.selectedGroup.value?.id
                }
                if (group == null) return

                val state = group.states.find {
                    it.stateId == stateId
                }
                if (state == null) return

                viewState.value.sourceAddress.value.sourceFieldChoices =
                    addTriggerMapper.fieldsToEntityViewState(state.fields, false).toMutableList()
            }
            ETriggerSourceType.TimeTrigger -> {

            }
        }
    }

    fun selectSourceField(fieldId: Int) {
        val device = devices.value.find {
            it.deviceId == viewState.value.sourceAddress.value.selectedDevice.value?.id
        }
        if (device == null) return

        var fields: List<BasicDeviceField> = listOf()
        when (viewState.value.sourceType.value) {
            ETriggerSourceType.FieldInGroup -> {
                val group = device.groups.find {
                    it.groupId == viewState.value.sourceAddress.value.selectedGroup.value?.id
                }
                if (group == null) return
                fields = group.fields
            }
            ETriggerSourceType.FieldInComplexGroup -> {
                val group = device.complexGroups.find {
                    it.complexGroupId == viewState.value.sourceAddress.value.selectedGroup.value?.id
                }
                if (group == null) return

                val state = group.states.find {
                    it.stateId == viewState.value.sourceAddress.value.selectedField.value?.id
                }
                if (state == null) return

                fields = state.fields

                //TODO find field and its data
            }
            ETriggerSourceType.TimeTrigger -> {

            }
        }

    }
}
