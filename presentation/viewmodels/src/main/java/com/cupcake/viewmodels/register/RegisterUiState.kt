package com.cupcake.viewmodels.register

import com.cupcake.viewmodels.base.BaseErrorUiState

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

    val fieldName: String = "",
    val fieldError: String = "",
    val isFieldValid: Boolean = false,


    val isLoading: Boolean = false,

    val isUserRegistered: Boolean = false,

    val error: BaseErrorUiState? = null,
)