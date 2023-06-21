package com.cupcake.usecase


import com.cupcake.usecase.validation.ValidateLoginFormUseCase
import repo.JobFinderRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val validateLoginForm: ValidateLoginFormUseCase,
    private val repository: JobFinderRepository
) {
     operator fun invoke(userName: String, password: String): Boolean {
        validateLoginForm(userName, password)


        return true
    }
}