package com.cupcake.usecase.validation

import com.cupcake.models.ErrorType
import javax.inject.Inject

class ValidateConfirmedPasswordUseCase @Inject constructor() {
    operator fun invoke(confirmedPassword: String, password: String) {
        if (confirmedPassword != password) {
            throw ErrorType.InvalidFieldConfirmedPassword(ERROR)
        }
    }

    companion object {
        private const val ERROR = "The password does not match"
    }
}
