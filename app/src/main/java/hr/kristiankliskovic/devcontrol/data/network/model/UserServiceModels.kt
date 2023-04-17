package hr.kristiankliskovic.devcontrol.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginByCredsRequest(
    val username: String,
    val password: String,
)

@Serializable
data class LoginByTokenRequest(
    val authToken: String
)

@Serializable
data class LoginResponse(
    val id: Int,
    val authToken: String,
    val username: String
)

@Serializable
data class LogoutRequest(
    val authToken: String,
    val logoutOtherSessions: Boolean,
)

@Serializable
data class DeleteUserRequest(
    val authToken: String
)

@Serializable
data class ChangePasswordRequest(
    val userId: Int,
    val oldPassword: String,
    val newPassword: String,
    val logoutOtherSessions: Boolean,
    val dontLogoutToken: String,
)

@Serializable
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
)

@Serializable
data class GetAllUsersRequest(
    val authToken: String,
)

@Serializable
data class GetAllUsersResponse(
    val users: List<GetAllUsersResponseUser>,
)

@Serializable
data class GetAllUsersResponseUser(
    val id: Int,
    val username: String,
)
