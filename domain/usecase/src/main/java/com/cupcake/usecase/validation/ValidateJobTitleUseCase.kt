package com.cupcake.usecase.validation

import com.cupcake.models.ValidationResult
import javax.inject.Inject

class ValidateJobTitleUseCase @Inject constructor() {
    operator fun invoke(jobTitle: String): ValidationResult {
        return if (jobTitle.isBlank() || jobTitle.isEmpty()) {
            ValidationResult(false, ERROR)
        } else {
            ValidationResult(true)
        }
    }

    companion object {
        private const val ERROR = "Please enter a Job Title"
    }
}
