package com.cupcake.viewmodels.register

import com.cupcake.viewmodels.base.BaseErrorUiState

data class RegisterUiState(
    val fullName: String = "",
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isUserRegistered: Boolean = false,
    val isLoading: Boolean = false,
    val error: BaseErrorUiState? = null,
)