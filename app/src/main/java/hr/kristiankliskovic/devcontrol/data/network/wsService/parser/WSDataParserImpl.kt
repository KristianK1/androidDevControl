package hr.kristiankliskovic.devcontrol.data.network.wsService.parser

import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import hr.kristiankliskovic.devcontrol.DevControlApp
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.data.network.model.*
import hr.kristiankliskovic.devcontrol.model.*

class WSDataParserImpl(
    private val gson: Gson,
) : WSDataParser {

    override fun getWSSMessageType(data: String): WSSReceivingMessageTypes {
        val wssMessage = gson.fromJson(data, WssReceivingMessage::class.java)
        return when (wssMessage.messageType) {
            WssReceivingMessageType_DeviceData -> WSSReceivingMessageTypes.DeviceData
            WssReceivingMessageType_UserMessage -> WSSReceivingMessageTypes.UserMessage
            WssReceivingMessageType_DeviceDeleted -> WSSReceivingMessageTypes.DeviceDeleted
            WssReceivingMessageType_lostRights -> WSSReceivingMessageTypes.LostRights
            else -> throw(Throwable("incorrect wss message received"))
        }
    }

    override fun parseDeviceData(data: String): List<Device> {
        val wssMessage = gson.fromJson(data, WssReceivingMessage::class.java)

        val deviceDataJSON = gson.toJson(wssMessage.data)

        val typeToken = object : TypeToken<List<WSSDeviceDataSingle>>() {}.type
        val deviceData = gson.fromJson<List<WSSDeviceDataSingle>>(deviceDataJSON, typeToken)

        val parsedList: MutableList<Device> = mutableListOf()

        for(device in deviceData) {
            val groups: MutableList<DeviceGroup> = mutableListOf()
            for (group in device.deviceFieldGroups) {
                val fields: MutableList<BasicDeviceField> = mutableListOf()
                for (field in group.fields) {
                    fields.add(parseFieldInGroup(field)!!) //ako je null nek pukne
                }
                val mappedGroup = DeviceGroup(
                    groupId = group.id,
                    groupName = group.groupName,
                    fields = fields
                )
                groups.add(mappedGroup)
            }

            val complexGroups: MutableList<DeviceComplexGroup> = mutableListOf()
            for (complexGroup in device.deviceFieldComplexGroups) {
                val states: MutableList<DeviceComplexGroupState> = mutableListOf()
                for (state in complexGroup.fieldGroupStates) {
                    val fields: MutableList<BasicDeviceField> = mutableListOf()
                    for (field in state.fields) {
                        fields.add(parseFieldInComplexGroup(field,
                            complexGroup.readOnly)!!) //ako je null nek pukne
                    }
                    val fullState = DeviceComplexGroupState(
                        stateId = state.id,
                        stateName = state.stateName,
                        fields = fields,
                    )
                    states.add(fullState)
                }
                val fullComplexGroup = DeviceComplexGroup(
                    complexGroupId = complexGroup.id,
                    groupName = complexGroup.groupName,
                    states = states,
                    currentState = complexGroup.currentState,
                    readOnly = complexGroup.readOnly,
                )
                complexGroups.add(fullComplexGroup)
            }

            parsedList.add(
                Device(
                    deviceId = device.id,
                    deviceKey = device.deviceKey,
                    userAdminId = device.userAdminId,
                    deviceName = device.deviceName,
                    groups = groups,
                    complexGroups = complexGroups,
                    isActive = device.isActive,
                )
            )
        }
        return parsedList
    }

    override fun parseUserMessages(data: String): WssLogoutReasonResponse {
        val parsed = gson.fromJson(data, WssLogoutReasonResponse::class.java)
        var int = parsed.logoutReason //magic
        int += 1 //I can't check is it null the normal way
        val errorMessage = DevControlApp.application.getText(R.string.registerError)
        if(parsed.logoutReason == WssLogoutReason.DeletedUser.ordinal){
            Toast.makeText(DevControlApp.application.applicationContext, DevControlApp.application.getText(R.string.RemotedeleteUser_message), Toast.LENGTH_SHORT).show()
        }
        if(parsed.logoutReason == WssLogoutReason.LogoutAll.ordinal){
            Toast.makeText(DevControlApp.application.applicationContext, DevControlApp.application.getText(R.string.RemoteLogout_message), Toast.LENGTH_SHORT).show()
        }
        return parsed
    }

    override fun parseDeviceDeletedMessage(data: String): Int {
        val wssMessage = gson.fromJson(data, WssReceivingMessage::class.java)
        val deviceDeleteData = gson.toJson(wssMessage.data)

        val parsedData = gson.fromJson(deviceDeleteData, WSSDeviceDeleted::class.java)
        return parsedData.deletedDeviceId
    }

    override fun parseLostRightsToDeviceMessage(data: String): Int {
        val wssMessage = gson.fromJson(data, WssReceivingMessage::class.java)
        val deviceDeleteData = gson.toJson(wssMessage.data)

        val parsedData = gson.fromJson(deviceDeleteData, WSSLostRightsToDevice::class.java)
        return parsedData.lostRightsToDevice}

    private fun parseFieldInGroup(field: WSSBasicFieldInGroup): BasicDeviceField? {
        val fieldValue = gson.toJson(field.fieldValue)
        when (field.fieldType) {
            numericFieldLabel -> {
                val fieldValueParsed = gson.fromJson(fieldValue, WSSNumericField::class.java)
                val fieldDirectionReadOnly = parseDirection(fieldValueParsed.fieldDirection)
                return NumericDeviceField(
                    fieldId = field.id,
                    fieldName = field.fieldName,
                    currentValue = fieldValueParsed.fieldValue,
                    minValue = fieldValueParsed.minValue,
                    maxValue = fieldValueParsed.maxValue,
                    valueStep = fieldValueParsed.valueStep,
                    readOnly = field.readOnly || fieldDirectionReadOnly,
                    prefix = fieldValueParsed.prefix,
                    sufix = fieldValueParsed.sufix,
                )
            }
            textFieldLabel -> {
                val fieldValueParsed = gson.fromJson(fieldValue, WSSTextField::class.java)
                val fieldDirectionReadOnly = parseDirection(fieldValueParsed.fieldDirection)
                return TextDeviceField(
                    fieldId = field.id,
                    fieldName = field.fieldName,
                    currentValue = fieldValueParsed.fieldValue,
                    readOnly = field.readOnly || fieldDirectionReadOnly
                )
            }
            buttonFieldLabel -> {
                val fieldValueParsed = gson.fromJson(fieldValue, WSSButtonField::class.java)
                val fieldDirectionReadOnly = parseDirection(fieldValueParsed.fieldDirection)
                return ButtonDeviceField(
                    fieldId = field.id,
                    fieldName = field.fieldName,
                    currentValue = fieldValueParsed.fieldValue,
                    readOnly = field.readOnly || fieldDirectionReadOnly
                )
            }
            multipleChoiceFieldLabel -> {
                val fieldValueParsed =
                    gson.fromJson(fieldValue, WSSMultipleChoiceField::class.java)
                val fieldDirectionReadOnly = parseDirection(fieldValueParsed.fieldDirection)
                return MultipleChoiceDeviceField(
                    fieldId = field.id,
                    fieldName = field.fieldName,
                    currentValue = fieldValueParsed.fieldValue,
                    choices = fieldValueParsed.values,
                    readOnly = field.readOnly || fieldDirectionReadOnly
                )
            }
            RGBFieldLabel -> {
                val fieldValueParsed = gson.fromJson(fieldValue, WSSRGBField::class.java)
                val fieldDirectionReadOnly = parseDirection(fieldValueParsed.fieldDirection)
                return RGBDeviceField(
                    fieldId = field.id,
                    fieldName = field.fieldName,
                    currentValue = RGBValue(
                        R = fieldValueParsed.R,
                        G = fieldValueParsed.G,
                        B = fieldValueParsed.B,
                    ),
                    readOnly = field.readOnly || fieldDirectionReadOnly
                )
            }
            else -> return null
        }
    }

    private fun parseFieldInComplexGroup(field: WSSBasicFieldInComplexGroup, CGreadOnly: Boolean): BasicDeviceField? {
        val fieldValue = gson.toJson(field.fieldValue)
        when (field.fieldType) {
            numericFieldLabel -> {
                val fieldValueParsed = gson.fromJson(fieldValue, WSSNumericField::class.java)
                val fieldDirectionReadOnly = parseDirection(fieldValueParsed.fieldDirection) || CGreadOnly
                return NumericDeviceField(
                    fieldId = field.id,
                    fieldName = field.fieldName,
                    currentValue = fieldValueParsed.fieldValue,
                    minValue = fieldValueParsed.minValue,
                    maxValue = fieldValueParsed.maxValue,
                    valueStep = fieldValueParsed.valueStep,
                    readOnly = fieldDirectionReadOnly,
                    prefix = fieldValueParsed.prefix,
                    sufix = fieldValueParsed.sufix,
                )
            }
            textFieldLabel -> {
                val fieldValueParsed = gson.fromJson(fieldValue, WSSTextField::class.java)
                val fieldDirectionReadOnly = parseDirection(fieldValueParsed.fieldDirection)
                return TextDeviceField(
                    fieldId = field.id,
                    fieldName = field.fieldName,
                    currentValue = fieldValueParsed.fieldValue,
                    readOnly = fieldDirectionReadOnly
                )
            }
            buttonFieldLabel -> {
                val fieldValueParsed = gson.fromJson(fieldValue, WSSButtonField::class.java)
                val fieldDirectionReadOnly = parseDirection(fieldValueParsed.fieldDirection)
                return ButtonDeviceField(
                    fieldId = field.id,
                    fieldName = field.fieldName,
                    currentValue = fieldValueParsed.fieldValue,
                    readOnly = fieldDirectionReadOnly
                )
            }
            multipleChoiceFieldLabel -> {
                val fieldValueParsed =
                    gson.fromJson(fieldValue, WSSMultipleChoiceField::class.java)
                val fieldDirectionReadOnly = parseDirection(fieldValueParsed.fieldDirection)
                return MultipleChoiceDeviceField(
                    fieldId = field.id,
                    fieldName = field.fieldName,
                    currentValue = fieldValueParsed.fieldValue,
                    choices = fieldValueParsed.values,
                    readOnly = fieldDirectionReadOnly
                )
            }
            RGBFieldLabel -> {
                val fieldValueParsed = gson.fromJson(fieldValue, WSSRGBField::class.java)
                val fieldDirectionReadOnly = parseDirection(fieldValueParsed.fieldDirection)
                return RGBDeviceField(
                    fieldId = field.id,
                    fieldName = field.fieldName,
                    currentValue = RGBValue(
                        R = fieldValueParsed.R,
                        G = fieldValueParsed.G,
                        B = fieldValueParsed.B,
                    ),
                    readOnly = fieldDirectionReadOnly
                )
            }
            else -> return null
        }
    }

    private fun parseDirection(direction: String): Boolean {
        return when (direction) {
            fieldDirectionInput -> {
                false
            }
            fieldDirectionOutput -> {
                true
            }
            else -> {
                true
            }
        }
    }
}
