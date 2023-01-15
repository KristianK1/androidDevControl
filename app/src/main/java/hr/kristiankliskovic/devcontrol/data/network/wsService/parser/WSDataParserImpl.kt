package hr.kristiankliskovic.devcontrol.data.network.wsService.parser

import android.util.Log
import com.google.gson.Gson
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

    override fun parseDeviceData(data: String): Device {
//    val data2 =
//        "{ \"id\": 4, \"deviceKey\": \"d25a1fa2bad54a4b9743c575c79b7afa\", \"deviceName\": \"esp32thin\", \"userAdminId\": 16, \"deviceFieldGroups\": [ { \"id\": 0, \"groupName\": \"group1_rename\", \"fields\": [ { \"id\": 1, \"fieldName\": \"field1\", \"fieldType\": \"numeric\", \"fieldValue\": { \"minValue\": -1, \"maxValue\": 25, \"fieldValue\": -1, \"fieldDirection\": \"input\", \"valueStep\": 1 }, \"readOnly\": false }, { \"id\": 2, \"fieldName\": \"field2\", \"fieldType\": \"numeric\", \"fieldValue\": { \"fieldDirection\": \"input\", \"fieldValue\": -1, \"minValue\": -1, \"maxValue\": 25, \"valueStep\": 0.5 }, \"readOnly\": false }, { \"id\": 3, \"fieldName\": \"field3\", \"fieldType\": \"text\", \"fieldValue\": { \"fieldValue\": \"23 deg C\", \"fieldDirection\": \"input\" }, \"readOnly\": false }, { \"id\": 4, \"fieldName\": \"field4\", \"fieldType\": \"multipleChoice\", \"fieldValue\": { \"fieldValue\": 0, \"values\": [ \"OFF\", \"ANIMACIJA 1\", \"ANIMACIJA 2\", \"ANIMACIJA 3\" ], \"fieldDirection\": \"input\" }, \"readOnly\": false }, { \"id\": 5, \"fieldName\": \"field5\", \"fieldType\": \"button\", \"fieldValue\": { \"fieldValue\": false, \"fieldDirection\": \"input\" }, \"readOnly\": false }, { \"id\": 6, \"fieldName\": \"field6\", \"fieldType\": \"RGB\", \"fieldValue\": { \"fieldDirection\": \"input\", \"B\": 0, \"R\": 0, \"G\": 0 }, \"readOnly\": false }, { \"id\": 7, \"fieldName\": \"field7\", \"fieldType\": \"button\", \"fieldValue\": { \"fieldDirection\": \"input\", \"fieldValue\": true }, \"readOnly\": false }, { \"id\": 8, \"fieldName\": \"field8\", \"fieldType\": \"button\", \"fieldValue\": { \"fieldValue\": true, \"fieldDirection\": \"input\" }, \"readOnly\": false }, { \"id\": 50, \"fieldName\": \"field0\", \"fieldType\": \"numeric\", \"fieldValue\": { \"maxValue\": 25, \"minValue\": -1, \"fieldDirection\": \"input\", \"valueStep\": 1, \"fieldValue\": -1 }, \"readOnly\": false }, { \"id\": 99, \"fieldName\": \"field9\", \"fieldType\": \"button\", \"fieldValue\": { \"fieldValue\": true, \"fieldDirection\": \"input\" }, \"readOnly\": false } ] }, { \"id\": 1, \"groupName\": \"group2\", \"fields\": [ { \"id\": 0, \"fieldName\": \"field0\", \"fieldType\": \"numeric\", \"fieldValue\": { \"minValue\": -1, \"fieldValue\": -1, \"maxValue\": 25, \"fieldDirection\": \"input\", \"valueStep\": 1 }, \"readOnly\": false }, { \"id\": 1, \"fieldName\": \"field1\", \"fieldType\": \"numeric\", \"fieldValue\": { \"valueStep\": 1, \"fieldDirection\": \"input\", \"maxValue\": 25, \"minValue\": -1, \"fieldValue\": -1 }, \"readOnly\": false }, { \"id\": 2, \"fieldName\": \"field2\", \"fieldType\": \"numeric\", \"fieldValue\": { \"fieldValue\": -1, \"valueStep\": 1, \"maxValue\": 25, \"minValue\": -1, \"fieldDirection\": \"input\" }, \"readOnly\": false }, { \"id\": 3, \"fieldName\": \"field3\", \"fieldType\": \"text\", \"fieldValue\": { \"fieldDirection\": \"input\", \"fieldValue\": \"23 deg C\" }, \"readOnly\": false }, { \"id\": 4, \"fieldName\": \"field4\", \"fieldType\": \"multipleChoice\", \"fieldValue\": { \"values\": [ \"OFF\", \"ANIMACIJA 1\", \"ANIMACIJA 2\" ], \"fieldDirection\": \"input\", \"fieldValue\": 0 }, \"readOnly\": false }, { \"id\": 5, \"fieldName\": \"field5\", \"fieldType\": \"button\", \"fieldValue\": { \"fieldValue\": false, \"fieldDirection\": \"input\" }, \"readOnly\": false }, { \"id\": 6, \"fieldName\": \"field6\", \"fieldType\": \"RGB\", \"fieldValue\": { \"B\": 0, \"G\": 0, \"fieldDirection\": \"input\", \"R\": 0 }, \"readOnly\": false } ] } ], \"deviceFieldComplexGroups\": [ { \"id\": 0, \"groupName\": \"complexGroup1\", \"currentState\": 0, \"fieldGroupStates\": [ { \"id\": 0, \"stateName\": \"individual\", \"fields\": [ { \"fieldType\": \"numeric\", \"id\": 0, \"fieldName\": \"RField\", \"fieldValue\": { \"fieldValue\": 0, \"minValue\": 0, \"fieldDirection\": \"input\", \"valueStep\": 1, \"maxValue\": 25 } }, { \"fieldType\": \"numeric\", \"fieldName\": \"GField\", \"fieldValue\": { \"fieldValue\": 0, \"fieldDirection\": \"input\", \"valueStep\": 1, \"maxValue\": 25, \"minValue\": 0 }, \"id\": 1 }, { \"fieldName\": \"BField\", \"fieldValue\": { \"fieldValue\": 0, \"maxValue\": 25, \"minValue\": 0, \"valueStep\": 1, \"fieldDirection\": \"input\" }, \"fieldType\": \"numeric\", \"id\": 2 } ] }, { \"id\": 1, \"stateName\": \"rgb\", \"fields\": [ { \"fieldValue\": { \"B\": 0, \"R\": 0, \"fieldDirection\": \"input\", \"G\": 0 }, \"fieldType\": \"RGB\", \"fieldName\": \"RGB\", \"id\": 0 } ] }, { \"id\": 2, \"stateName\": \"animations\", \"fields\": [ { \"fieldType\": \"multipleChoice\", \"fieldValue\": { \"fieldDirection\": \"input\", \"values\": [ \"OFF\", \"A1\", \"A2\", \"A3\", \"A4\", \"A5\" ], \"fieldValue\": 0 }, \"fieldName\": \"animations\", \"id\": 0 } ] } ], \"readOnly\": false }, { \"id\": 1, \"groupName\": \"complexGroup1_new\", \"currentState\": 0, \"fieldGroupStates\": [ { \"id\": 0, \"stateName\": \"individual\", \"fields\": [ { \"fieldName\": \"RField\", \"fieldValue\": { \"fieldValue\": 0, \"minValue\": 0, \"valueStep\": 1, \"fieldDirection\": \"input\", \"maxValue\": 25 }, \"id\": 0, \"fieldType\": \"numeric\" }, { \"fieldValue\": { \"fieldValue\": 0, \"fieldDirection\": \"input\", \"valueStep\": 1, \"minValue\": 0, \"maxValue\": 25 }, \"id\": 1, \"fieldType\": \"numeric\", \"fieldName\": \"GField\" }, { \"fieldValue\": { \"fieldDirection\": \"input\", \"valueStep\": 1, \"fieldValue\": 0, \"maxValue\": 25, \"minValue\": 0 }, \"id\": 2, \"fieldName\": \"BField\", \"fieldType\": \"numeric\" } ] }, { \"id\": 1, \"stateName\": \"rgb\", \"fields\": [ { \"fieldValue\": { \"R\": 0, \"B\": 0, \"fieldDirection\": \"input\", \"G\": 0 }, \"fieldType\": \"RGB\", \"fieldName\": \"RGB\", \"id\": 0 } ] }, { \"id\": 2, \"stateName\": \"animations\", \"fields\": [ { \"fieldValue\": { \"fieldDirection\": \"input\", \"fieldValue\": 0, \"values\": [ \"OFF\", \"A1\", \"A2\", \"A3\", \"A4\" ] }, \"id\": 0, \"fieldType\": \"multipleChoice\", \"fieldName\": \"animations\" } ] } ], \"readOnly\": false } ], \"updateTimeStamp\": 1672875209824 }"

        val wssMessage = gson.fromJson(data, WssReceivingMessage::class.java)
        val deviceDataJSON = gson.toJson(wssMessage.data)
        Log.i("parsingData", "parsing")
        Log.i("parsingData", deviceDataJSON)

        val deviceData = gson.fromJson(deviceDataJSON, WSSDeviceData::class.java)
        Log.i("parsingData", "parsing2")
        val groups: MutableList<DeviceGroup> = mutableListOf()
        for (group in deviceData.deviceFieldGroups) {
            val fields: MutableList<BasicDeviceField> = mutableListOf()
            for (field in group.fields) {
                Log.i("parsingData", "parsing21")
                fields.add(parseFieldInGroup(field)!!) //ako je null nek pukne
                Log.i("parsingData", "parsing22")
            }
            val mappedGroup = DeviceGroup(
                groupId = group.id,
                groupName = group.groupName,
                fields = fields
            )
            groups.add(mappedGroup)
        }

        val complexGroups: MutableList<DeviceComplexGroup> = mutableListOf()
        for (complexGroup in deviceData.deviceFieldComplexGroups) {
            val states: MutableList<DeviceComplexGroupState> = mutableListOf()
            for (state in complexGroup.fieldGroupStates) {
                val fields: MutableList<BasicDeviceField> = mutableListOf()
                for (field in state.fields) {
                    Log.i("parsingData", "parsing23")
                    fields.add(parseFieldInComplexGroup(field)!!) //ako je null nek pukne
                    Log.i("parsingData", "parsing24")
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
        Log.i("parsingData", "${deviceData.updateTimeStamp}")

        return Device(
            deviceId = deviceData.id,
            deviceKey = deviceData.deviceKey,
            userAdminId = deviceData.userAdminId,
            deviceName = deviceData.deviceName,
            groups = groups,
            complexGroups = complexGroups,
            isActive = deviceData.isActive,
        )
    }

    override fun parseUserMessages(data: String): WssLogoutReasonResponse {
        val parsed = gson.fromJson(data, WssLogoutReasonResponse::class.java)
        var int = parsed.logoutReason //magic
        int += 1 //I can't check is it null the normal way
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
                    readOnly = field.readOnly || fieldDirectionReadOnly
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

    private fun parseFieldInComplexGroup(field: WSSBasicFieldInComplexGroup): BasicDeviceField? {
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
                    readOnly = fieldDirectionReadOnly
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
