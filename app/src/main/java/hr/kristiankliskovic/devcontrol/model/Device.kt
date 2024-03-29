package hr.kristiankliskovic.devcontrol.model


data class Device(
    val deviceId: Int,
    val deviceName: String,
    val userAdminId: Int,
    val deviceKey: String,
    val groups: List<DeviceGroup>,
    val complexGroups: List<DeviceComplexGroup>,
    val isActive: Boolean,
)

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

enum class TypesOfFields {
    Numeric,
    Text,
    Boolean,
    MultipleChoice,
    RGB
}

data class NumericDeviceField(
    val fieldId: Int,
    val fieldName: String,
    val currentValue: Float,
    val minValue: Float,
    val maxValue: Float,
    val valueStep: Float,
    val readOnly: Boolean,
    val prefix: String,
    val sufix: String,
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
