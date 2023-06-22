package com.cupcake.usecase.validation

import com.cupcake.models.ErrorType
import javax.inject.Inject

class ValidateConfirmedPasswordUseCase @Inject constructor() {
    operator fun invoke(
        confirmedPassword: String,
        password: String
    ): ErrorType.InvalidFieldConfirmedPassword {
        return if (confirmedPassword.isBlank() || confirmedPassword.isEmpty()) {
            ErrorType.InvalidFieldConfirmedPassword(ERROR)
        } else if (confirmedPassword != password) {
            ErrorType.InvalidFieldConfirmedPassword(ERROR_NO_MATCHING)
        } else {
            ErrorType.InvalidFieldConfirmedPassword("")
        }
    }

    companion object {
        private const val ERROR = "Please enter the confirm password"
        private const val ERROR_NO_MATCHING = "The password doesn't match"
    }
}
