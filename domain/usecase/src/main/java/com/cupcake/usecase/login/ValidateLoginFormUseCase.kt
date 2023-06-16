package com.cupcake.usecase.login

import javax.inject.Inject

class ValidateLoginFormUseCase @Inject constructor(
    private val validateUsername: ValidateUsernameUseCase,
    private val validatePassword: ValidatePasswordUseCase
) {
    operator fun invoke(userName: String, password: String) {
        validateUsername(userName)
        validatePassword(password)

    }



}