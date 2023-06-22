package com.cupcake.usecase.job


import com.cupcake.models.Job
import repo.JobFinderRepository
import javax.inject.Inject

class GetJobsOnUserLocationUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {
    // todo the country should change after (register feature)
    suspend operator fun invoke(limit: Int): List<Job> {
        return repository.getJobs()
            .filter { it.jobLocation == "Iraq" }
            .take(limit)
    }
}