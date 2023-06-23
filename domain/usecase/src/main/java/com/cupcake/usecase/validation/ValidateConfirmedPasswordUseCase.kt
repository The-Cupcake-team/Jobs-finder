package com.cupcake.usecase.validation

import com.cupcake.models.ValidationResult
import javax.inject.Inject

class ValidateConfirmedPasswordUseCase @Inject constructor() {
    operator fun invoke(
        confirmedPassword: String,
        password: String
    ): ValidationResult {
        return if (confirmedPassword.isBlank() || confirmedPassword.isEmpty()) {
            ValidationResult(false, ERROR)
        } else if (confirmedPassword != password) {
            ValidationResult(false, ERROR_NO_MATCHING)
        } else {
            ValidationResult(true)
        }
    }

    companion object {
        private const val ERROR = "Please enter the confirm password"
        private const val ERROR_NO_MATCHING = "The password doesn't match"
    }
}
