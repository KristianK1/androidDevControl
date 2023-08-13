package hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.triggerViewComponents

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import hr.kristiankliskovic.devcontrol.R
import hr.kristiankliskovic.devcontrol.model.*
import hr.kristiankliskovic.devcontrol.ui.components.triggerComponents.sourceComponents.TriggerItemLine

sealed class SourceFieldInfoViewState

data class SourceNumericFieldInfoViewState(
    val fieldNumericType: Int,
    val sourceFieldNumericValue: Float,
    val sourceFieldNumericSecondValue: Float?,
) : SourceFieldInfoViewState()

data class SourceTextFieldInfoViewState(
    val sourceTextValue: String,
    val fieldTextType: Int,
) : SourceFieldInfoViewState()

data class SourceButtonFieldInfoViewState(
    val sourceBooleanValue: Boolean,
) : SourceFieldInfoViewState()

data class SourceMCFieldInfoViewState(
    val fieldMCType: Int?,
    val sourceFieldMCStateId: Int?,
    val sourceFieldMCStateName: String?,
) : SourceFieldInfoViewState()

data class SourceRGBFieldInfoViewState(
    val fieldRGBType: Int,
    val fieldRGBContext: Int,
    val sourceFieldRGBValue: Int?,
    val sourceFieldRGBSecondValue: Int?,
) : SourceFieldInfoViewState()

@Composable
fun SourceFieldInfo(
    viewState: SourceFieldInfoViewState,
) {
    when (viewState) {
        is SourceNumericFieldInfoViewState -> {
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerSourceFieldType_propertyName),
                propertyValue = stringResource(id = R.string.getAllTriggersScreen_triggerFieldType_numeric)
            )
            when (viewState.fieldNumericType) {
                ENumericTriggerType.Bigger.ordinal -> {
                    TriggerItemLine(
                        propertyName = stringResource(id = R.string.getAllTriggersScreen_numericTriggerType_biggerPropertyName),
                        propertyValue = "${viewState.sourceFieldNumericValue}"
                    )

                }
                ENumericTriggerType.Smaller.ordinal -> {
                    TriggerItemLine(
                        propertyName = stringResource(id = R.string.getAllTriggersScreen_numericTriggerType_smallerPropertyName),
                        propertyValue = "${viewState.sourceFieldNumericValue}"
                    )

                }
                ENumericTriggerType.Equal.ordinal -> {
                    TriggerItemLine(
                        propertyName = stringResource(id = R.string.getAllTriggersScreen_numericTriggerType_equalPropertyName),
                        propertyValue = "${viewState.sourceFieldNumericValue}"
                    )

                }
                ENumericTriggerType.Inbetween.ordinal -> {
                    TriggerItemLine(
                        propertyName = stringResource(id = R.string.getAllTriggersScreen_numericTriggerType_betweenPropertyName),
                        propertyValue = "[${viewState.sourceFieldNumericValue}, ${viewState.sourceFieldNumericSecondValue}]"
                    )

                }
                ENumericTriggerType.NotInBetween.ordinal -> {
                    TriggerItemLine(
                        propertyName = stringResource(id = R.string.getAllTriggersScreen_numericTriggerType_notBetweenPropertyName),
                        propertyValue = "[${viewState.sourceFieldNumericValue}, ${viewState.sourceFieldNumericSecondValue}]"
                    )
                }
            }
        }
        is SourceTextFieldInfoViewState -> {
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerSourceFieldType_propertyName),
                propertyValue = stringResource(id = R.string.getAllTriggersScreen_triggerFieldType_text)
            )
            when (viewState.fieldTextType) {
                ETextTriggerType.StartsWith.ordinal -> {
                    TriggerItemLine(
                        propertyName = stringResource(id = R.string.getAllTriggersScreen_textTriggerType_startWithPropertyName),
                        propertyValue = "\"${viewState.sourceTextValue}\""
                    )
                }
                ETextTriggerType.EndsWith.ordinal -> {
                    TriggerItemLine(
                        propertyName = stringResource(id = R.string.getAllTriggersScreen_textTriggerType_endWithPropertyName),
                        propertyValue = "\"${viewState.sourceTextValue}\""
                    )
                }
                ETextTriggerType.Contains.ordinal -> {
                    TriggerItemLine(
                        propertyName = stringResource(id = R.string.getAllTriggersScreen_textTriggerType_containsPropertyName),
                        propertyValue = "\"${viewState.sourceTextValue}\""
                    )
                }
                ETextTriggerType.IsEqualTo.ordinal -> {
                    TriggerItemLine(
                        propertyName = stringResource(id = R.string.getAllTriggersScreen_textTriggerType_equalPropertyName),
                        propertyValue = "\"${viewState.sourceTextValue}\""
                    )
                }
                ETextTriggerType.IsNotEqualTo.ordinal -> {
                    TriggerItemLine(
                        propertyName = stringResource(id = R.string.getAllTriggersScreen_textTriggerType_notEqualPropertyName),
                        propertyValue = "\"${viewState.sourceTextValue}\""
                    )
                }
            }

        }
        is SourceButtonFieldInfoViewState -> {
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerSourceFieldType_propertyName),
                propertyValue = stringResource(id = R.string.getAllTriggersScreen_triggerFieldType_button)
            )
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_ButtonTriggerType_equalPropertyName),
                propertyValue = stringResource(id = if (viewState.sourceBooleanValue) R.string.buttonTriggerType_true else R.string.buttonTriggerType_false)
            )
        }
        is SourceMCFieldInfoViewState -> {
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerSourceFieldType_propertyName),
                propertyValue = stringResource(id = R.string.getAllTriggersScreen_triggerFieldType_MC)
            )
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_MCTriggerType_equalPropertyName),
                propertyValue = "${viewState.sourceFieldMCStateName} (id: ${viewState.sourceFieldMCStateId})"
            )
        }

        is SourceRGBFieldInfoViewState -> {
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_triggerSourceFieldType_propertyName),
                propertyValue = stringResource(id = R.string.getAllTriggersScreen_triggerFieldType_RGB)
            )
            TriggerItemLine(
                propertyName = stringResource(id = R.string.getAllTriggersScreen_rgbTriggerType_equalPropertyName),
                propertyValue = stringResource(id = if (viewState.fieldRGBContext == ERGBTriggerType_context.R.ordinal) R.string.rgbTriggerContext_R else if (viewState.fieldRGBContext == ERGBTriggerType_context.G.ordinal) R.string.rgbTriggerContext_G else R.string.rgbTriggerContext_B)
            )
            when (viewState.fieldRGBType) {
                ERGBTriggerType_numeric.Bigger.ordinal -> {
                    TriggerItemLine(
                        propertyName = stringResource(id = R.string.getAllTriggersScreen_numericTriggerType_biggerPropertyName),
                        propertyValue = "${viewState.sourceFieldRGBValue}"
                    )

                }
                ERGBTriggerType_numeric.Smaller.ordinal -> {
                    TriggerItemLine(
                        propertyName = stringResource(id = R.string.getAllTriggersScreen_numericTriggerType_smallerPropertyName),
                        propertyValue = "${viewState.sourceFieldRGBValue}"
                    )

                }
                ERGBTriggerType_numeric.Equal.ordinal -> {
                    TriggerItemLine(
                        propertyName = stringResource(id = R.string.getAllTriggersScreen_numericTriggerType_equalPropertyName),
                        propertyValue = "${viewState.sourceFieldRGBValue}"
                    )

                }
                ERGBTriggerType_numeric.Inbetween.ordinal -> {
                    TriggerItemLine(
                        propertyName = stringResource(id = R.string.getAllTriggersScreen_numericTriggerType_betweenPropertyName),
                        propertyValue = "[${viewState.sourceFieldRGBValue}, ${viewState.sourceFieldRGBSecondValue}]"
                    )

                }
                ERGBTriggerType_numeric.NotInBetween.ordinal -> {
                    TriggerItemLine(
                        propertyName = stringResource(id = R.string.getAllTriggersScreen_numericTriggerType_notBetweenPropertyName),
                        propertyValue = "[${viewState.sourceFieldRGBValue}, ${viewState.sourceFieldRGBSecondValue}]"
                    )
                }
            }

        }
    }
}
