package com.cupcake.usecase.job

import com.cupcake.models.Job
import repo.JobFinderRepository
import javax.inject.Inject


class GetRecommendedJobsUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {
    // todo the job title should change after (register feature)
    suspend operator fun invoke(limit: Int): List<Job> {
        return repository.getJobs()
            .filter { it.jobTitle.title == "Android" }
            .take(limit)
    }
}