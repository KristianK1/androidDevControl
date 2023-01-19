package hr.kristiankliskovic.devcontrol.data.repository.authToken

import android.util.Log
import hr.kristiankliskovic.devcontrol.data.encrpytedPreferences.PreferencesManager

private const val PREFS_KEY_AUTH_TOKEN = "authToken"

class AuthTokenRepositoryImpl(
    private val preferencesManager: PreferencesManager
): AuthTokenRepository {

    override fun getAuthToken(): String? {
        val x = preferencesManager.getString(PREFS_KEY_AUTH_TOKEN)
        return x
    }

    override fun saveAuthToken(token: String) {
        preferencesManager.saveString(PREFS_KEY_AUTH_TOKEN, token)
    }

    override fun deleteAuthToken() {
        preferencesManager.deleteString(PREFS_KEY_AUTH_TOKEN)
    }
}
