package hr.kristiankliskovic.devcontrol.data.network.wsService.parser.deviceData

import hr.kristiankliskovic.devcontrol.data.network.model.WSSReceivingMessageData
import hr.kristiankliskovic.devcontrol.data.network.model.WSSReceivingMessageTypes
import hr.kristiankliskovic.devcontrol.data.network.model.WssLogoutReasonResponse
import hr.kristiankliskovic.devcontrol.model.Device

interface WSDataParser {
    fun getWSSMessageType(data: String): WSSReceivingMessageTypes
    fun parseDeviceData(data: String): Device
    fun parseUserMessages(data: String): WssLogoutReasonResponse
}
