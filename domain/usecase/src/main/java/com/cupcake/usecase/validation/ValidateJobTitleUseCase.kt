package com.cupcake.usecase.validation

import com.cupcake.models.ValidationResult
import javax.inject.Inject

class ValidateJobTitleUseCase @Inject constructor() {
    operator fun invoke(jobTitle: String): ValidationResult {
        return if (jobTitle.isBlank()) {
            ValidationResult(false, ERROR)
        } else if (jobTitle.length < MINIMUM_LENGTH) {
            ValidationResult(false, ERROR_LENGTH_SHORT)
        } else if (jobTitle.length > MAXIMUM_LENGTH) {
            ValidationResult(false, ERROR_LENGTH_LARGE)
        } else if (!Regex("^[a-zA-Z/-]+$").matches(jobTitle)) {
            ValidationResult(false, ERROR_LETTER)
        } else {
            ValidationResult(true, "")
        }
    }


    companion object {
        private const val MINIMUM_LENGTH = 4
        private const val MAXIMUM_LENGTH = 250
        private const val ERROR_LENGTH_SHORT = "Please write at least 6 length of full name"
        private const val ERROR_LENGTH_LARGE = "Please write at most 250 length of full name"
        private const val ERROR_LETTER = "The job title must contain only letters, dashes, or slashes"
        private const val ERROR = "Please enter a full name"

    }
}
