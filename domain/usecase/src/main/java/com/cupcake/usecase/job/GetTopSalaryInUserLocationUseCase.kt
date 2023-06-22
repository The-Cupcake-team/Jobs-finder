package com.cupcake.usecase.job


import com.cupcake.models.Job
import repo.JobFinderRepository
import javax.inject.Inject

class GetTopSalaryInUserLocationUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {
    suspend operator fun invoke(limit: Int): List<Job> {
        return repository.getJobs()
            .sortedByDescending { it.jobSalary.maxSalary }
            .take(limit)
    }
}