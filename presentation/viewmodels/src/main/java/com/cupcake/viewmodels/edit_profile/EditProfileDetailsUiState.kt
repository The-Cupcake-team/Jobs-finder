package com.cupcake.viewmodels.edit_profile

data class EditProfileDetailsUiState(
    val fullName: String = "",
    val jobTitle: String = "",
    val location: String = "",
    val website: String = "",
    val isLoading: Boolean = false,
    val isProfileUpdated: Boolean = false,
)