package com.cupcake.usecase.validation

import com.cupcake.models.ErrorType
import javax.inject.Inject

class ValidateFullNameUseCase @Inject constructor() {
    operator fun invoke(fullName: String) {
        if (fullName.isBlank()) {
            throw ErrorType.Validation(ERROR)
        }

        val regex = Regex("^[a-zA-Z]+$")
        if (!regex.matches(fullName)) {
            throw ErrorType.Validation(LETTERS_ERROR)
        }

        if (fullName.length < MINIMUM_LENGTH) {
            throw ErrorType.Validation(LENGTH_ERROR)
        }
    }


    companion object {
        private const val MINIMUM_LENGTH = 4
        private const val LENGTH_ERROR = "Please write at least 4 length of full name"
        private const val LETTERS_ERROR = "The full name must be only letters"
        private const val ERROR = "Please enter a full name"

    }
}
