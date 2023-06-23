package com.cupcake.usecase.validation

import com.cupcake.models.ValidationResult
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {
    operator fun invoke(email: String): ValidationResult {
        return if (email.isBlank() || email.isEmpty()) {
            ValidationResult(false, ERROR)
        } else if (!REGEX.matches(email)) {
            ValidationResult(false, INVALID_EMAIL)
        } else {
            ValidationResult(true)
        }
    }

    companion object {
        private val REGEX = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+$")
        private const val ERROR = "Email is Required"
        private const val INVALID_EMAIL = "Invalid Email Address"
    }
}