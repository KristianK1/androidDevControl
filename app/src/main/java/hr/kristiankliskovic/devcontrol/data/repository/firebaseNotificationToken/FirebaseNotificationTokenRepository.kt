package hr.kristiankliskovic.devcontrol.data.repository.firebaseNotificationToken

interface FirebaseNotificationTokenRepository {
    fun getToken(): String?
    fun saveToken(token: String)
    fun deleteToken()
}
