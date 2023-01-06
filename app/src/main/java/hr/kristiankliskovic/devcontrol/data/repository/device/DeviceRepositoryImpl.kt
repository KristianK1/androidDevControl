package hr.kristiankliskovic.devcontrol.data.repository.device

import android.util.Log
import androidx.core.util.rangeTo
import hr.kristiankliskovic.devcontrol.data.network.deviceService.DeviceService
import hr.kristiankliskovic.devcontrol.data.network.wsService.WebSocketService
import hr.kristiankliskovic.devcontrol.data.repository.authToken.AuthTokenRepository
import hr.kristiankliskovic.devcontrol.model.Device
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DeviceRepositoryImpl(
    private val websocketService: WebSocketService,
    private val authTokenRepository: AuthTokenRepository,
    private val deviceService: DeviceService,
    private val bgDispatcher: CoroutineDispatcher,
) : DeviceRepository {

    private var devicesInternal: MutableList<Device> = mutableListOf()

    override val devices: Flow<List<Device>> = flow {
        websocketService.deviceMessages.collect { device ->
            if (device != null) {
                for ((index, _) in devicesInternal.withIndex()) {
                    if (devicesInternal[index].deviceId == device.deviceId) {
                        devicesInternal.removeAt(index)
                        break
                    }
                }
                devicesInternal.add(device)
                emit(devicesInternal)
            } else {
                Log.i("QdeviceData", "no device")
            }
        }
    }.flowOn(bgDispatcher)

    override fun getDevice(deviceId: Int): Flow<Device> = flow {
        devices.collect {
            Log.i("deviceData", "getDeviceFlow in devrepo")
            for (device in it) {
                Log.i("deviceData", "getDeviceFlow in devrepo__${device.deviceId}")
            }
            emit(devicesInternal.find { it.deviceId == deviceId }!!)
        }
    }.flowOn(bgDispatcher)

    override suspend fun changeNumericField(
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValue: Float,
    ): Boolean {
        return deviceService.changeNumericField(
            authTokenRepository.getAuthToken()!!,
            deviceId,
            groupId,
            fieldId,
            fieldValue,
        )
    }

    override suspend fun changeTextField(
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValue: String,
    ): Boolean {
        return deviceService.changeTextField(
            authTokenRepository.getAuthToken()!!,
            deviceId,
            groupId,
            fieldId,
            fieldValue,
        )
    }

    override suspend fun changeButtonField(
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValue: Boolean,
    ): Boolean {
        return deviceService.changeButtonField(
            authTokenRepository.getAuthToken()!!,
            deviceId,
            groupId,
            fieldId,
            fieldValue,
        )
    }

    override suspend fun changeMCField(
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValue: Int,
    ): Boolean {
        return deviceService.changeMCField(
            authTokenRepository.getAuthToken()!!,
            deviceId,
            groupId,
            fieldId,
            fieldValue,
        )
    }

    override suspend fun changeRGBField(
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        fieldValueR: Int,
        fieldValueG: Int,
        fieldValueB: Int,
    ): Boolean {
        return deviceService.changeRGBField(
            authTokenRepository.getAuthToken()!!,
            deviceId,
            groupId,
            fieldId,
            fieldValueR,
            fieldValueG,
            fieldValueB,
        )
    }

    override suspend fun changeNumericFieldInComplexGroup(
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValue: Float,
    ): Boolean {
        return deviceService.changeNumericFieldInComplexGroup(
            authTokenRepository.getAuthToken()!!,
            deviceId,
            groupId,
            stateId,
            fieldId,
            fieldValue,
        )
    }

    override suspend fun changeTextFieldInComplexGroup(
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValue: String,
    ): Boolean {
        return deviceService.changeTextFieldInComplexGroup(
            authTokenRepository.getAuthToken()!!,
            deviceId,
            groupId,
            stateId,
            fieldId,
            fieldValue,
        )
    }

    override suspend fun changeButtonFieldInComplexGroup(
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValue: Boolean,
    ): Boolean {
        return deviceService.changeButtonFieldInComplexGroup(
            authTokenRepository.getAuthToken()!!,
            deviceId,
            groupId,
            stateId,
            fieldId,
            fieldValue,
        )
    }

    override suspend fun changeMCFieldInComplexGroup(
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValue: Int,
    ): Boolean {
        return deviceService.changeMCFieldInComplexGroup(
            authTokenRepository.getAuthToken()!!,
            deviceId,
            groupId,
            stateId,
            fieldId,
            fieldValue,
        )
    }

    override suspend fun changeRGBFieldInComplexGroup(
        deviceId: Int,
        groupId: Int,
        stateId: Int,
        fieldId: Int,
        fieldValueR: Int,
        fieldValueG: Int,
        fieldValueB: Int,
    ): Boolean {
        return deviceService.changeRGBFieldInComplexGroup(
            authTokenRepository.getAuthToken()!!,
            deviceId,
            groupId,
            stateId,
            fieldId,
            fieldValueR,
            fieldValueG,
            fieldValueB,
        )
    }

    override suspend fun changeComplexGroupState(deviceId: Int, groupId: Int, state: Int): Boolean {
        return deviceService.changeComplexGroupState(
            authTokenRepository.getAuthToken()!!,
            deviceId,
            groupId,
            state,
        )
    }


}
