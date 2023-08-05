package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.google.gson.Gson
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.components.otherComponents.RadioButtonRow
import hr.kristiankliskovic.devcontrol.R

@Composable
fun TriggerFieldDataSettings(
    field: BasicDeviceField,
    setTriggerSettings: (TriggerSettings) -> Unit,
) {
    Column {
        var triggerSettings by remember { mutableStateOf<TriggerSettings?>(null) }
        when (field) {
            is NumericDeviceField -> {
                var type by remember { mutableStateOf(ENumericTriggerType.Bigger) }
                var value1 by remember { mutableStateOf<Float?>(null) }
                var value2 by remember { mutableStateOf<Float?>(null) }

                RadioButtonRow(
                    selected = type == ENumericTriggerType.Bigger,
                    text = stringResource(id = R.string.numericTriggerType_bigger),
                    onClick = {
                        type = ENumericTriggerType.Bigger
                        if (value1 != null) {
                            triggerSettings = INumericTrigger(
                                type = type,
                                value = value1!!,
                            )
                            setTriggerSettings(triggerSettings!!)
                        }
                    }
                )
                RadioButtonRow(
                    selected = type == ENumericTriggerType.Smaller,
                    text = stringResource(id = R.string.numericTriggerType_smaller),
                    onClick = {
                        type = ENumericTriggerType.Smaller
                        if (value1 != null) {
                            triggerSettings = INumericTrigger(
                                type = type,
                                value = value1!!,
                            )
                            setTriggerSettings(triggerSettings!!)
                        }
                    }
                )
                RadioButtonRow(
                    selected = type == ENumericTriggerType.Equal,
                    text = stringResource(id = R.string.numericTriggerType_equal),
                    onClick = {
                        type = ENumericTriggerType.Equal
                        if (value1 != null) {
                            triggerSettings = INumericTrigger(
                                type = type,
                                value = value1!!,
                            )
                            setTriggerSettings(triggerSettings!!)
                        }
                    }
                )
                RadioButtonRow(
                    selected = type == ENumericTriggerType.Inbetween,
                    text = stringResource(id = R.string.numericTriggerType_between),
                    onClick = {
                        type = ENumericTriggerType.Inbetween
                        if (value1 != null && value2 != null) {
                            triggerSettings = INumericTrigger(
                                type = type,
                                value = value1!!,
                                second_value = value2,
                            )
                            setTriggerSettings(triggerSettings!!)
                        }
                    }
                )
                RadioButtonRow(
                    selected = type == ENumericTriggerType.NotInBetween,
                    text = stringResource(id = R.string.numericTriggerType_NotBetween),
                    onClick = {
                        type = ENumericTriggerType.NotInBetween
                        if (value1 != null && value2 != null) {
                            triggerSettings = INumericTrigger(
                                type = type,
                                value = value1!!,
                                second_value = value2,
                            )
                            setTriggerSettings(triggerSettings!!)
                        }
                    }
                )
                Row {
                    ChooseNumericValuePopup(
                        minValue = field.minValue,
                        maxValue = field.maxValue,
                        step = field.valueStep,
                        prefix = field.prefix,
                        sufix = field.sufix,
                        chosen = {
                            value1 = it
                            if (
                                type == ENumericTriggerType.Bigger ||
                                type == ENumericTriggerType.Smaller ||
                                type == ENumericTriggerType.Equal ||
                                value1 != null ||
                                value2 != null
                            ) {
                                Log.i("numericFset", "type$type")
                                Log.i("numericFset", "v1__${value1!!}")
                                Log.i("numericFset", "v2__$value2")
                                triggerSettings = INumericTrigger(
                                    value = value1!!,
                                    second_value = value2,
                                    type = type,
                                )
                                Log.i("numericFset", Gson().toJson(triggerSettings))
                                setTriggerSettings(triggerSettings!!)
                            }
                        }
                    )
                    if (type == ENumericTriggerType.Inbetween || type == ENumericTriggerType.NotInBetween) {
                        ChooseNumericValuePopup(
                            minValue = field.minValue,
                            maxValue = field.maxValue,
                            step = field.valueStep,
                            prefix = field.prefix,
                            sufix = field.sufix,
                            chosen = {
                                value2 = it
                                if (
                                    value1 != null
                                ) {
                                    Log.i("numericFset", "1")
                                    triggerSettings = INumericTrigger(
                                        value = value1!!,
                                        second_value = value2!!,
                                        type = type
                                    )
                                    Log.i("numericFset", "2")
                                    setTriggerSettings(triggerSettings!!)
                                    Log.i("numericFset", "3")
                                }
                            }
                        )
                    }
                }
            }
            is ButtonDeviceField -> {
                var type by remember { mutableStateOf(true) }

                RadioButtonRow(
                    selected = true,
                    text = stringResource(id = R.string.buttonTriggerType_true),
                    onClick = {
                        type = true
                        triggerSettings = IBooleanTrigger(
                            value = true
                        )
                        setTriggerSettings(triggerSettings!!)
                    }
                )
                RadioButtonRow(
                    selected = false,
                    text = stringResource(id = R.string.buttonTriggerType_false),
                    onClick = {
                        type = false
                        triggerSettings = IBooleanTrigger(
                            value = false
                        )
                        setTriggerSettings(triggerSettings!!)
                    }
                )
            }
            is MultipleChoiceDeviceField -> {
                var type by remember { mutableStateOf(EMCTriggerType.IsEqualTo) }
                RadioButtonRow(
                    selected = type == EMCTriggerType.IsEqualTo,
                    text = stringResource(id = R.string.MCTriggerType_equal),
                    onClick = {
                        type = EMCTriggerType.IsEqualTo
                        triggerSettings = IBooleanTrigger(
                            value = true
                        )
                        setTriggerSettings(triggerSettings!!)
                    }
                )
                RadioButtonRow(
                    selected = type == EMCTriggerType.IsEqualTo,
                    text = stringResource(id = R.string.MCTriggerType_notEqual),
                    onClick = {
                        type = EMCTriggerType.IsNotEqualTo
                        triggerSettings = IBooleanTrigger(
                            value = false
                        )
                        setTriggerSettings(triggerSettings!!)
                    }
                )
                ChooseTextValuePopup(
                    values = field.choices,
                    chosen = {
                        triggerSettings = IMCTrigger(
                            value = it,
                            type = type
                        )
                        setTriggerSettings(triggerSettings!!)
                    }
                )
            }
            is RGBDeviceField -> {

            }
            is TextDeviceField -> {

            }
        }
    }
}
