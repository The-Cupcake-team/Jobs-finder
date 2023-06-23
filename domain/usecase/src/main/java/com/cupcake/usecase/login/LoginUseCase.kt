package com.cupcake.usecase.login

import com.cupcake.models.ErrorType
import repo.AuthenticationRepository
import repo.JobFinderRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val validateLoginForm: ValidateLoginFormUseCase,
    private val jobsFinderRepository: JobFinderRepository,
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(userName: String, password: String) {
        val isValid = validateLoginForm(userName, password)

        if (!isValid) {
            throw ErrorType.UnAuthorized(ERROR)
        }

        val user = authenticationRepository.login(userName, password)
        authenticationRepository.saveAuthData(user.token)
        jobsFinderRepository.saveProfileData(user.profile.avatar, user.profile.jobTitle.id)
    }

    companion object {
        const val ERROR = "Please Fill All Fields"
    }
}