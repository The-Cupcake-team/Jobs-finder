package com.cupcake.usecase.validation

import com.cupcake.models.ErrorType
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {
    operator fun invoke(email: String) {
        if (email.isBlank()) {
            throw ErrorType.InvalidFieldEmail(ERROR)
        }

        /*if ("@" !in email) {
            //throw ErrorType.Validation(INVALID_EMAIL)
            return  INVALID_EMAIL
        }*/

    }

    companion object {
        private const val ERROR = "Email is Required"
        private const val INVALID_EMAIL = "Invalid Email Address"
    }
}