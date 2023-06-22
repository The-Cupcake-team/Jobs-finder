package com.cupcake.usecase.validation

import com.cupcake.models.ErrorType
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {
    operator fun invoke(email: String) {
        if (email.isBlank() || email.isEmpty()) {
            throw ErrorType.InvalidFieldEmail(ERROR)
        } else if (!REGEX.matches(email)) {
            throw ErrorType.InvalidFieldEmail(INVALID_EMAIL)
        }
    }

    companion object {
        private val REGEX = Regex("^\\\\w+([.-]?\\\\w+)*@\\\\w+([.-]?\\\\w+)*(\\\\.\\\\w{2,})+\$")
        private const val ERROR = "Email is Required"
        private const val INVALID_EMAIL = "Invalid Email Address"
    }
}