package com.cupcake.usecase.register

import com.cupcake.usecase.validation.ValidateConfirmedPasswordUseCase
import com.cupcake.usecase.validation.ValidateEmailUseCase
import com.cupcake.usecase.validation.ValidateFullNameUseCase
import com.cupcake.usecase.validation.ValidatePasswordUseCase
import com.cupcake.usecase.validation.ValidateUsernameUseCase
import javax.inject.Inject

class ValidateRegisterFormUseCase @Inject constructor(
    private val validateFullName: ValidateFullNameUseCase,
    private val validateUsername: ValidateUsernameUseCase,
    private val validateEmail: ValidateEmailUseCase,
    private val validatePassword: ValidatePasswordUseCase,
    private val validateConfirmedPassword: ValidateConfirmedPasswordUseCase,
) {
    operator fun invoke(
        fullName: String,
        userName: String,
        email: String,
        password: String,
        confirmedPassword: String
    ){
        validateFullName(fullName)
        validateUsername(userName)
        validateEmail(email)
        validatePassword(password)
        validateConfirmedPassword(confirmedPassword, password)
    }
}