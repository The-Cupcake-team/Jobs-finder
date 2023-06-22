package com.cupcake.usecase.validation

import com.cupcake.models.ErrorType
import javax.inject.Inject


class ValidatePasswordUseCase @Inject constructor() {
    operator fun invoke(password: String): ErrorType.InvalidFieldPassword {
        return if (password.isEmpty() || password.isBlank()) {
            ErrorType.InvalidFieldPassword(ERROR)
        } else if (password.length < MINIMUM_LENGTH) {
            ErrorType.InvalidFieldPassword(ERROR_LENGTH_SHORT)
        } else if (password.length > MAXIMUM_LENGTH) {
            ErrorType.InvalidFieldPassword(ERROR_LENGTH_LARGE)
        } else if (!password.any { it.isLetter() }) {
            ErrorType.InvalidFieldPassword(ERROR_LETTER)
        } else if (!password.any { it.isDigit() }) {
            ErrorType.InvalidFieldPassword(ERROR_DIGIT)
        } else if (!password.contains(Regex("[!@\$%*&]"))) {
            ErrorType.InvalidFieldPassword(ERROR_SPECIAL_CHARACTER)
        } else {
            ErrorType.InvalidFieldPassword("")
        }
    }

    companion object {
        private const val MINIMUM_LENGTH = 8
        private const val MAXIMUM_LENGTH = 250
        private const val ERROR_LENGTH_SHORT = "Please write at least 8 length of password"
        private const val ERROR_LENGTH_LARGE = "Please write at most 8 length of password"
        private const val ERROR_LETTER = "Please write at least 1 letter"
        private const val ERROR_DIGIT = "Please write at least 1 digit"
        private const val ERROR_SPECIAL_CHARACTER = "Please write at least 1 special character"
        private const val ERROR = "Please enter a password"

    }
}
