package com.cupcake.usecase.login

import javax.inject.Inject

class ValidateLoginFormUseCase @Inject constructor(
    private val validateUsername: ValidateUsernameUseCase,
    private val validatePassword: ValidatePasswordUseCase
) {
    operator fun invoke(userName: String, password: String): ValidationResults {
        val validateUserName: String = userNameChanged(userName)
        val validatePassword: String = passwordValidate(password)
        var isUserNameValid = false
        var isPasswordValid = false
        if (validateUserName == "") {
            isUserNameValid = true
        }
        if (validatePassword == "") {
            isPasswordValid = true
        }
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
        val isUserNameValid: Boolean=false ,
        val isPasswordValid: Boolean=false ,
    )


}