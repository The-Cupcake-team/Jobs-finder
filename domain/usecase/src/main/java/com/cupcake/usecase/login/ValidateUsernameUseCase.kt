package com.cupcake.usecase.login

import com.cupcake.usecase.ErrorType
import javax.inject.Inject


class ValidateUsernameUseCase @Inject constructor() {
    operator fun invoke(username: String): ValidationResult {
        if (lessThanMinimum(username)) {
            throw  ErrorType.InvalidFieldUserName(Error_LENGTH)

        }
        return ValidationResult(true)
    }

    private fun lessThanMinimum(password: String): Boolean {
        return password.length < MINIMUM_LENGTH
    }


    companion object {
        private const val MINIMUM_LENGTH = 4
        private const val Error_LENGTH = "Please write at least 4 length of username"
    }
}