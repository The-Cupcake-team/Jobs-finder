package com.cupcake.usecase.validation

import com.cupcake.usecase.ErrorType
import javax.inject.Inject

class ValidateConfirmedPasswordUseCase @Inject constructor(){
    operator fun invoke(confirmedPassword: String, password: String){
        if (confirmedPassword != password) {
            throw  ErrorType.InvalidFieldConfirmedPassword(Error)
        }

    }
    companion object {
        private const val Error = "The password does not match"
    }
}
