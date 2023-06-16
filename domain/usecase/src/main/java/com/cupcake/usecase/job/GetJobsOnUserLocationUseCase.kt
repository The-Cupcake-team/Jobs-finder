package com.cupcake.usecase.job


import com.cupcake.models.JobWithTitle
import repo.JobFinderRepository
import javax.inject.Inject

class GetJobsOnUserLocationUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {
    // todo the country should change after (register feature)
    suspend operator fun invoke(limit: Int): List<JobWithTitle> {
        return repository.getJobs()
            .filter { it.jobLocation == "Iraq" }
            .take(limit)
    }
}