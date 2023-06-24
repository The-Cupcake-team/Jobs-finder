package com.cupcake.viewmodels.register

import com.cupcake.viewmodels.jobs.JobTitleUiState

data class RegisterUiState(
    var fullName: String = "",
    var userName: String = "",
    var email: String = "",
    var password: String = "",
    var confirmedPassword: String = "",
    var jobTitleId: Int = 1,

    val isFullNameValid: Boolean = false,
    val isUserNameValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isConfirmedPasswordValid: Boolean = false,

    val fullNameError: String = "",
    val userNameError: String = "",
    val emailError: String = "",
    val passwordError: String = "",
    val confirmedPasswordError: String = "",

    var jobTitles: List<JobTitleUiState> = emptyList(),

    val isLoading: Boolean = false,
)
