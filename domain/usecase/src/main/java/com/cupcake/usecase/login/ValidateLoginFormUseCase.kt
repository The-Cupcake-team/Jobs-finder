package com.cupcake.usecase.login

import javax.inject.Inject

class ValidateLoginFormUseCase @Inject constructor(
    private val validateUsername: ValidateUsernameUseCase,
    private val validatePassword: ValidatePasswordUseCase
) {
    operator fun invoke(userName: String, password: String): ValidationResults {
        val validateUserName: String = userNameChanged(userName)
        val validatePassword: String = passwordValidate(password)
        val isUserNameValid = validateUserName.isBlank()
        val isPasswordValid = validatePassword.isBlank()
        return ValidationResults(
            validateUserName,
            validatePassword,
            isUserNameValid,
            isPasswordValid
        )
    }

    private fun passwordValidate(password: String): String {
        val validateResult = validatePassword(password)
        return if (!validateResult.successful) {
            validateResult.errorMessage ?: ""
        } else {
            ""
        }
    }

    private fun userNameChanged(userName: String): String {
        val validateResult = validateUsername(userName)
        return if (!validateResult.successful) {
            validateResult.errorMessage ?: ""
        } else {
            ""
        }
    }

    data class ValidationResults(
        val validateUserName: String,
        val validatePassword: String,
        val isUserNameValid: Boolean,
        val isPasswordValid: Boolean
    )
}