package hr.kristiankliskovic.devcontrol.model


data class Device(
    val deviceId: Int,
    val deviceName: String,
    val userAdminId: Int,
    val deviceKey: String, //TODO remove?
    val groups: List<DeviceGroup>,
    val complexGroups: List<DeviceComplexGroup>,
    val isActive: Boolean,
){
    companion object{
        fun getEmptyObject(): Device{
            return Device(
                deviceId = 0,
                deviceKey = "",
                userAdminId = 0,
                deviceName = "",
                groups = listOf(),
                complexGroups = listOf(),
                isActive = false,
            )
        }
    }
}

data class DeviceGroup(
    val groupId: Int,
    val groupName: String,
    val fields: List<BasicDeviceField>,
)

data class DeviceComplexGroup(
    val complexGroupId: Int,
    val groupName: String,
    val states: List<DeviceComplexGroupState>,
    val currentState: Int,
    val readOnly: Boolean,
)

data class DeviceComplexGroupState(
    val stateId: Int,
    val stateName: String,
    val fields: List<BasicDeviceField>,
)

sealed class BasicDeviceField

data class NumericDeviceField(
    val fieldId: Int,
    val fieldName: String,
    val currentValue: Float,
    val minValue: Float,
    val maxValue: Float,
    val valueStep: Float,
    val readOnly: Boolean,
) : BasicDeviceField()

data class TextDeviceField(
    val fieldId: Int,
    val fieldName: String,
    val currentValue: String,
    val readOnly: Boolean,
) : BasicDeviceField()

data class ButtonDeviceField(
    val fieldId: Int,
    val fieldName: String,
    val currentValue: Boolean,
    val readOnly: Boolean,
) : BasicDeviceField()

data class MultipleChoiceDeviceField(
    val fieldId: Int,
    val fieldName: String,
    val currentValue: Int,
    val choices: List<String>,
    val readOnly: Boolean,
) : BasicDeviceField()

data class RGBDeviceField(
    val fieldId: Int,
    val fieldName: String,
    val currentValue: RGBValue,
    val readOnly: Boolean,
) : BasicDeviceField()
