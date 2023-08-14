package hr.kristiankliskovic.devcontrol.data.network.customDeserializers.triggers

import android.util.Log
import com.google.gson.*
import hr.kristiankliskovic.devcontrol.model.*
import java.lang.reflect.Type

class TriggerSourceDataDeserializer(private val sourceType: ETriggerSourceType) :
    JsonDeserializer<TriggerSourceData> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): TriggerSourceData {
        return when (sourceType) {
            ETriggerSourceType.FieldInGroup -> {
                Log.i("ALLT_gson", "ETriggerSourceType.FieldInGroup")
                val x: TriggerSourceData = context!!.deserialize(json,
                    ITriggerSourceAdress_fieldInGroup::class.java)
                x
            }
            ETriggerSourceType.FieldInComplexGroup -> {
                Log.i("ALLT_gson", "ETriggerSourceType.FieldInComplexGroup")
                context!!.deserialize(json,
                    ITriggerSourceAdress_fieldInComplexGroup::class.java)
            }
            ETriggerSourceType.TimeTrigger -> {
                Log.i("ALLT_gson", "ETriggerSourceType.TimeTrigger")
                context!!.deserialize(json,
                    ITriggerTimeSourceData::class.java)
            }
        }
    }
}

class TriggerResponseSettingsDeserializer(private val responseType: ETriggerResponseType) :
    JsonDeserializer<TriggerResponse> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): TriggerResponse {
        return when (responseType) {
            ETriggerResponseType.Email -> {
                Log.i("ALLT_gson", "responseEmail")
                context!!.deserialize(json,
                    ITriggerEmailResponse::class.java)
            }
            ETriggerResponseType.MobileNotification -> {
                Log.i("ALLT_gson", "responseMobile")
                context!!.deserialize(json,
                    ITriggerMobileNotificationResponse::class.java)
            }
            ETriggerResponseType.SettingValue_fieldInGroup -> {
                Log.i("ALLT_gson", "responseFG")
                context!!.deserialize(json,
                    ITriggerSettingValueResponse_fieldInGroup::class.java)
            }
            ETriggerResponseType.SettingValue_fieldInComplexGroup -> {
                Log.i("ALLT_gson", "responseFCG")
                context!!.deserialize(json,
                    ITriggerSettingsValueResponse_fieldInComplexGroup::class.java)
            }
        }
    }
}

class TriggerSettingsDeserializer(private val fieldType: String?) :
    JsonDeserializer<TriggerSettings> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): TriggerSettings? {
        if (fieldType == null) {
            return null
        }

        return when (fieldType) {
            "numeric" -> {
                Log.i("ALLT_gson", "numeric")
                context!!.deserialize(json, INumericTrigger::class.java)
            }
            "text" -> {
                Log.i("ALLT_gson", "text")
                context!!.deserialize(json, ITextTrigger::class.java)
            }
            "button" -> {
                Log.i("ALLT_gson", "button")
                context!!.deserialize(json, IBooleanTrigger::class.java)
            }
            "RGB" -> {
                Log.i("ALLT_gson", "RGB")
                context!!.deserialize(json, IRGBTrigger::class.java)
            }
            "multipleChoice" -> {
                Log.i("ALLT_gson", "multipleChoice")
                context!!.deserialize(json, IMCTrigger::class.java)
            }
            else -> {
                null
            }
        }
    }
}


class EnumDeserializer(private val enumClass: Class<out Enum<*>>) : JsonDeserializer<Enum<*>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): Enum<*> {
        val ordinal = json.asInt
        return enumClass.enumConstants[ordinal]
    }
}

class EnumSerializer(private val enumClass: Class<out Enum<*>>) : JsonSerializer<Enum<*>> {
    override fun serialize(
        src: Enum<*>,
        typeOfSrc: Type,
        context: JsonSerializationContext,
    ): JsonElement {
        return JsonPrimitive(src.ordinal)
    }
}

fun getBasicGson(): GsonBuilder {
    val gsonBuilder = GsonBuilder()

    gsonBuilder.registerTypeAdapter(ENumericTriggerType::class.java,
        EnumDeserializer(ENumericTriggerType::class.java))
    gsonBuilder.registerTypeAdapter(ETextTriggerType::class.java,
        EnumDeserializer(ETextTriggerType::class.java))
    gsonBuilder.registerTypeAdapter(EMCTriggerType::class.java,
        EnumDeserializer(EMCTriggerType::class.java))
    gsonBuilder.registerTypeAdapter(ERGBTriggerType_numeric::class.java,
        EnumDeserializer(ERGBTriggerType_numeric::class.java))
    gsonBuilder.registerTypeAdapter(ERGBTriggerType_context::class.java,
        EnumDeserializer(ERGBTriggerType_context::class.java))
    gsonBuilder.registerTypeAdapter(ETriggerSourceType::class.java,
        EnumDeserializer(ETriggerSourceType::class.java))
    gsonBuilder.registerTypeAdapter(ETriggerResponseType::class.java,
        EnumDeserializer(ETriggerResponseType::class.java))
    gsonBuilder.registerTypeAdapter(ETriggerTimeType::class.java,
        EnumDeserializer(ETriggerTimeType::class.java))

    gsonBuilder.registerTypeAdapter(ENumericTriggerType::class.java,
        EnumSerializer(ENumericTriggerType::class.java))
    gsonBuilder.registerTypeAdapter(ETextTriggerType::class.java,
        EnumSerializer(ETextTriggerType::class.java))
    gsonBuilder.registerTypeAdapter(EMCTriggerType::class.java,
        EnumSerializer(EMCTriggerType::class.java))
    gsonBuilder.registerTypeAdapter(ERGBTriggerType_numeric::class.java,
        EnumSerializer(ERGBTriggerType_numeric::class.java))
    gsonBuilder.registerTypeAdapter(ERGBTriggerType_context::class.java,
        EnumSerializer(ERGBTriggerType_context::class.java))
    gsonBuilder.registerTypeAdapter(ETriggerSourceType::class.java,
        EnumSerializer(ETriggerSourceType::class.java))
    gsonBuilder.registerTypeAdapter(ETriggerResponseType::class.java,
        EnumSerializer(ETriggerResponseType::class.java))
    gsonBuilder.registerTypeAdapter(ETriggerTimeType::class.java,
        EnumSerializer(ETriggerTimeType::class.java))

    return gsonBuilder
}
