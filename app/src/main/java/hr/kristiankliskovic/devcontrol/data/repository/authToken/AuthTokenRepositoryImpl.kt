package hr.kristiankliskovic.devcontrol.data.repository.authToken

import hr.kristiankliskovic.devcontrol.data.encrpytedPreferences.PreferencesManager

private const val PREFS_KEY_AUTH_TOKEN = "authToken"

class AuthTokenRepositoryImpl(
    private val preferencesManager: PreferencesManager
): AuthTokenRepository {

    override fun getAuthToken(): String? {
        return preferencesManager.getString(PREFS_KEY_AUTH_TOKEN)
    }

    override fun saveAuthToken(token: String) {
        preferencesManager.saveString(PREFS_KEY_AUTH_TOKEN, token)
    }

    override fun deleteAuthToken() {
        preferencesManager.deleteString(PREFS_KEY_AUTH_TOKEN)
    }
}
