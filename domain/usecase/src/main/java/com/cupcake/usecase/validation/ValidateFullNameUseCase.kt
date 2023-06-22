package com.cupcake.usecase.validation

import com.cupcake.models.ErrorType
import javax.inject.Inject

class ValidateFullNameUseCase @Inject constructor() {
    operator fun invoke(fullName: String): ErrorType.InvalidFieldFullName {
        return if (fullName.isBlank() || fullName.isEmpty()) {
            ErrorType.InvalidFieldFullName(ERROR)
        } else if (fullName.length < MINIMUM_LENGTH) {
            ErrorType.InvalidFieldFullName(ERROR_LENGTH_SHORT)
        } else if (fullName.length > MAXIMUM_LENGTH) {
            ErrorType.InvalidFieldFullName(ERROR_LENGTH_LARGE)
        } else if (!Regex("^[a-zA-Z]+$").matches(fullName)) {
            ErrorType.InvalidFieldFullName(LETTERS_ERROR)
        } else {
            return ErrorType.InvalidFieldFullName("")
        }
    }


    companion object {
        private const val MINIMUM_LENGTH = 6
        private const val MAXIMUM_LENGTH = 250
        private const val ERROR_LENGTH_SHORT = "Please write at least 6 length of full name"
        private const val ERROR_LENGTH_LARGE = "Please write at most 250 length of full name"
        private const val LETTERS_ERROR = "The full name must be only letters"
        private const val ERROR = "Please enter a full name"

    }
}
