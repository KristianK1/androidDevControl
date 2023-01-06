package hr.kristiankliskovic.devcontrol.ui.deviceControls

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import hr.kristiankliskovic.devcontrol.data.repository.device.DeviceRepository
import hr.kristiankliskovic.devcontrol.model.RGBValue
import hr.kristiankliskovic.devcontrol.ui.deviceControls.mapper.DeviceControlsMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DeviceControlsViewModel(
    deviceId: Int,
    private val deviceRepository: DeviceRepository,
    private val deviceControlsMapper: DeviceControlsMapper,
) : ViewModel() {

    val deviceControlsViewState: StateFlow<DeviceControlsViewState> =
        deviceRepository.getDevice(deviceId = deviceId).map { deviceData ->
            Log.i("deviceData", "devControlViewModel")
            Log.i("deviceData", Gson().toJson(deviceData))

            val x = deviceControlsMapper.toDeviceControlsViewState(deviceData)
            Log.i("deviceData_mapEnd", Gson().toJson(x))
            x
        }.stateIn(viewModelScope, SharingStarted.Lazily, DeviceControlsViewState.empty())


    fun onChangeNumeric(deviceId: Int, groupId: Int, fieldId: Int, value: Float) {
        viewModelScope.launch {
            deviceRepository.changeNumericField(deviceId, groupId, fieldId, value)
        }
    }

    fun onChangeText(deviceId: Int, groupId: Int, fieldId: Int, value: String) {
        viewModelScope.launch {
            deviceRepository.changeTextField(deviceId, groupId, fieldId, value)
        }
    }

    fun onChangeButton(deviceId: Int, groupId: Int, fieldId: Int, value: Boolean) {
        viewModelScope.launch {
            deviceRepository.changeButtonField(deviceId, groupId, fieldId, value)
        }
    }

    fun onChangeMultipleChoice(deviceId: Int, groupId: Int, fieldId: Int, value: Int) {
        viewModelScope.launch {
            deviceRepository.changeMCField(deviceId, groupId, fieldId, value)
        }
    }

    fun onChangeRGB(deviceId: Int, groupId: Int, fieldId: Int, value: RGBValue) {
        viewModelScope.launch {
            deviceRepository.changeRGBField(deviceId, groupId, fieldId, value.R, value.G, value.B)
        }
    }

    fun changeComplexGroupState(deviceId: Int, groupId: Int, stateId: Int) {
        viewModelScope.launch {
            deviceRepository.changeComplexGroupState(deviceId, groupId, stateId)
        }
    }

    fun onChangeNumericInCG(deviceId: Int, groupId: Int, stateId: Int, fieldId: Int, value: Float) {
        viewModelScope.launch {
            deviceRepository.changeNumericFieldInComplexGroup(deviceId,
                groupId,
                stateId,
                fieldId,
                value)
        }
    }

    fun onChangeTextInCG(deviceId: Int, groupId: Int, stateId: Int, fieldId: Int, value: String) {
        viewModelScope.launch {
            deviceRepository.changeTextFieldInComplexGroup(deviceId,
                groupId,
                stateId,
                fieldId,
                value)

        }
    }

    fun onChangeButtonInCG(
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        value: Boolean,
    ) {
        viewModelScope.launch {
            deviceRepository.changeButtonFieldInComplexGroup(deviceId,
                groupId,
                stateId,
                fieldId,
                value)

        }
    }

    fun onChangeMultipleChoiceInCG(
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        value: Int,
    ) {
        viewModelScope.launch {
            deviceRepository.changeMCFieldInComplexGroup(deviceId, groupId, stateId, fieldId, value)

        }
    }

    fun onChangeRGBInCG(deviceId: Int, groupId: Int, stateId: Int, fieldId: Int, value: RGBValue) {
        viewModelScope.launch {
            deviceRepository.changeRGBFieldInComplexGroup(deviceId,
                groupId,
                stateId,
                fieldId,
                value.R,
                value.G,
                value.B)

        }
    }
}
