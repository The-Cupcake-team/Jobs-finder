package com.cupcake.usecase.validation

import com.cupcake.models.ValidationResult
import javax.inject.Inject

class ValidateFullNameUseCase @Inject constructor() {
    operator fun invoke(fullName: String): ValidationResult {
        return if (fullName.isBlank() || fullName.isEmpty()) {
            ValidationResult(false, ERROR)
        } else if (fullName.length < MINIMUM_LENGTH) {
            ValidationResult(false, ERROR_LENGTH_SHORT)
        } else if (fullName.length > MAXIMUM_LENGTH) {
            ValidationResult(false, ERROR_LENGTH_LARGE)
        } else {
            ValidationResult(true, "")
        }
    }


    companion object {
        private const val MINIMUM_LENGTH = 6
        private const val MAXIMUM_LENGTH = 250
        private const val ERROR_LENGTH_SHORT = "Please write at least 6 length of full name"
        private const val ERROR_LENGTH_LARGE = "Please write at most 250 length of full name"
        private const val ERROR = "Please enter a full name"

    }
}
