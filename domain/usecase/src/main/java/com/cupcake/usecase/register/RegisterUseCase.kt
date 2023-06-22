package com.cupcake.usecase.register

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
    ): User {

        validateRegisterForm(fullName, userName, email, password, confirmPassword)

        val user = authenticationRepository.register(fullName, userName, email, password)
        authenticationRepository.saveAuthToken(user.token)

        return user
    }
}