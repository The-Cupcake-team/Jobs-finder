package com.cupcake.usecase.register

import com.cupcake.models.ErrorType
import com.cupcake.models.User
import repo.AuthenticationRepository
import repo.JobFinderRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val jobsFinderRepository: JobFinderRepository,
    private val validateRegisterForm: ValidateRegisterFormUseCase
) {

    suspend operator fun invoke(
        fullName: String,
        userName: String,
        email: String,
        jobTitle: String,
        password: String,
        confirmPassword: String,
        jobTitleId: Int
    ): User {

        val isValid = validateRegisterForm(fullName, userName, email, jobTitle, password, confirmPassword)

        if (!isValid) {
            throw ErrorType.UnAuthorized(ERROR)
        }

        val user =
            authenticationRepository.register(fullName, userName, email, password, jobTitleId)

        authenticationRepository.saveAuthData(user.token)

        jobsFinderRepository.saveProfileData(user.profile.avatar, user.profile.jobTitle.id)

        return user
    }

    companion object {
        const val ERROR = "Please Fill All Fields"
    }
}