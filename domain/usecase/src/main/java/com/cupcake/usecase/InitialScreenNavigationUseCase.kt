package com.cupcake.usecase

import repo.AuthenticationRepository
import javax.inject.Inject

class InitialScreenNavigationUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) {

    suspend operator fun invoke(): Boolean {
        return authenticationRepository.isLoggedIn() && !isTokenExpired()
    }

    private suspend fun isTokenExpired(): Boolean {
        val tokenExpireTime = authenticationRepository.getAuthTokenExpireTime()
        return tokenExpireTime == null || getCurrentTimeInSeconds() >= tokenExpireTime
    }

    private fun getCurrentTimeInSeconds(): Long {
        return System.currentTimeMillis() / 1000
    }

}