package hr.kristiankliskovic.devcontrol.ui.triggerSettings_seeAll.mapper

import android.util.Log
import com.google.gson.Gson
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.triggerViewComponents.*
import hr.kristiankliskovic.devcontrol.ui.triggerSettings_seeAll.SeeAllTriggersViewState
import hr.kristiankliskovic.devcontrol.utils.ISOtoCalendar
import hr.kristiankliskovic.devcontrol.utils.addTimeToCalendar
import hr.kristiankliskovic.devcontrol.utils.getLocalTimeForDisplayFromISO
import hr.kristiankliskovic.devcontrol.utils.getTimeZoneOffsetInMinutes
import java.util.*
import kotlin.math.abs

class SeeAllTriggersMapperImpl : SeeAllTriggersMapper {
    override fun triggersToSeeAllTriggersViewState(
        triggers: List<ITrigger>,
        devices: List<Device>,
    ): SeeAllTriggersViewState {
        Log.i("triggerMap", "start mapper F")
        Log.i("triggerMap_devices", "" + devices.size)
        return SeeAllTriggersViewState(
            triggers = triggers.mapNotNull { trigger ->
                Log.i("triggerMap", "start mapper F2")
                Log.i("triggerMap", "triggerName: ${trigger.id}")

                var sourceAddressViewState: TriggerView_AddressViewState? = null
                var sourceFieldInfo: SourceFieldInfoViewState? = null
                var timeTriggerInfo: TimeTriggerInfoViewState? = null

                val sourceData = trigger.sourceData
                val sourceSettings = trigger.settings
                Log.i("triggerMap", "start source data")

                when (trigger.sourceType) {
                    ETriggerSourceType.FieldInGroup -> {
                        var sourceAddressViewState: TriggerView_AddressViewState

                        val sourceData = trigger.sourceData;
                        Log.i("triggerMap_printSourceData", Gson().toJson(sourceData))
                        Log.i("triggerMap_printSourceData_type", "" + sourceData::class)

                        if (sourceData is ITriggerSourceAdress_fieldInGroup) {
                            Log.i("triggerMap", "source data is FG")
                            val device = devices.find { it.deviceId == sourceData.deviceId }
                            if (device != null) {
                                Log.i("triggerMap", "source_FG_foundDevice")

                                val group = device.groups.find { it.groupId == sourceData.groupId }
                                if (group != null) {
                                    Log.i("triggerMap", "source_FG_foundGroup")
                                    val fields = group.fields
                                    for (field in fields) {
                                        when (field) {
                                            is NumericDeviceField -> {
                                                if (field.fieldId == sourceData.fieldId) {
                                                    Log.i("triggerMap",
                                                        "source_FG_foundNumericField")
                                                    sourceAddressViewState =
                                                        TriggerView_AddressViewState(
                                                            sourceDeviceId = device.deviceId,
                                                            sourceDeviceName = device.deviceName,
                                                            sourceGroupId = group.groupId,
                                                            sourceGroupName = group.groupName,
                                                            sourceFieldId = field.fieldId,
                                                            sourceFieldName = field.fieldName,
                                                        )
                                                    if (sourceSettings is INumericTrigger) {
                                                        sourceFieldInfo =
                                                            SourceNumericFieldInfoViewState(
                                                                fieldNumericType = sourceSettings.type,
                                                                sourceFieldNumericValue = sourceSettings.value,
                                                                sourceFieldNumericSecondValue = sourceSettings.second_value,
                                                            )
                                                    }
                                                }
                                            }
                                            is TextDeviceField -> {
                                                if (field.fieldId == sourceData.fieldId) {
                                                    Log.i("triggerMap", "source_FG_foundTextField")
                                                    sourceAddressViewState =
                                                        TriggerView_AddressViewState(
                                                            sourceDeviceId = device.deviceId,
                                                            sourceDeviceName = device.deviceName,
                                                            sourceGroupId = group.groupId,
                                                            sourceGroupName = group.groupName,
                                                            sourceFieldId = field.fieldId,
                                                            sourceFieldName = field.fieldName,
                                                        )
                                                    if (sourceSettings is ITextTrigger) {
                                                        sourceFieldInfo =
                                                            SourceTextFieldInfoViewState(
                                                                fieldTextType = sourceSettings.type,
                                                                sourceTextValue = sourceSettings.value,
                                                            )
                                                    }
                                                }
                                            }
                                            is ButtonDeviceField -> {
                                                if (field.fieldId == sourceData.fieldId) {
                                                    Log.i("triggerMap",
                                                        "source_FG_foundButtonField")
                                                    sourceAddressViewState =
                                                        TriggerView_AddressViewState(
                                                            sourceDeviceId = device.deviceId,
                                                            sourceDeviceName = device.deviceName,
                                                            sourceGroupId = group.groupId,
                                                            sourceGroupName = group.groupName,
                                                            sourceFieldId = field.fieldId,
                                                            sourceFieldName = field.fieldName,
                                                        )
                                                    if (sourceSettings is IBooleanTrigger) {
                                                        sourceFieldInfo =
                                                            SourceButtonFieldInfoViewState(
                                                                sourceBooleanValue = sourceSettings.value,
                                                            )
                                                    }
                                                }
                                            }
                                            is MultipleChoiceDeviceField -> {
                                                if (field.fieldId == sourceData.fieldId) {
                                                    Log.i("triggerMap", "source_FG_foundMCField")
                                                    sourceAddressViewState =
                                                        TriggerView_AddressViewState(
                                                            sourceDeviceId = device.deviceId,
                                                            sourceDeviceName = device.deviceName,
                                                            sourceGroupId = group.groupId,
                                                            sourceGroupName = group.groupName,
                                                            sourceFieldId = field.fieldId,
                                                            sourceFieldName = field.fieldName,
                                                        )
                                                    if (sourceSettings is IMCTrigger) {
                                                        val MCstate =
                                                            field.choices[sourceSettings.value]
                                                        sourceFieldInfo =
                                                            SourceMCFieldInfoViewState(
                                                                fieldMCType = sourceSettings.type,
                                                                sourceFieldMCStateId = sourceSettings.value,
                                                                sourceFieldMCStateName = MCstate,
                                                            )
                                                    }
                                                }
                                            }
                                            is RGBDeviceField -> {
                                                if (field.fieldId == sourceData.fieldId) {
                                                    Log.i("triggerMap", "source_FG_foundRGBField")
                                                    sourceAddressViewState =
                                                        TriggerView_AddressViewState(
                                                            sourceDeviceId = device.deviceId,
                                                            sourceDeviceName = device.deviceName,
                                                            sourceGroupId = group.groupId,
                                                            sourceGroupName = group.groupName,
                                                            sourceFieldId = field.fieldId,
                                                            sourceFieldName = field.fieldName,
                                                        )
                                                    if (sourceSettings is IRGBTrigger) {
                                                        sourceFieldInfo =
                                                            SourceRGBFieldInfoViewState(
                                                                fieldRGBType = sourceSettings.type,
                                                                fieldRGBContext = sourceSettings.contextType,
                                                                sourceFieldRGBValue = sourceSettings.value,
                                                                sourceFieldRGBSecondValue = sourceSettings.second_value,
                                                            )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                    ETriggerSourceType.FieldInComplexGroup -> {
                        if (sourceData is ITriggerSourceAdress_fieldInComplexGroup) {
                            Log.i("triggerMap", "source data is FCG")
                            val device = devices.find { it.deviceId == sourceData.deviceId }
                            if (device != null) {
                                val complexGroup =
                                    device.complexGroups.find { it.complexGroupId == sourceData.complexGroupId }
                                if (complexGroup != null) {
                                    val state =
                                        complexGroup.states.find { it.stateId == sourceData.stateId }
                                    if (state != null) {
                                        val fields = state.fields
                                        for (field in fields) {
                                            when (field) {
                                                is NumericDeviceField -> {
                                                    if (field.fieldId == sourceData.fieldId) {
                                                        sourceAddressViewState =
                                                            TriggerView_AddressViewState(
                                                                sourceDeviceId = device.deviceId,
                                                                sourceDeviceName = device.deviceName,
                                                                sourceGroupId = complexGroup.complexGroupId,
                                                                sourceGroupName = complexGroup.groupName,
                                                                sourceStateId = state.stateId,
                                                                sourceStateName = state.stateName,
                                                                sourceFieldId = field.fieldId,
                                                                sourceFieldName = field.fieldName,
                                                            )
                                                        if (sourceSettings is INumericTrigger) {
                                                            sourceFieldInfo =
                                                                SourceNumericFieldInfoViewState(
                                                                    fieldNumericType = sourceSettings.type,
                                                                    sourceFieldNumericValue = sourceSettings.value,
                                                                    sourceFieldNumericSecondValue = sourceSettings.second_value,
                                                                )
                                                        }
                                                    }
                                                }
                                                is TextDeviceField -> {
                                                    if (field.fieldId == sourceData.fieldId) {
                                                        sourceAddressViewState =
                                                            TriggerView_AddressViewState(
                                                                sourceDeviceId = device.deviceId,
                                                                sourceDeviceName = device.deviceName,
                                                                sourceGroupId = complexGroup.complexGroupId,
                                                                sourceGroupName = complexGroup.groupName,
                                                                sourceStateId = state.stateId,
                                                                sourceStateName = state.stateName,
                                                                sourceFieldId = field.fieldId,
                                                                sourceFieldName = field.fieldName,
                                                            )
                                                        if (sourceSettings is ITextTrigger) {
                                                            sourceFieldInfo =
                                                                SourceTextFieldInfoViewState(
                                                                    fieldTextType = sourceSettings.type,
                                                                    sourceTextValue = sourceSettings.value,
                                                                )
                                                        }
                                                    }
                                                }
                                                is ButtonDeviceField -> {
                                                    if (field.fieldId == sourceData.fieldId) {
                                                        sourceAddressViewState =
                                                            TriggerView_AddressViewState(
                                                                sourceDeviceId = device.deviceId,
                                                                sourceDeviceName = device.deviceName,
                                                                sourceGroupId = complexGroup.complexGroupId,
                                                                sourceGroupName = complexGroup.groupName,
                                                                sourceStateId = state.stateId,
                                                                sourceStateName = state.stateName,
                                                                sourceFieldId = field.fieldId,
                                                                sourceFieldName = field.fieldName,
                                                            )
                                                        if (sourceSettings is IBooleanTrigger) {
                                                            sourceFieldInfo =
                                                                SourceButtonFieldInfoViewState(
                                                                    sourceBooleanValue = sourceSettings.value,
                                                                )
                                                        }
                                                    }
                                                }
                                                is MultipleChoiceDeviceField -> {
                                                    if (field.fieldId == sourceData.fieldId) {
                                                        sourceAddressViewState =
                                                            TriggerView_AddressViewState(
                                                                sourceDeviceId = device.deviceId,
                                                                sourceDeviceName = device.deviceName,
                                                                sourceGroupId = complexGroup.complexGroupId,
                                                                sourceGroupName = complexGroup.groupName,
                                                                sourceStateId = state.stateId,
                                                                sourceStateName = state.stateName,
                                                                sourceFieldId = field.fieldId,
                                                                sourceFieldName = field.fieldName,
                                                            )
                                                        if (sourceSettings is IMCTrigger) {
                                                            val MCstate =
                                                                field.choices[sourceSettings.value]
                                                            sourceFieldInfo =
                                                                SourceMCFieldInfoViewState(
                                                                    fieldMCType = sourceSettings.type,
                                                                    sourceFieldMCStateId = sourceSettings.value,
                                                                    sourceFieldMCStateName = MCstate,
                                                                )
                                                        }
                                                    }
                                                }
                                                is RGBDeviceField -> {
                                                    if (field.fieldId == sourceData.fieldId) {
                                                        sourceAddressViewState =
                                                            TriggerView_AddressViewState(
                                                                sourceDeviceId = device.deviceId,
                                                                sourceDeviceName = device.deviceName,
                                                                sourceGroupId = complexGroup.complexGroupId,
                                                                sourceGroupName = complexGroup.groupName,
                                                                sourceStateId = state.stateId,
                                                                sourceStateName = state.stateName,
                                                                sourceFieldId = field.fieldId,
                                                                sourceFieldName = field.fieldName,
                                                            )
                                                        if (sourceSettings is IRGBTrigger) {
                                                            sourceFieldInfo =
                                                                SourceRGBFieldInfoViewState(
                                                                    fieldRGBType = sourceSettings.type,
                                                                    fieldRGBContext = sourceSettings.contextType,
                                                                    sourceFieldRGBValue = sourceSettings.value,
                                                                    sourceFieldRGBSecondValue = sourceSettings.second_value,
                                                                )
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                    ETriggerSourceType.TimeTrigger -> {
                        if (sourceData is ITriggerTimeSourceData) {
                            Log.i("triggerMap", "source data is time")
                            var lastFired = ""
                            if (sourceData.lastRunTimestamp != null) {
                                try {
                                    lastFired =
                                        getLocalTimeForDisplayFromISO(sourceData.lastRunTimestamp)
                                } catch (_: Throwable) {

                                }
                            }
                            timeTriggerInfo = TimeTriggerInfoViewState(
                                type = sourceData.type,
                                timeStamp = getLocalTimeForDisplayFromISO(sourceData.firstTimeStamp),
                                lastFired = lastFired
                            )
                            Log.i("triggerMap", "end time data")

                        }
                    }
                }
                Log.i("triggerMap", "end source data")

                var responseAddressViewState: TriggerView_AddressViewState? = null
                var responseFieldInfoViewState: ResponseFieldInfoViewState? = null
                var emailInfoViewState: EmailInfoViewState? = null
                var mobileNotificationInfoViewState: MobileNotificationInfoViewState? = null

                val responseData = trigger.responseSettings

                when (trigger.responseType) {
                    ETriggerResponseType.SettingValue_fieldInGroup -> {

                        if (responseData is ITriggerSettingValueResponse_fieldInGroup) {
                            val device = devices.find { it.deviceId == responseData.deviceId }
                            if (device != null) {
                                val group =
                                    device.groups.find { it.groupId == responseData.groupId }
                                if (group != null) {
                                    val fields = group.fields
                                    for (field in fields) {
                                        when (field) {
                                            is NumericDeviceField -> {
                                                if (field.fieldId == responseData.fieldId) {
                                                    responseAddressViewState =
                                                        TriggerView_AddressViewState(
                                                            sourceDeviceId = device.deviceId,
                                                            sourceDeviceName = device.deviceName,
                                                            sourceGroupId = group.groupId,
                                                            sourceGroupName = group.groupName,
                                                            sourceFieldId = field.fieldId,
                                                            sourceFieldName = field.fieldName,
                                                        )
                                                    responseFieldInfoViewState =
                                                        ResponseNumericFieldInfoViewState(
                                                            fieldValue = responseData.value as Float,
                                                            prefix = field.prefix,
                                                            sufix = field.sufix,
                                                        )
                                                }
                                            }
                                            is TextDeviceField -> {
                                                if (field.fieldId == responseData.fieldId) {
                                                    responseAddressViewState =
                                                        TriggerView_AddressViewState(
                                                            sourceDeviceId = device.deviceId,
                                                            sourceDeviceName = device.deviceName,
                                                            sourceGroupId = group.groupId,
                                                            sourceGroupName = group.groupName,
                                                            sourceFieldId = field.fieldId,
                                                            sourceFieldName = field.fieldName,
                                                        )
                                                    responseFieldInfoViewState =
                                                        ResponseTextFieldInfoViewState(
                                                            fieldValue = responseData.value as String,
                                                        )
                                                }
                                            }
                                            is ButtonDeviceField -> {
                                                if (field.fieldId == responseData.fieldId) {
                                                    responseAddressViewState =
                                                        TriggerView_AddressViewState(
                                                            sourceDeviceId = device.deviceId,
                                                            sourceDeviceName = device.deviceName,
                                                            sourceGroupId = group.groupId,
                                                            sourceGroupName = group.groupName,
                                                            sourceFieldId = field.fieldId,
                                                            sourceFieldName = field.fieldName,
                                                        )
                                                    responseFieldInfoViewState =
                                                        ResponseButtonFieldInfoViewState(
                                                            fieldValue = responseData.value as Boolean,
                                                        )
                                                }
                                            }
                                            is MultipleChoiceDeviceField -> {
                                                if (field.fieldId == responseData.fieldId) {
                                                    responseAddressViewState =
                                                        TriggerView_AddressViewState(
                                                            sourceDeviceId = device.deviceId,
                                                            sourceDeviceName = device.deviceName,
                                                            sourceGroupId = group.groupId,
                                                            sourceGroupName = group.groupName,
                                                            sourceFieldId = field.fieldId,
                                                            sourceFieldName = field.fieldName,
                                                        )
                                                    val MCstate =
                                                        field.choices[responseData.value as Int]
                                                    responseFieldInfoViewState =
                                                        ResponseMCFieldInfoViewState(
                                                            stateId = responseData.value,
                                                            stateName = MCstate,
                                                        )
                                                }
                                            }
                                            is RGBDeviceField -> {
                                                if (field.fieldId == responseData.fieldId) {
                                                    responseAddressViewState =
                                                        TriggerView_AddressViewState(
                                                            sourceDeviceId = device.deviceId,
                                                            sourceDeviceName = device.deviceName,
                                                            sourceGroupId = group.groupId,
                                                            sourceGroupName = group.groupName,
                                                            sourceFieldId = field.fieldId,
                                                            sourceFieldName = field.fieldName,
                                                        )
                                                    responseFieldInfoViewState =
                                                        ResponseRGBFieldInfoViewState(
                                                            fieldValue = responseData.value as Int,
                                                            context = responseData.rgbContext!!,
                                                        )
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                    ETriggerResponseType.SettingValue_fieldInComplexGroup -> {
                        if (responseData is ITriggerSettingsValueResponse_fieldInComplexGroup) {
                            val device = devices.find { it.deviceId == responseData.deviceId }
                            if (device != null) {
                                val complexGroup =
                                    device.complexGroups.find { it.complexGroupId == responseData.complexGroupId }
                                if (complexGroup != null) {
                                    val state =
                                        complexGroup.states.find { it.stateId == responseData.complexGroupState }
                                    if (state != null) {
                                        val fields = state.fields
                                        for (field in fields) {
                                            when (field) {
                                                is NumericDeviceField -> {
                                                    if (field.fieldId == responseData.fieldId) {
                                                        responseAddressViewState =
                                                            TriggerView_AddressViewState(
                                                                sourceDeviceId = device.deviceId,
                                                                sourceDeviceName = device.deviceName,
                                                                sourceGroupId = complexGroup.complexGroupId,
                                                                sourceGroupName = complexGroup.groupName,
                                                                sourceStateId = state.stateId,
                                                                sourceStateName = state.stateName,
                                                                sourceFieldId = field.fieldId,
                                                                sourceFieldName = field.fieldName,
                                                            )
                                                        responseFieldInfoViewState =
                                                            ResponseNumericFieldInfoViewState(
                                                                fieldValue = responseData.value as Float,
                                                                prefix = field.prefix,
                                                                sufix = field.sufix,
                                                            )
                                                    }
                                                }
                                                is TextDeviceField -> {
                                                    if (field.fieldId == responseData.fieldId) {
                                                        responseAddressViewState =
                                                            TriggerView_AddressViewState(
                                                                sourceDeviceId = device.deviceId,
                                                                sourceDeviceName = device.deviceName,
                                                                sourceGroupId = complexGroup.complexGroupId,
                                                                sourceGroupName = complexGroup.groupName,
                                                                sourceStateId = state.stateId,
                                                                sourceStateName = state.stateName,
                                                                sourceFieldId = field.fieldId,
                                                                sourceFieldName = field.fieldName,
                                                            )
                                                        responseFieldInfoViewState =
                                                            ResponseTextFieldInfoViewState(
                                                                fieldValue = responseData.value as String,
                                                            )
                                                    }
                                                }
                                                is ButtonDeviceField -> {
                                                    if (field.fieldId == responseData.fieldId) {
                                                        responseAddressViewState =
                                                            TriggerView_AddressViewState(
                                                                sourceDeviceId = device.deviceId,
                                                                sourceDeviceName = device.deviceName,
                                                                sourceGroupId = complexGroup.complexGroupId,
                                                                sourceGroupName = complexGroup.groupName,
                                                                sourceStateId = state.stateId,
                                                                sourceStateName = state.stateName,
                                                                sourceFieldId = field.fieldId,
                                                                sourceFieldName = field.fieldName,
                                                            )
                                                        responseFieldInfoViewState =
                                                            ResponseButtonFieldInfoViewState(
                                                                fieldValue = responseData.value as Boolean,
                                                            )
                                                    }
                                                }
                                                is MultipleChoiceDeviceField -> {
                                                    if (field.fieldId == responseData.fieldId) {
                                                        responseAddressViewState =
                                                            TriggerView_AddressViewState(
                                                                sourceDeviceId = device.deviceId,
                                                                sourceDeviceName = device.deviceName,
                                                                sourceGroupId = complexGroup.complexGroupId,
                                                                sourceGroupName = complexGroup.groupName,
                                                                sourceStateId = state.stateId,
                                                                sourceStateName = state.stateName,
                                                                sourceFieldId = field.fieldId,
                                                                sourceFieldName = field.fieldName,
                                                            )
                                                        val MCstate =
                                                            field.choices[responseData.value as Int]
                                                        responseFieldInfoViewState =
                                                            ResponseMCFieldInfoViewState(
                                                                stateId = responseData.value,
                                                                stateName = MCstate,
                                                            )
                                                    }
                                                }
                                                is RGBDeviceField -> {
                                                    if (field.fieldId == responseData.fieldId) {
                                                        responseAddressViewState =
                                                            TriggerView_AddressViewState(
                                                                sourceDeviceId = device.deviceId,
                                                                sourceDeviceName = device.deviceName,
                                                                sourceGroupId = complexGroup.complexGroupId,
                                                                sourceGroupName = complexGroup.groupName,
                                                                sourceStateId = state.stateId,
                                                                sourceStateName = state.stateName,
                                                                sourceFieldId = field.fieldId,
                                                                sourceFieldName = field.fieldName,
                                                            )
                                                        responseFieldInfoViewState =
                                                            ResponseRGBFieldInfoViewState(
                                                                fieldValue = responseData.value as Int,
                                                                context = responseData.rgbContext!!,
                                                            )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                    ETriggerResponseType.Email -> {
                        if (responseData is ITriggerEmailResponse) {
                            emailInfoViewState = EmailInfoViewState(
                                subject = responseData.emailSubject,
                                content = responseData.emailText,
                            )
                        }
                    }
                    ETriggerResponseType.MobileNotification -> {
                        if (responseData is ITriggerMobileNotificationResponse) {
                            mobileNotificationInfoViewState = MobileNotificationInfoViewState(
                                title = responseData.notificationTitle,
                                content = responseData.notificationText,
                            )
                        }
                    }
                }

                TriggerItemViewState(
                    id = trigger.id,
                    name = trigger.name,
                    sourceType = trigger.sourceType,
                    sourceAddressViewState = sourceAddressViewState,
                    sourceFieldInfo = sourceFieldInfo,
                    timeTriggerInfo = timeTriggerInfo,

                    responseType = trigger.responseType,
                    responseAddressViewState = responseAddressViewState,
                    responseFieldInfoViewState = responseFieldInfoViewState,
                    emailInfoViewState = emailInfoViewState,
                    mobileNotificationViewState = mobileNotificationInfoViewState,
                )
            }
        )
    }
}
