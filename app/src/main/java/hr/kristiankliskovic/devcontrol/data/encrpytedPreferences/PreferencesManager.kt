package hr.kristiankliskovic.devcontrol.data.encrpytedPreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

const val PREFS_FILE = "MyPreferences"

class PreferencesManager(
    context: Context,
) {
    private val sharedPreferences: SharedPreferences

    init {
        val masterKey: MasterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        sharedPreferences = EncryptedSharedPreferences.create(
            context,
            PREFS_FILE,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun saveString(key: String, token: String) {
        sharedPreferences.edit().putString(key, token).apply()
    }

    fun deleteString(key: String) {
        sharedPreferences.edit().putString(key, null).apply()
    }
}
