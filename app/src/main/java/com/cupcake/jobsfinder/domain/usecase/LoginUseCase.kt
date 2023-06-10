package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.data.repository.JobFinderRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {
    suspend operator fun invoke(email: String, password: String): Boolean {
        return true
    }
}