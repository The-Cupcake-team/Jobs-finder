package com.cupcake.usecase.job


import com.cupcake.models.JobWithTitle
import repo.JobFinderRepository
import javax.inject.Inject

class GetTopSalaryInUserLocationUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {
    suspend operator fun invoke(limit: Int): List<JobWithTitle> {
        return repository.getJobs()
            .sortedByDescending { it.salary }
            .take(limit)
    }
}