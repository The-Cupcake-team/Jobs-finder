package com.cupcake.usecase.validation

import com.cupcake.models.ErrorType
import javax.inject.Inject


class ValidatePasswordUseCase @Inject constructor() {
    operator fun invoke(passwordText: String){
        val password = passwordText.trim()

        if (password.isEmpty()) {
            throw ErrorType.InvalidFieldPassword(Error)
        } else if (password.length < MINIMUM_LENGTH) {
            throw ErrorType.InvalidFieldPassword(Error_LENGTH)
        } else if (!password.any { it.isLetter() }) {
            throw ErrorType.InvalidFieldPassword(Error_LETTER)
        } else if (!password.any { it.isDigit() }) {
            throw ErrorType.InvalidFieldPassword(Error_DIGIT)
        } else if (!password.contains(Regex("[!@\$%*&]"))) {
            throw ErrorType.InvalidFieldPassword(Error_SPECIALCHARACTER)
        }
    }

    companion object {
        private const val MINIMUM_LENGTH = 8
        private const val Error_LENGTH = "Please write at least 8 length of password"
        private const val Error_LETTER = "Please write at least 1 letter"
        private const val Error_DIGIT = "Please write at least 1 digit"
        private const val Error_SPECIALCHARACTER = "Please write at least 1 special character"
        private const val Error = "Please enter a password"

    }
}
