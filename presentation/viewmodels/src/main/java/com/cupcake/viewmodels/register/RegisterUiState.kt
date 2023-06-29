package com.cupcake.viewmodels.register

import com.cupcake.viewmodels.jobs.JobTitleUiState

data class RegisterUiState(
    val fullName: String = "",
    val userName: String = "",
    val email: String = "",
    val jobTitle: String = "",
    val password: String = "",
    val confirmedPassword: String = "",
    val jobTitleId: Int = 0,

    val isFullNameValid: Boolean = false,
    val isUserNameValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val isJobTitleValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isConfirmedPasswordValid: Boolean = false,

    val fullNameError: String = "",
    val userNameError: String = "",
    val emailError: String = "",
    val jobTitleError: String = "",
    val passwordError: String = "",
    val confirmedPasswordError: String = "",

    val jobTitles: List<JobTitleUiState> = emptyList(),

    val isLoading: Boolean = false,
)
