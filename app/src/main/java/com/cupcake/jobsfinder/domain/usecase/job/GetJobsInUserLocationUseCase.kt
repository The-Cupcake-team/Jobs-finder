package com.cupcake.jobsfinder.domain.usecase.job

import com.cupcake.jobsfinder.domain.mapper.toJobWithJobTitle
import com.cupcake.jobsfinder.domain.model.JobWithTitle
import com.cupcake.jobsfinder.data.repository.JobFinderRepository
import javax.inject.Inject

class GetJobsInUserLocationUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {
    suspend operator fun invoke(limit: Int): List<JobWithTitle> {
        return repository.getJobs()
            .filter { it.jobLocation == "Iraq" }
            .take(limit)
            .map { it.toJobWithJobTitle() }
    }
}