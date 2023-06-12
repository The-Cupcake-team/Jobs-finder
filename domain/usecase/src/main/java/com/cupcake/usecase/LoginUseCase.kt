package com.cupcake.usecase


import repo.JobFinderRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {
    suspend operator fun invoke(email: String, password: String): Boolean {
        return true
    }
}