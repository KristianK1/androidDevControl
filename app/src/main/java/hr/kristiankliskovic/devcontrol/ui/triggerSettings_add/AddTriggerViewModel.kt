package hr.kristiankliskovic.devcontrol.ui.triggerSettings_add

import android.icu.lang.UCharacter.NumericType
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_add.mapper.AddTriggerMapper
import hr.kristiankliskovic.devcontrol.utils.valuesToCalendar
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import java.util.Calendar

class AddTriggerViewModel(
    private val deviceRepository: DeviceRepository,
    private val addTriggerMapper: AddTriggerMapper,
) : ViewModel() {
    val devicesViewState: StateFlow<TriggerSourceDevicesViewState> =
        deviceRepository.devices.map { devices ->
            addTriggerMapper.toAddTriggerViewState(devices)
        }.stateIn(viewModelScope, SharingStarted.Lazily, TriggerSourceDevicesViewState.empty())

    var triggerName: MutableStateFlow<String> = MutableStateFlow("")

    var sourceType: MutableStateFlow<ETriggerSourceType> =
        MutableStateFlow(ETriggerSourceType.FieldInGroup)


    var sourceData: MutableStateFlow<TriggerSourceDataViewState> = MutableStateFlow(
        TriggerSourceAdress_fieldInGroupViewState()
    )

    var sourceSettings: MutableStateFlow<TriggerSettingsViewState?> = MutableStateFlow(
//        NumericTriggerViewState(
//            type = ENumericTriggerType.Bigger
//        )
        null
    )

    var timeSourceTime: MutableStateFlow<Int?> = MutableStateFlow(null)
    var timeSourceData: MutableStateFlow<Calendar?> = MutableStateFlow(null)

    fun changeTriggerName(name: String) {
        triggerName.value = name;
    }

    fun changeSourceType(type: ETriggerSourceType) {
        if (sourceType.value != type) {
            when (sourceType.value) {
                ETriggerSourceType.FieldInGroup -> {
                    sourceData.value = TriggerSourceAdress_fieldInGroupViewState()
                    sourceSettings.value = null
                }
                ETriggerSourceType.FieldInComplexGroup -> {
                    sourceData.value = TriggerSourceAdress_fieldInComplexGroupViewState()
                }
                ETriggerSourceType.TimeTrigger -> {
                    sourceData.value = TriggerTimeSourceDataViewState(
                        type = ETriggerTimeType.Once
                    )
                }
            }
        }
    }

    fun setTimeTriggerTime(
        hour: Int,
        minute: Int,
    ) {
        timeSourceTime.value = hour * 60 + minute / 5 * 5
    }

    fun setDateTriggerDate(
        year: Int,
        month: Int,
        day: Int,
    ) {
        timeSourceData.value = valuesToCalendar(
            year = year,
            month = month,
            day = day,
        )
    }

    fun setNumericSourceType(type: ENumericTriggerType) {
        if (sourceSettings.value != null && sourceSettings.value is NumericTriggerViewState) {
            (sourceSettings.value as NumericTriggerViewState).type = type
        }
    }

    fun setMCSourceType(type: EMCTriggerType) {
        if (sourceSettings.value != null && sourceSettings.value is MCTriggerViewState) {
            (sourceSettings.value as MCTriggerViewState).type = type
        }
    }

    fun setRGBSourceType(type: ERGBTriggerType_numeric) {
        if (sourceSettings.value != null && sourceSettings.value is RGBTriggerViewState) {
            (sourceSettings.value as RGBTriggerViewState).type = type
        }
    }

    fun setRGBSourceContext(context: ERGBTriggerType_context) {
        if (sourceSettings.value != null && sourceSettings.value is RGBTriggerViewState) {
            (sourceSettings.value as RGBTriggerViewState).contextType = context
        }
    }

    fun setTextSourceType(type: ETextTriggerType) {
        if (sourceSettings.value != null && sourceSettings.value is TextTriggerViewState) {
            (sourceSettings.value as TextTriggerViewState).type = type
        }
    }

    ///////////////////////////
    fun setFirstNumericSourceValue(value: Float) {
        if (sourceSettings.value != null && sourceSettings.value is NumericTriggerViewState) {
            (sourceSettings.value as NumericTriggerViewState).value = value
        }
    }

    fun setSecondNumericSourceValue(value: Float) {
        if (sourceSettings.value != null && sourceSettings.value is NumericTriggerViewState) {
            (sourceSettings.value as NumericTriggerViewState).second_value = value
        }
    }

    fun setTextSourceValue(text: String) {
        if (sourceSettings.value != null && sourceSettings.value is TextTriggerViewState) {
            (sourceSettings.value as TextTriggerViewState).value = text
        }
    }

    fun setMCTextSourceValue(value: Int) {
        if (sourceSettings.value != null && sourceSettings.value is MCTriggerViewState) {
            (sourceSettings.value as MCTriggerViewState).value = value
        }
    }

    fun setBooleanSourceType(type: Boolean) {
        if (sourceSettings.value != null && sourceSettings.value is BooleanTriggerViewState) {
            (sourceSettings.value as BooleanTriggerViewState).value = type
        }
    }

    fun setFirstRGBSourceValue(value: Int){
        if (sourceSettings.value != null && sourceSettings.value is RGBTriggerViewState) {
            (sourceSettings.value as RGBTriggerViewState).value = value
        }
    }

    fun setSecondRGBSourceValue(value: Int){
        if (sourceSettings.value != null && sourceSettings.value is RGBTriggerViewState) {
            (sourceSettings.value as RGBTriggerViewState).second_value = value
        }
    }

}
