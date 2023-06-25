package com.cupcake.usecase

import repo.JobFinderRepository
import javax.inject.Inject

class GetNotificationsUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {
    suspend operator fun invoke() = repository.notifications()
}