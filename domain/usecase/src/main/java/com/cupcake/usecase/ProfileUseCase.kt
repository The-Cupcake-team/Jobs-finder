package com.cupcake.usecase

import com.cupcake.models.ErrorType
import com.cupcake.models.Profile
import com.cupcake.models.UserProfile
import com.cupcake.usecase.login.LoginUseCase
import com.cupcake.usecase.login.ValidateLoginFormUseCase
import repo.AuthenticationRepository
import repo.JobFinderRepository
import javax.inject.Inject

class ProfileUseCase@Inject constructor(
    private val jobsFinderRepository: JobFinderRepository,
) {

    suspend operator fun invoke(id: String): UserProfile {
       return jobsFinderRepository.getProfile(id)
    }

}