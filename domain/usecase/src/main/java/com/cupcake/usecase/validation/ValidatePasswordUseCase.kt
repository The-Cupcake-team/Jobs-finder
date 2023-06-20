package com.cupcake.usecase.validation

import com.cupcake.models.ErrorType
import javax.inject.Inject


class ValidatePasswordUseCase @Inject constructor() {
    operator fun invoke(password: String) {
        if (password.isBlank()) {
            throw ErrorType.Validation(ERROR)
        }


        if (password.length < MINIMUM_LENGTH) {
            throw ErrorType.Validation(LENGTH_ERROR)
        }


        if (!password.any { it.isLetter() }) {
            throw ErrorType.Validation(LETTERS_ERROR)
        }


        if (!password.any { it.isDigit() }) {
            throw ErrorType.Validation(DIGITS_ERROR)
        }


        if (!password.contains(Regex("[!@\$%*&]"))) {
            throw ErrorType.Validation(SPECIAL_CHARACTERS_ERROR)
        }
    }


    companion object {
        private const val MINIMUM_LENGTH = 8
        private const val LENGTH_ERROR = "Please write a Password with at least 8 characters"
        private const val LETTERS_ERROR = "Please write at least 1 letter"
        private const val DIGITS_ERROR = "Please write at least 1 digit"
        private const val SPECIAL_CHARACTERS_ERROR = "Please write at least 1 special character"
        private const val ERROR = "Password is Required"

    }
}
