package hr.kristiankliskovic.devcontrol.data.repository.device

import hr.kristiankliskovic.devcontrol.data.network.deviceService.DeviceService
import hr.kristiankliskovic.devcontrol.data.network.wsService.WebSocketService
import hr.kristiankliskovic.devcontrol.data.repository.authToken.AuthTokenRepository
import hr.kristiankliskovic.devcontrol.model.Device
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import java.util.concurrent.CopyOnWriteArrayList

class DeviceRepositoryImpl(
    private val websocketService: WebSocketService,
    private val authTokenRepository: AuthTokenRepository,
    private val deviceService: DeviceService,
    private val bgDispatcher: CoroutineDispatcher,
) : DeviceRepository {

    val devicesInternal: CopyOnWriteArrayList<Device> = CopyOnWriteArrayList()

    override val devices: Flow<CopyOnWriteArrayList<Device>> = flow {
        websocketService.deviceMessages.collect { device ->
            if (device != null) {
                for ((index, _) in devicesInternal.withIndex()) {
                    if (devicesInternal[index].deviceId == device.deviceId) {
                        devicesInternal.removeAt(index)
                        break
                    }
                }
                devicesInternal.add(device)
                devicesInternal.sortBy { it.deviceId }
                emit(devicesInternal)
            } else {
//                Log.i("QdeviceData", "no device")
            }
        }
    }.flowOn(bgDispatcher)


    override fun getDevice(deviceId: Int): Flow<Device> = flow {
        devices.collect { devs ->
            //Log.i("deviceData", "getDeviceFlow in devrepo")
//            for (device in it) {
//                //Log.i("deviceData", "getDeviceFlow in devrepo__${device.deviceId}")
//            }
            devs.find { it.deviceId == deviceId }?.let { emit(it) }
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
        //Log.i("changeState", "final2")
        return deviceService.changeComplexGroupState(
            authTokenRepository.getAuthToken()!!,
            deviceId,
            groupId,
            state,
        )
    }


}
