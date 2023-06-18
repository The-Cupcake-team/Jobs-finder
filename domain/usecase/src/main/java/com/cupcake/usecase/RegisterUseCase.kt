package com.cupcake.usecase

import com.cupcake.usecase.validation.ValidateRegisterFormUseCase
import repo.JobFinderRepository
import javax.inject.Inject

class RegisterUseCase  @Inject constructor(
    private val repository: JobFinderRepository,
    private val validateRegisterFormUseCase: ValidateRegisterFormUseCase
){
    operator fun invoke(fullName: String,
                        userName: String,
                        email: String,
                        password: String,
                        confirmedPassword: String) {
        validateRegisterFormUseCase(fullName, userName, email ,password, confirmedPassword)
    }
}