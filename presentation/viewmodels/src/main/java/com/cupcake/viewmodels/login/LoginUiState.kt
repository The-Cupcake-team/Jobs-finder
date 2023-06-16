package com.cupcake.viewmodels.login


data class LoginUiState(
    var userName: String = "",
    var password: String = "",
    val userNameError: String = "",
    val passwordError: String = "",
    val isUserNameValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = ""
)