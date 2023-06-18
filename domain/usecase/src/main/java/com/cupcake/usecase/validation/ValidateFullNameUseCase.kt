package com.cupcake.usecase.validation

import com.cupcake.usecase.ErrorType
import javax.inject.Inject

class ValidateFullNameUseCase @Inject constructor() {
     operator fun invoke(fullName: String) {
         checkIfEmpty(fullName)
         checkCharacter(fullName)
         lessThanMinimum(fullName)
    }

    private fun lessThanMinimum(fullName: String) {
        if (fullName.length <MINIMUM_LENGTH)
            throw  ErrorType.InvalidFieldFullName(Error_LENGTH)
    }

    private fun checkCharacter(fullName: String) {
        val regex = Regex("^[a-zA-Z]+$")
        if (!regex.matches(fullName) )
            throw  ErrorType.InvalidFieldFullName(Error_LETTER)
    }
    private fun checkIfEmpty(fullName: String) {
        if (fullName.isEmpty()) {
            throw ErrorType.InvalidFieldFullName(Error)
        }
    }

    companion object {
        private const val MINIMUM_LENGTH = 4
        private const val Error_LENGTH = "Please write at least 4 length of full name"
        private const val Error_LETTER = "The full name must be only letters"
        private const val Error = "Please enter a full name"

    }
}
