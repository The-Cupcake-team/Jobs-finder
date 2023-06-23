package com.cupcake.usecase.register

import com.cupcake.models.ErrorType
import com.cupcake.models.User
import repo.AuthenticationRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val validateRegisterForm: ValidateRegisterFormUseCase
) {

    suspend operator fun invoke(
        fullName: String,
        userName: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {

        val isValid = validateRegisterForm(fullName, userName, email, password, confirmPassword)

        if (!isValid) {
            throw ErrorType.UnAuthorized(ERROR)
        }

        val user = authenticationRepository.register(fullName, userName, email, password)
        authenticationRepository.saveAuthData(user.token)
    }

    companion object {
        const val ERROR = "Please Fill All Fields"
    }
}