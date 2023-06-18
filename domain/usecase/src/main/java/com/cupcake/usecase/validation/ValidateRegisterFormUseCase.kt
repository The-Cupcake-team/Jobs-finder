package com.cupcake.usecase.validation

import javax.inject.Inject

class ValidateRegisterFormUseCase @Inject constructor(
    private val validateUsername: ValidateUsernameUseCase,
    private val validatePassword: ValidatePasswordUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateConfirmedPasswordUseCase: ValidateConfirmedPasswordUseCase,
    private val validateFullNameUseCase: ValidateFullNameUseCase
) {
    operator fun invoke(fullName: String,
                        userName: String,
                        email: String,
                        password: String,
                        confirmedPassword:String
                        ) {
        validateFullNameUseCase(fullName)
        validateUsername(userName)
        validateEmailUseCase(email)
        validatePassword(password)
        validateConfirmedPasswordUseCase(confirmedPassword, password)
    }
}