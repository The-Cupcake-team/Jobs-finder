package com.cupcake.viewmodels.register

data class RegisterUiState(
    var fullName: String = "",
    var userName: String = "",
    var email: String = "",
    var password: String = "",
    var confirmedPassword: String = "",

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

    val isLoading: Boolean = false,
)