package com.cupcake.usecase.login

import com.cupcake.models.ErrorType
import repo.AuthenticationRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val validateLoginForm: ValidateLoginFormUseCase,
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(userName: String, password: String) {
        val isValid = validateLoginForm(userName, password)

        if (!isValid) {
            throw ErrorType.UnAuthorized(ERROR)
        }

        val token = authenticationRepository.login(userName, password)
        authenticationRepository.saveAuthData(token)
    }

    companion object {
        const val ERROR = "Please Fill All Fields"
    }
}