package com.cupcake.usecase

import repo.JobFinderRepository
import javax.inject.Inject

class GetAllJobUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {
//    suspend operator fun invoke(limit: Int): List<JobWithTitle> {
//        return repository.getJobs().take(limit).map { it.toJobWithJobTitle() }
//    }
}