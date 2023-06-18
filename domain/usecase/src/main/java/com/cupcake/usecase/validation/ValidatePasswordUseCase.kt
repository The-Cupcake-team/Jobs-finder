package com.cupcake.usecase.validation

import com.cupcake.usecase.ErrorType
import javax.inject.Inject


class ValidatePasswordUseCase @Inject constructor() {
    operator fun invoke(passwordText: String){
        checkIfEmpty(passwordText)
        lessThanMinimum(passwordText)
        notContainsLetter(passwordText)
        notContainsDigit(passwordText)
        checkCharacter(passwordText)


    }

    private fun checkIfEmpty(passwordText: String) {
        if (passwordText.isEmpty()) {
            throw ErrorType.InvalidFieldPassword(Error)
        }
    }

    private fun lessThanMinimum(password: String) {
        if (password.length < MINIMUM_LENGTH) {
            throw ErrorType.InvalidFieldPassword(Error_LENGTH)
        }
    }

    private fun notContainsLetter(password: String) {
       if (!password.any { it.isLetter() }){
           throw  ErrorType.InvalidFieldPassword(Error_LETTER)
       }
    }

    private fun notContainsDigit(password: String) {
         if (!password.any { it.isDigit() }){
            throw  ErrorType.InvalidFieldPassword(Error_DIGIT)
        }
    }

    private fun checkCharacter(password: String) {
        if (!password.contains(Regex("[!@\$%*&]"))) {
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
