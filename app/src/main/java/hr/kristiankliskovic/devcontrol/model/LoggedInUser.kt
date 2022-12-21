package hr.kristiankliskovic.devcontrol.model

data class LoggedInUser(
    val userId: Int,
    val username: String,
    val token: String,
)
