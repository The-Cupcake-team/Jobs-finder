package com.cupcake.usecase.job

import com.cupcake.models.Job
import repo.JobFinderRepository
import javax.inject.Inject


class GetRecommendedJobsUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {

    suspend operator fun invoke(limit: Int): List<Job> {
        return repository.getJobs()
            .filter { it.jobTitle.id == repository.getUserJobTitleId() }
            .take(limit)
    }
}