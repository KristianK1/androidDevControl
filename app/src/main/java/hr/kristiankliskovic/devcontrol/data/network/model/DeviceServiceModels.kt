package hr.kristiankliskovic.devcontrol.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ChangeNumericField(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val fieldId: Int,
    val fieldValue: Float,
)

@Serializable
data class ChangeTextField(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val fieldId: Int,
    val fieldValue: String,
)

@Serializable
data class ChangeButtonField(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val fieldId: Int,
    val fieldValue: Boolean,
)

@Serializable
data class ChangeMCField(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val fieldId: Int,
    val fieldValue: Int,
)

@Serializable
data class ChangeRGBField(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val fieldId: Int,
    val fieldValue: RGBvalue,
)

@Serializable
data class RGBvalue(
    val R: Int,
    val G: Int,
    val B: Int,
)



@Serializable
data class ChangeNumericFieldInCG(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val stateId: Int,
    val fieldId: Int,
    val fieldValue: Float,
)

@Serializable
data class ChangeTextFieldInCG(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val stateId: Int,
    val fieldId: Int,
    val fieldValue: String,
)

@Serializable
data class ChangeButtonFieldInCG(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val stateId: Int,
    val fieldId: Int,
    val fieldValue: Boolean,
)

@Serializable
data class ChangeMCFieldInCG(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val stateId: Int,
    val fieldId: Int,
    val fieldValue: Int,
)

@Serializable
data class ChangeRGBFieldInCG(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val stateId: Int,
    val fieldId: Int,
    val fieldValue: RGBvalue,
)

@Serializable
data class ChangeComplexGroupState(
    val authToken: String,
    val deviceId: Int,
    val groupId: Int,
    val state: Int,
)
