package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.mapper.AddTriggerMapper
import hr.kristiankliskovic.devcontrol.utils.valuesToCalendar
import kotlinx.coroutines.flow.*

class AddTriggerViewModel(
    deviceRepository: DeviceRepository,
    private val addTriggerMapper: AddTriggerMapper,
) : ViewModel() {

    var viewState: StateFlow<AddTriggerViewState> =
        MutableStateFlow(AddTriggerViewState())

    private val devices: StateFlow<List<Device>> =
        deviceRepository.devices.stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    fun changeTriggerName(name: String) {
        viewState.value.triggerName = name;
    }

    fun changeSourceType(type: ETriggerSourceType) {
        if (viewState.value.sourceType.value != type) {
            when (viewState.value.sourceType.value) {
                ETriggerSourceType.FieldInGroup -> {
                    viewState.value.sourceAddress.value.selectedDevice = null
                    viewState.value.sourceAddress.value.selectedGroup = null
                    viewState.value.sourceAddress.value.selectedField = null

                    viewState.value.sourceSettings = null
                }
                ETriggerSourceType.FieldInComplexGroup -> {
                    viewState.value.sourceAddress.value.selectedDevice = null
                    viewState.value.sourceAddress.value.selectedGroup = null
                    viewState.value.sourceAddress.value.selectedState = null
                    viewState.value.sourceAddress.value.selectedField = null

                    viewState.value.sourceSettings = null
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

    fun setTimeTriggerType(type: ETriggerTimeType){
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
        if (viewState.value.sourceAddress.value.selectedDevice?.id == deviceId) return

        val device = devices.value.find { it.deviceId == deviceId }
        if (device == null) return

        viewState.value.sourceAddress.value.selectedDevice = DeviceEntityViewState(
            id = deviceId,
            name = device.deviceName
        )

        when (viewState.value.sourceType.value) {
            ETriggerSourceType.FieldInGroup -> {
                viewState.value.sourceAddress.value.sourceGroupsChoices =
                    addTriggerMapper.groupsToEntityViewState(device.groups)
                viewState.value.sourceAddress.value.sourceFieldChoices = listOf()
            }
            ETriggerSourceType.FieldInComplexGroup -> {
                viewState.value.sourceAddress.value.sourceGroupsChoices =
                    addTriggerMapper.complexGroupsToEntityViewState(device.complexGroups, false)
                viewState.value.sourceAddress.value.sourceComplexGroupStatesChoices = listOf()
                viewState.value.sourceAddress.value.sourceFieldChoices = listOf()
            }
            ETriggerSourceType.TimeTrigger -> {

            }
        }
    }

    fun selectSourceGroup(groupId: Int) {
        if (viewState.value.sourceAddress.value.selectedGroup?.id == groupId) return
        when (viewState.value.sourceType.value) {
            ETriggerSourceType.FieldInGroup -> {
                val device = devices.value.find {
                    it.deviceId == viewState.value.sourceAddress.value.selectedDevice?.id
                }
                if (device == null) return

                val group = device.groups.find {
                    it.groupId == groupId
                }
                if (group == null) return

                viewState.value.sourceAddress.value.sourceFieldChoices =
                    addTriggerMapper.fieldsToEntityViewState(group.fields, false)
            }
            ETriggerSourceType.FieldInComplexGroup -> {
                val device = devices.value.find {
                    it.deviceId == viewState.value.sourceAddress.value.selectedDevice?.id
                }
                if (device == null) return

                val group = device.complexGroups.find {
                    it.complexGroupId == groupId
                }
                if (group == null) return

                viewState.value.sourceAddress.value.sourceComplexGroupStatesChoices =
                    addTriggerMapper.complexGroupsStatesToEntityViewState(group.states, false)
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
                    it.deviceId == viewState.value.sourceAddress.value.selectedDevice?.id
                }
                if (device == null) return

                val group = device.complexGroups.find {
                    it.complexGroupId == viewState.value.sourceAddress.value.selectedGroup?.id
                }
                if (group == null) return

                val state = group.states.find {
                    it.stateId == stateId
                }
                if (state == null) return

                viewState.value.sourceAddress.value.sourceFieldChoices =
                    addTriggerMapper.fieldsToEntityViewState(state.fields, false)
            }
            ETriggerSourceType.TimeTrigger -> {

            }
        }
    }

    fun selectSourceField(fieldId: Int) {
        val device = devices.value.find {
            it.deviceId == viewState.value.sourceAddress.value.selectedDevice?.id
        }
        if (device == null) return

        var fields: List<BasicDeviceField> = listOf()
        when (viewState.value.sourceType.value) {
            ETriggerSourceType.FieldInGroup -> {
                val group = device.groups.find {
                    it.groupId == viewState.value.sourceAddress.value.selectedGroup?.id
                }
                if(group == null) return
                fields = group.fields
            }
            ETriggerSourceType.FieldInComplexGroup -> {
                val group = device.complexGroups.find {
                    it.complexGroupId == viewState.value.sourceAddress.value.selectedGroup?.id
                }
                if (group == null) return

                val state = group.states.find {
                    it.stateId == viewState.value.sourceAddress.value.selectedField?.id
                }
                if(state == null) return

                fields = state.fields

                //TODO find field and its data
            }
            ETriggerSourceType.TimeTrigger -> {

            }
        }

    }
}
