package hr.kristiankliskovic.devcontrol.ui.userProfileSettings

data class UserProfileSettingsViewState(
    val userId: Int,
    val username: String,
    val email: String,
){
    companion object{
        fun getEmptyObject() = UserProfileSettingsViewState(
            userId = -1,
            username = "",
            email = "",
        )
    }
}
