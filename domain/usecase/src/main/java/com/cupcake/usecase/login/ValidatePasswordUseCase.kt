package com.cupcake.usecase.login

import com.cupcake.usecase.ErrorType
import javax.inject.Inject


class ValidatePasswordUseCase @Inject constructor() {
    operator fun invoke(passwordText: String): ValidationResult {
        if (lessThanMinimum(passwordText)) {
             throw  ErrorType.InvalidFieldPassword(Error_LENGTH)
        }
        if (notContainsLetter(passwordText)) {
            throw  ErrorType.InvalidFieldPassword(Error_LETTER)
        }
        if (notContainsDigit(passwordText)) {
            throw  ErrorType.InvalidFieldPassword(Error_DIGIT)
        }

        return ValidationResult(true)
    }

    private fun lessThanMinimum(password: String): Boolean {
        return password.length < MINIMUM_LENGTH
    }

    private fun notContainsLetter(password: String): Boolean {
        return !password.any { it.isLetter() }
    }

    private fun notContainsDigit(password: String): Boolean {
        return !password.any { it.isDigit() }
    }

    companion object {
        private const val MINIMUM_LENGTH = 8
        private const val Error_LENGTH = "Please write at least 8 length of password"
        private const val Error_LETTER = "Please write at least 1 letter"
        private const val Error_DIGIT = "Please write at least 1 digit"
    }
}