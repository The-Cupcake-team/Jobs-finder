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
        val validationResults = validateLoginForm(userName, password)

        if (validationResults.any { !it.isValid }) {
            throw ErrorType.UnAuthorized(validationResults)
        }

        val user = authenticationRepository.login(userName, password)
        authenticationRepository.saveAuthData(user.token, true)
        jobsFinderRepository.insertProfile(user)
        jobsFinderRepository.saveProfileData(user.profile.avatar, user.profile.jobTitle.id)
    }

    companion object {
        const val ERROR = "Please Fill All Fields"
    }
}