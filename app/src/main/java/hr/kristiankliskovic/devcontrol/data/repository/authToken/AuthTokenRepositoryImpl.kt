package hr.kristiankliskovic.devcontrol.data.repository.authToken

import android.util.Log
import hr.kristiankliskovic.devcontrol.data.encrpytedPreferences.PreferencesManager

private const val PREFS_KEY_AUTH_TOKEN = "authToken"

class AuthTokenRepositoryImpl(
    private val preferencesManager: PreferencesManager
): AuthTokenRepository {

    override fun getAuthToken(): String? {
        Log.i("login","AuthTokenRepositoryImpl_getToken_start")
        val x = preferencesManager.getString(PREFS_KEY_AUTH_TOKEN)
        Log.i("login","AuthTokenRepositoryImpl_getToken_end")
        Log.i("login", x ?: "NOtoken")
        return x
    }

    override fun saveAuthToken(token: String) {
        Log.i("login","AuthTokenRepositoryImpl_saveToken_start")
        preferencesManager.saveString(PREFS_KEY_AUTH_TOKEN, token)
        Log.i("login","AuthTokenRepositoryImpl_saveToken_end")
    }

    override fun deleteAuthToken() {
        Log.i("login","AuthTokenRepositoryImpl_deleteToken_start")
        preferencesManager.deleteString(PREFS_KEY_AUTH_TOKEN)
        Log.i("login","AuthTokenRepositoryImpl_deleteToken_end")
    }
}
