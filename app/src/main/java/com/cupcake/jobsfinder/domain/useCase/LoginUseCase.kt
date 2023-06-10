package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.domain.reposirory.Repository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(email: String, password: String): Boolean {
        return true
    }
}