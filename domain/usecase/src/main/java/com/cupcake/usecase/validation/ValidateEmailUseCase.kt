package com.cupcake.usecase.validation

import com.cupcake.usecase.ErrorType
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor(){
    operator fun invoke(email: String) {
        if (email.isEmpty()) {
            throw  ErrorType.InvalidFieldEmail(Error)
        }
    }
    companion object {
        private const val Error = "Please enter an email"
    }
}