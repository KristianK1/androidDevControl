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
            setDevices(it)
            it
        }.stateIn(viewModelScope, SharingStarted.Lazily, mutableListOf())

    private fun setDevices(it: CopyOnWriteArrayList<Device>) {
        viewState.value.sourceAddress.value.devicesChoices =
            addTriggerMapper.devicesToEntityViewState(it).toMutableList()

        viewState.value.responseAddress.value.devicesChoices =
            addTriggerMapper.devicesToEntityViewState(it).toMutableList()
    }

    fun changeTriggerName(name: String) {
        viewState.value.triggerName = name
    }

    fun changeSourceType(type: ETriggerSourceType) {
        if (viewState.value.sourceType.value != type) {
            when (viewState.value.sourceType.value) {
                ETriggerSourceType.FieldInGroup -> {
                    viewState.value.sourceAddress.value.selectedDevice.value = null
                    viewState.value.sourceAddress.value.selectedGroup.value = null
                    viewState.value.sourceAddress.value.selectedField.value = null
                    viewState.value.sourceSettings.value = null
                    viewState.value.sourceAddress.value.devicesChoices =
                        addTriggerMapper.devicesToEntityViewState(devices.value).toMutableList()
                }
                ETriggerSourceType.FieldInComplexGroup -> {
                    viewState.value.sourceAddress.value.selectedDevice.value = null
                    viewState.value.sourceAddress.value.selectedGroup.value = null
                    viewState.value.sourceAddress.value.selectedState.value = null
                    viewState.value.sourceAddress.value.selectedField.value = null
                    viewState.value.sourceSettings.value = null
                    viewState.value.sourceAddress.value.devicesChoices =
                        addTriggerMapper.devicesToEntityViewState(devices.value).toMutableList()
                }
                ETriggerSourceType.TimeTrigger -> {
                    viewState.value.timeSourceTime.value = null
                    viewState.value.timeSourceDate.value = null
                }
            }
            viewState.value.sourceType.value = type
        }
    }

    fun changeResponseType(type: ETriggerResponseType) {
        viewModelScope.launch {
            if (viewState.value.responseType.value != type) {
                when (viewState.value.responseType.value) {
                    ETriggerResponseType.SettingValue_fieldInGroup -> {
                        viewState.value.responseAddress.value.selectedDevice.value = null
                        viewState.value.responseAddress.value.selectedGroup.value = null
                        viewState.value.responseAddress.value.selectedField.value = null
                        viewState.value.responseSettings.value = null
                        viewState.value.responseAddress.value.devicesChoices =
                            addTriggerMapper.devicesToEntityViewState(devices.value).toMutableList()
                    }
                    ETriggerResponseType.SettingValue_fieldInComplexGroup -> {
                        viewState.value.responseAddress.value.selectedDevice.value = null
                        viewState.value.responseAddress.value.selectedGroup.value = null
                        viewState.value.responseAddress.value.selectedState.value = null
                        viewState.value.responseAddress.value.selectedField.value = null
                        viewState.value.responseSettings.value = null
                        viewState.value.responseAddress.value.devicesChoices =
                            addTriggerMapper.devicesToEntityViewState(devices.value).toMutableList()
                    }
                    ETriggerResponseType.Email -> {
                        viewState.value.notificationEmailViewState.value.title.value = ""
                        viewState.value.notificationEmailViewState.value.text.value = ""
                    }
                    ETriggerResponseType.MobileNotification -> {
                        viewState.value.notificationEmailViewState.value.title.value = ""
                        viewState.value.notificationEmailViewState.value.text.value = ""
                    }
                }
                viewState.value.responseType.value = type
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
        if (viewState.value.sourceSettings.value != null && viewState.value.sourceSettings.value is NumericTriggerSourceViewState) {
            (viewState.value.sourceSettings.value as NumericTriggerSourceViewState).type.value =
                type
        }
    }

    fun setTextSourceType(type: ETextTriggerType) {
        if (viewState.value.sourceSettings.value != null && viewState.value.sourceSettings.value is TextTriggerSourceViewState) {
            (viewState.value.sourceSettings.value as TextTriggerSourceViewState).type.value = type
        }
    }

    fun setMCSourceType(type: EMCTriggerType) {
        if (viewState.value.sourceSettings.value != null && viewState.value.sourceSettings.value is MCTriggerSourceViewState) {
            (viewState.value.sourceSettings.value as MCTriggerSourceViewState).type.value = type
        }
    }

    fun setRGBSourceType(type: ERGBTriggerType_numeric) {
        if (viewState.value.sourceSettings.value != null && viewState.value.sourceSettings.value is RGBTriggerSourceViewState) {
            (viewState.value.sourceSettings.value as RGBTriggerSourceViewState).type.value = type
        }
    }

    fun setRGBSourceContext(context: ERGBTriggerType_context) {
        if (viewState.value.sourceSettings.value != null && viewState.value.sourceSettings.value is RGBTriggerSourceViewState) {
            (viewState.value.sourceSettings.value as RGBTriggerSourceViewState).contextType.value =
                context
        }
    }

    ///////////////////////////
    fun setFirstNumericSourceValue(value: Float) {
        if (viewState.value.sourceSettings.value != null && viewState.value.sourceSettings.value is NumericTriggerSourceViewState) {
            (viewState.value.sourceSettings.value as NumericTriggerSourceViewState).value.value =
                value
        }
    }

    fun setSecondNumericSourceValue(value: Float) {
        if (viewState.value.sourceSettings.value != null && viewState.value.sourceSettings.value is NumericTriggerSourceViewState) {
            (viewState.value.sourceSettings.value as NumericTriggerSourceViewState).second_value.value =
                value
        }
    }

    fun setTextSourceValue(text: String) {
        if (viewState.value.sourceSettings.value != null && viewState.value.sourceSettings.value is TextTriggerSourceViewState) {
            (viewState.value.sourceSettings.value as TextTriggerSourceViewState).value.value = text
        }
    }

    fun setMCTextSourceValue(value: Int) {
        if (viewState.value.sourceSettings.value != null && viewState.value.sourceSettings.value is MCTriggerSourceViewState) {
            (viewState.value.sourceSettings.value as MCTriggerSourceViewState).value.value = value
        }
    }

    fun setBooleanSourceType(type: Boolean) {
        if (viewState.value.sourceSettings.value != null && viewState.value.sourceSettings.value is BooleanTriggerSourceViewState) {
            (viewState.value.sourceSettings.value as BooleanTriggerSourceViewState).value.value =
                type
        }
    }

    fun setFirstRGBSourceValue(value: Int) {
        if (viewState.value.sourceSettings.value != null && viewState.value.sourceSettings.value is RGBTriggerSourceViewState) {
            (viewState.value.sourceSettings.value as RGBTriggerSourceViewState).value.value = value
        }
    }

    fun setSecondRGBSourceValue(value: Int) {
        if (viewState.value.sourceSettings.value != null && viewState.value.sourceSettings.value is RGBTriggerSourceViewState) {
            (viewState.value.sourceSettings.value as RGBTriggerSourceViewState).second_value.value =
                value
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
                viewState.value.sourceAddress.value.groupsChoices =
                    addTriggerMapper.groupsToEntityViewState(device.groups).toMutableList()
                Log.i("RFG", Gson().toJson(viewState.value.sourceAddress.value.groupsChoices))
                viewState.value.sourceAddress.value.fieldChoices = mutableListOf()
            }
            ETriggerSourceType.FieldInComplexGroup -> {
                viewState.value.sourceAddress.value.groupsChoices =
                    addTriggerMapper.complexGroupsToEntityViewState(device.complexGroups, false)
                        .toMutableList()
                viewState.value.sourceAddress.value.complexGroupStatesChoices =
                    mutableListOf()
                viewState.value.sourceAddress.value.fieldChoices = mutableListOf()
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
                Log.i("RFG", "jedan")
                if (device == null) return

                val group = device.groups.find {
                    it.groupId == groupId
                }
                Log.i("RFG", "dva")
                if (group == null) return
                Log.i("RFG", "tri")

                viewState.value.sourceAddress.value.selectedGroup.value = DeviceEntityViewState(
                    id = group.groupId,
                    name = group.groupName,
                )
                Log.i("RFG", "cetiri")

                viewState.value.sourceAddress.value.fieldChoices =
                    addTriggerMapper.fieldsToEntityViewState(group.fields, false).toMutableList()
                Log.i("RFG", "pet")

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

                viewState.value.sourceAddress.value.selectedGroup.value = DeviceEntityViewState(
                    id = group.complexGroupId,
                    name = group.groupName,
                )

                viewState.value.sourceAddress.value.complexGroupStatesChoices =
                    addTriggerMapper.complexGroupsStatesToEntityViewState(group.states)
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

                viewState.value.sourceAddress.value.selectedState.value = DeviceEntityViewState(
                    id = state.stateId,
                    name = state.stateName,
                )

                viewState.value.sourceAddress.value.fieldChoices =
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
                    it.stateId == viewState.value.sourceAddress.value.selectedState.value?.id
                }
                if (state == null) return

                fields = state.fields

            }
            ETriggerSourceType.TimeTrigger -> {

            }
        }
        if (fields.isEmpty()) return

        var triggerSettings: TriggerSourceSettingsViewState? = null
        for (field in fields) {
            when (field) {
                is NumericDeviceField -> {
                    if (field.fieldId != fieldId) continue
                    viewState.value.sourceAddress.value.selectedField.value = DeviceEntityViewState(
                        id = field.fieldId,
                        name = field.fieldName,
                    )
                    triggerSettings = NumericTriggerSourceViewState(
                        minimum = field.minValue,
                        maximum = field.maxValue,
                        step = field.valueStep,
                        value = mutableStateOf(field.minValue),
                        second_value = mutableStateOf(null),
                        type = mutableStateOf(ENumericTriggerType.Bigger),
                        prefix = field.prefix,
                        sufix = field.sufix,
                    )
                }
                is TextDeviceField -> {
                    if (field.fieldId != fieldId) continue
                    viewState.value.sourceAddress.value.selectedField.value = DeviceEntityViewState(
                        id = field.fieldId,
                        name = field.fieldName,
                    )
                    triggerSettings = TextTriggerSourceViewState(
                        value = mutableStateOf(""),
                        type = mutableStateOf(ETextTriggerType.Contains),
                    )
                }
                is ButtonDeviceField -> {
                    if (field.fieldId != fieldId) continue
                    viewState.value.sourceAddress.value.selectedField.value = DeviceEntityViewState(
                        id = field.fieldId,
                        name = field.fieldName,
                    )
                    triggerSettings = BooleanTriggerSourceViewState(
                        value = mutableStateOf(true),
                    )
                }
                is MultipleChoiceDeviceField -> {
                    if (field.fieldId != fieldId) continue
                    viewState.value.sourceAddress.value.selectedField.value = DeviceEntityViewState(
                        id = field.fieldId,
                        name = field.fieldName,
                    )
                    triggerSettings = MCTriggerSourceViewState(
                        value = mutableStateOf(0),
                        values = field.choices,
                        type = mutableStateOf(EMCTriggerType.IsEqualTo),
                    )
                }
                is RGBDeviceField -> {
                    if (field.fieldId != fieldId) continue
                    viewState.value.sourceAddress.value.selectedField.value = DeviceEntityViewState(
                        id = field.fieldId,
                        name = field.fieldName,
                    )
                    triggerSettings = RGBTriggerSourceViewState(
                        value = mutableStateOf(null),
                        second_value = mutableStateOf(null),
                        type = mutableStateOf(ERGBTriggerType_numeric.Bigger),
                        contextType = mutableStateOf(ERGBTriggerType_context.R),
                    )
                }
            }
        }
        if (triggerSettings != null) {
            viewState.value.sourceSettings.value = triggerSettings
        }
    }


    fun selectResponseDevice(deviceId: Int) {
        if (viewState.value.responseAddress.value.selectedDevice.value?.id == deviceId) return

        val device = devices.value.find { it.deviceId == deviceId }
        if (device == null) return

        viewState.value.responseAddress.value.selectedDevice.value = DeviceEntityViewState(
            id = deviceId,
            name = device.deviceName
        )

        when (viewState.value.responseType.value) {
            ETriggerResponseType.SettingValue_fieldInGroup -> {
                viewState.value.responseAddress.value.groupsChoices =
                    addTriggerMapper.groupsToEntityViewState(device.groups).toMutableList()
                Log.i("RFG", Gson().toJson(viewState.value.sourceAddress.value.groupsChoices))
                viewState.value.responseAddress.value.fieldChoices = mutableListOf()
            }
            ETriggerResponseType.SettingValue_fieldInComplexGroup -> {
                viewState.value.responseAddress.value.groupsChoices =
                    addTriggerMapper.complexGroupsToEntityViewState(device.complexGroups, true)
                        .toMutableList()
                viewState.value.responseAddress.value.complexGroupStatesChoices =
                    mutableListOf()
                viewState.value.responseAddress.value.fieldChoices = mutableListOf()
            }
            ETriggerResponseType.Email -> {

            }
            ETriggerResponseType.MobileNotification -> {

            }
        }
    }

    fun selectResponseGroup(groupId: Int) {
        if (viewState.value.responseAddress.value.selectedGroup.value?.id == groupId) return
        when (viewState.value.responseType.value) {
            ETriggerResponseType.SettingValue_fieldInGroup -> {
                val device = devices.value.find {
                    it.deviceId == viewState.value.responseAddress.value.selectedDevice.value?.id
                }
                if (device == null) return

                val group = device.groups.find {
                    it.groupId == groupId
                }
                if (group == null) return

                viewState.value.responseAddress.value.selectedGroup.value = DeviceEntityViewState(
                    id = group.groupId,
                    name = group.groupName,
                )

                viewState.value.responseAddress.value.fieldChoices =
                    addTriggerMapper.fieldsToEntityViewState(group.fields, false).toMutableList()
            }
            ETriggerResponseType.SettingValue_fieldInComplexGroup -> {
                val device = devices.value.find {
                    it.deviceId == viewState.value.responseAddress.value.selectedDevice.value?.id
                }
                if (device == null) return

                val group = device.complexGroups.find {
                    it.complexGroupId == groupId
                }
                if (group == null) return

                viewState.value.responseAddress.value.selectedGroup.value = DeviceEntityViewState(
                    id = group.complexGroupId,
                    name = group.groupName,
                )

                viewState.value.responseAddress.value.complexGroupStatesChoices =
                    addTriggerMapper.complexGroupsStatesToEntityViewState(group.states)
                        .toMutableList()
            }
            ETriggerResponseType.Email -> {

            }
            ETriggerResponseType.MobileNotification -> {

            }
        }
    }

    fun selectResponseState(stateId: Int) {
        when (viewState.value.responseType.value) {
            ETriggerResponseType.SettingValue_fieldInGroup -> {

            }
            ETriggerResponseType.SettingValue_fieldInComplexGroup -> {
                val device = devices.value.find {
                    it.deviceId == viewState.value.responseAddress.value.selectedDevice.value?.id
                }
                if (device == null) return

                val group = device.complexGroups.find {
                    it.complexGroupId == viewState.value.responseAddress.value.selectedGroup.value?.id
                }
                if (group == null) return

                val state = group.states.find {
                    it.stateId == stateId
                }
                if (state == null) return

                viewState.value.responseAddress.value.selectedState.value = DeviceEntityViewState(
                    id = state.stateId,
                    name = state.stateName,
                )

                viewState.value.responseAddress.value.fieldChoices =
                    addTriggerMapper.fieldsToEntityViewState(state.fields, true).toMutableList()
            }
            ETriggerResponseType.Email -> {

            }
            ETriggerResponseType.MobileNotification -> {

            }
        }
    }

    fun selectResponseField(fieldId: Int) {
        val device = devices.value.find {
            it.deviceId == viewState.value.responseAddress.value.selectedDevice.value?.id
        }
        if (device == null) return

        var fields: List<BasicDeviceField> = listOf()
        when (viewState.value.responseType.value) {
            ETriggerResponseType.SettingValue_fieldInGroup -> {
                val group = device.groups.find {
                    it.groupId == viewState.value.responseAddress.value.selectedGroup.value?.id
                }
                if (group == null) return
                fields = group.fields
            }
            ETriggerResponseType.SettingValue_fieldInComplexGroup -> {
                val group = device.complexGroups.find {
                    it.complexGroupId == viewState.value.responseAddress.value.selectedGroup.value?.id
                }
                if (group == null) return

                val state = group.states.find {
                    it.stateId == viewState.value.responseAddress.value.selectedState.value?.id
                }
                if (state == null) return

                fields = state.fields

            }
            ETriggerResponseType.Email -> {

            }
            ETriggerResponseType.MobileNotification -> {

            }
        }
        if (fields.isEmpty()) return

        var triggerSettings: TriggerResponseSettingsViewState? = null
        for (field in fields) {
            when (field) {
                is NumericDeviceField -> {
                    if (field.fieldId != fieldId) continue
                    viewState.value.responseAddress.value.selectedField.value =
                        DeviceEntityViewState(
                            id = field.fieldId,
                            name = field.fieldName,
                        )
                    triggerSettings = NumericTriggerResponseViewState(
                        minimum = field.minValue,
                        maximum = field.maxValue,
                        step = field.valueStep,
                        value = mutableStateOf(field.minValue),
                        prefix = field.prefix,
                        sufix = field.sufix,
                    )
                }
                is TextDeviceField -> {
                    if (field.fieldId != fieldId) continue
                    viewState.value.responseAddress.value.selectedField.value =
                        DeviceEntityViewState(
                            id = field.fieldId,
                            name = field.fieldName,
                        )
                    triggerSettings = TextTriggerResponseViewState(
                        value = mutableStateOf(""),
                    )
                }
                is ButtonDeviceField -> {
                    if (field.fieldId != fieldId) continue
                    viewState.value.responseAddress.value.selectedField.value =
                        DeviceEntityViewState(
                            id = field.fieldId,
                            name = field.fieldName,
                        )
                    triggerSettings = BooleanTriggerResponseViewState(
                        value = mutableStateOf(true),
                    )
                }
                is MultipleChoiceDeviceField -> {
                    if (field.fieldId != fieldId) continue
                    viewState.value.responseAddress.value.selectedField.value =
                        DeviceEntityViewState(
                            id = field.fieldId,
                            name = field.fieldName,
                        )
                    triggerSettings = MCTriggerResponseViewState(
                        value = mutableStateOf(0),
                        values = field.choices,
                    )
                }
                is RGBDeviceField -> {
                    if (field.fieldId != fieldId) continue
                    viewState.value.responseAddress.value.selectedField.value =
                        DeviceEntityViewState(
                            id = field.fieldId,
                            name = field.fieldName,
                        )
                    triggerSettings = RGBTriggerResponseViewState(
                        value = mutableStateOf(null),
                        contextType = mutableStateOf(ERGBTriggerType_context.R),
                    )
                }
            }
        }
        if (triggerSettings != null) {
            viewState.value.responseSettings.value = triggerSettings
        }
    }

    fun setRGBResponseContext(context: ERGBTriggerType_context) {
        if (viewState.value.responseSettings.value != null && viewState.value.responseSettings.value is RGBTriggerResponseViewState) {
            (viewState.value.responseSettings.value as RGBTriggerResponseViewState).contextType.value =
                context
        }
    }

    fun setNumericResponseValue(value: Float) {
        if (viewState.value.responseSettings.value != null && viewState.value.responseSettings.value is NumericTriggerResponseViewState) {
            (viewState.value.responseSettings.value as NumericTriggerResponseViewState).value.value =
                value
        }
    }

    fun setTextResponseValue(text: String) {
        if (viewState.value.responseSettings.value != null && viewState.value.responseSettings.value is TextTriggerResponseViewState) {
            (viewState.value.responseSettings.value as TextTriggerResponseViewState).value.value =
                text
        }
    }

    fun setMCResponseValue(value: Int) {
        if (viewState.value.responseSettings.value != null && viewState.value.responseSettings.value is MCTriggerResponseViewState) {
            (viewState.value.responseSettings.value as MCTriggerResponseViewState).value.value =
                value
        }
    }

    fun setBooleanResponseType(type: Boolean) {
        if (viewState.value.responseSettings.value != null && viewState.value.responseSettings.value is BooleanTriggerResponseViewState) {
            (viewState.value.responseSettings.value as BooleanTriggerResponseViewState).value.value =
                type
        }
    }

    fun setRGBResponseValue(value: Int) {
        if (viewState.value.responseSettings.value != null && viewState.value.responseSettings.value is RGBTriggerResponseViewState) {
            (viewState.value.responseSettings.value as RGBTriggerResponseViewState).value.value =
                value
        }
    }

    fun changeResponseTitle(title: String) {
        viewState.value.notificationEmailViewState.value.title.value = title
    }

    fun changeResponseText(text: String) {
        viewState.value.notificationEmailViewState.value.text.value = text
    }

    fun saveTrigger() {

    }
}
