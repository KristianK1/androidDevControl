package hr.kristiankliskovic.devcontrol.data.repository.authToken

interface AuthTokenRepository {
    fun getAuthToken(): String?
    fun saveAuthToken(token: String)
    fun deleteAuthToken()
}
