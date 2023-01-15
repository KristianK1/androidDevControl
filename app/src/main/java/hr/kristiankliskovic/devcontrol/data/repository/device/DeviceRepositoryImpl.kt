package hr.kristiankliskovic.devcontrol.data.repository.device

import android.util.Log
import com.google.gson.Gson
import hr.kristiankliskovic.devcontrol.data.network.deviceService.DeviceService
import hr.kristiankliskovic.devcontrol.data.network.model.UserPermissionsForDeviceResponse
import hr.kristiankliskovic.devcontrol.data.network.wsService.WebSocketService
import hr.kristiankliskovic.devcontrol.data.repository.authToken.AuthTokenRepository
import hr.kristiankliskovic.devcontrol.data.repository.user.UserRepository
import hr.kristiankliskovic.devcontrol.model.Device
import hr.kristiankliskovic.devcontrol.model.LoggedInUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import java.util.concurrent.CopyOnWriteArrayList

class DeviceRepositoryImpl(
    private val websocketService: WebSocketService,
    private val authTokenRepository: AuthTokenRepository,
    private val deviceService: DeviceService,
    private val bgDispatcher: CoroutineDispatcher,
    userRepository: UserRepository,
) : DeviceRepository {
    private var devicesInternal: CopyOnWriteArrayList<Device> = CopyOnWriteArrayList()

    override val devices: Flow<CopyOnWriteArrayList<Device>> = flow {
        merge(
            websocketService.deviceRemoved,
            websocketService.deviceMessages,
            userRepository.loggedInUser,
        ).collect { it ->
            Log.i("deviceRepo_collect", "start")
            Log.i("deviceRepo_collect", Gson().toJson(it))
            when (it) {
                is LoggedInUser? -> {
                    Log.i("deviceRepo_collect", "split logged in user")
                    val loggedInUser: LoggedInUser? = it
                    if (loggedInUser == null) {
                        Log.i("deviceRepo_collect", "remove all")
                        devicesInternal = CopyOnWriteArrayList()
                        emit(devicesInternal)
                    }
                }
                is Int -> {
                    Log.i("deviceRepo_collect", "split removed")
                    val deviceId: Int = it
                    val deviceList = devicesInternal.toMutableList()
                    deviceList.removeIf { dev ->
                        dev.deviceId == deviceId
                    }
                    deviceList.sortBy { it.deviceId }
                    devicesInternal = CopyOnWriteArrayList(deviceList)
                    emit(devicesInternal)
                }
                is Device? -> {
                    Log.i("deviceRepo_collect", "split deviceData")
                    val device: Device? = it
                    if (device != null) {
                        val deviceList = devicesInternal.toMutableList()
                        deviceList.removeIf { dev ->
                            dev.deviceId == device.deviceId
                        }
                        deviceList.add(device)
                        deviceList.sortBy { it.deviceId }
                        devicesInternal = CopyOnWriteArrayList(deviceList)
                        emit(devicesInternal)
                    } else {
//                        Log.i("QdeviceData", "no device")
                    }
                }
            }
        }

    }.flowOn(bgDispatcher)

    override fun getDevice(deviceId: Int): Flow<Device> = flow {
        devices.collect { devs ->
            devs.find { it.deviceId == deviceId }?.let { emit(it) }
        }
    }.flowOn(bgDispatcher)

    override suspend fun addNewDevice(
        deviceName: String,
        deviceKey: String?,
    ): Boolean {
        return deviceService.addNewDevice(
            authToken = authTokenRepository.getAuthToken()!!,
            deviceName = deviceName,
            deviceKey = deviceKey,
        )
    }

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

    override suspend fun changeComplexGroupState(
        deviceId: Int,
        groupId: Int,
        state: Int,
    ): Boolean {
        return deviceService.changeComplexGroupState(
            authTokenRepository.getAuthToken()!!,
            deviceId,
            groupId,
            state,
        )
    }

    override suspend fun changeDeviceAdmin(deviceId: Int, adminId: Int): Boolean {
        return deviceService.changeDeviceAdmin(
            authToken = authTokenRepository.getAuthToken()!!,
            deviceId = deviceId,
            userId = adminId
        )
    }

    override suspend fun deleteDevice(deviceId: Int): Boolean {
        return deviceService.deleteDevice(
            authToken = authTokenRepository.getAuthToken()!!,
            deviceId = deviceId,
        )
    }

    override suspend fun addUserPermissionToDevice(
        userId: Int,
        deviceId: Int,
        readOnly: Boolean,
    ): Boolean {
        Log.i("perms", "three")
        return deviceService.addUserPermissionToDevice(
            authToken = authTokenRepository.getAuthToken()!!,
            userId = userId,
            deviceId = deviceId,
            readOnly = readOnly
        )
    }

    override suspend fun addUserPermissionToGroup(
        userId: Int,
        deviceId: Int,
        groupId: Int,
        readOnly: Boolean,
    ): Boolean {
        Log.i("perms", "three")
        return deviceService.addUserPermissionToGroup(
            authToken = authTokenRepository.getAuthToken()!!,
            userId = userId,
            deviceId = deviceId,
            groupId = groupId,
            readOnly = readOnly
        )
    }

    override suspend fun addUserPermissionToField(
        userId: Int,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
        readOnly: Boolean,
    ): Boolean {
        Log.i("perms", "three")
        return deviceService.addUserPermissionToField(
            authToken = authTokenRepository.getAuthToken()!!,
            userId = userId,
            deviceId = deviceId,
            groupId = groupId,
            fieldId = fieldId,
            readOnly = readOnly
        )
    }

    override suspend fun addUserPermissionToComplexGroup(
        userId: Int,
        deviceId: Int,
        complexGroupId: Int,
        readOnly: Boolean,
    ): Boolean {
        Log.i("perms", "three")
        return deviceService.addUserPermissionToComplexGroup(
            authToken = authTokenRepository.getAuthToken()!!,
            userId = userId,
            deviceId = deviceId,
            complexGroupId = complexGroupId,
            readOnly = readOnly
        )
    }

    override suspend fun deleteUserPermissionToDevice(userId: Int, deviceId: Int): Boolean {
        return deviceService.deleteUserPermissionToDevice(
            authToken = authTokenRepository.getAuthToken()!!,
            userId = userId,
            deviceId = deviceId,
        )
    }

    override suspend fun deleteUserPermissionToGroup(
        userId: Int,
        deviceId: Int,
        groupId: Int,
    ): Boolean {
        return deviceService.deleteUserPermissionToGroup(
            authToken = authTokenRepository.getAuthToken()!!,
            userId = userId,
            deviceId = deviceId,
            groupId = groupId,
        )
    }

    override suspend fun deleteUserPermissionToField(
        userId: Int,
        deviceId: Int,
        groupId: Int,
        fieldId: Int,
    ): Boolean {
        return deviceService.deleteUserPermissionToField(
            authToken = authTokenRepository.getAuthToken()!!,
            userId = userId,
            deviceId = deviceId,
            groupId = groupId,
            fieldId = fieldId,
        )
    }

    override suspend fun deleteUserPermissionToComplexGroup(
        userId: Int,
        deviceId: Int,
        complexGroupId: Int,
    ): Boolean {
        return deviceService.deleteUserPermissionToComplexGroup(
            authToken = authTokenRepository.getAuthToken()!!,
            userId = userId,
            deviceId = deviceId,
            complexGroupId = complexGroupId,
        )
    }


    private val allPermissionsForDeviceResponseInternal: MutableStateFlow<UserPermissionsForDeviceResponse?> =
        MutableStateFlow(null);
    override val allPermissionsForDeviceResponse: StateFlow<UserPermissionsForDeviceResponse?> =
        allPermissionsForDeviceResponseInternal.asStateFlow()

    override suspend fun getUserPermissionsForDevice(deviceId: Int) {
        val response = deviceService.getUserPermissionsForDevice(
            authToken = authTokenRepository.getAuthToken()!!,
            deviceId = deviceId,
        )
        allPermissionsForDeviceResponseInternal.emit(response)
    }

    override fun clearAllPermissionsResponse() {
        allPermissionsForDeviceResponseInternal.value = null
    }
}
