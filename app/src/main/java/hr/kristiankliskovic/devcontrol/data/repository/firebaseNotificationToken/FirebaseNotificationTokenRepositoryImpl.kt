package hr.kristiankliskovic.devcontrol.data.repository.firebaseNotificationToken

import hr.kristiankliskovic.devcontrol.data.encrpytedPreferences.PreferencesManager

private const val PREFS_KEY_FIREBASE_NOTIFICATION_TOKEN = "firebaseNotificationToken"

class FirebaseNotificationTokenRepositoryImpl(
    private val preferencesManager: PreferencesManager,
) : FirebaseNotificationTokenRepository {

    override fun getToken(): String? {
        return preferencesManager.getString(PREFS_KEY_FIREBASE_NOTIFICATION_TOKEN)
    }

    override fun saveToken(token: String) {
        preferencesManager.saveString(PREFS_KEY_FIREBASE_NOTIFICATION_TOKEN, token)
    }

    override fun deleteToken() {
        preferencesManager.deleteString(PREFS_KEY_FIREBASE_NOTIFICATION_TOKEN)
    }
}
