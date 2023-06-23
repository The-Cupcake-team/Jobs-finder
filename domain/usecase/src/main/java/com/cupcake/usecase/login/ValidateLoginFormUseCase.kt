package com.cupcake.usecase.login

import com.cupcake.usecase.validation.ValidatePasswordUseCase
import com.cupcake.usecase.validation.ValidateUsernameUseCase
import javax.inject.Inject

class ValidateLoginFormUseCase @Inject constructor(
    private val validateUsername: ValidateUsernameUseCase,
    private val validatePassword: ValidatePasswordUseCase,
) {
    operator fun invoke(
        userName: String,
        password: String
    ): Boolean {
        return validateUsername(userName).isValid &&
                validatePassword(password).isValid
    }
}