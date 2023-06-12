package com.cupcake.ui.login

import java.lang.Error

data class LoginUiState(
    val userName: String = "",
    val password: String = "",
    val userNameError: String = "",
    val passwordError: String = "",
    val isUserNameValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isLoading: Boolean = false,
    val isValidFields: Boolean = false,
    val error: String = ""
    )