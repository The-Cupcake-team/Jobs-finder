package com.cupcake.usecase.job

import com.cupcake.models.JobTitle
import repo.JobFinderRepository
import javax.inject.Inject

class GetPopularJobsUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {

    suspend operator fun invoke(limit: Int): List<JobTitle> {
        return repository.getJobs()
            .asSequence()
            .map { it.jobTitle.title }
            .groupBy{it}
            .map {it.key to (it.value.size)}
            .sortedByDescending {it.second}
            .map { JobTitle(it.second , it.first)}
            .take(limit)
            .toList()
    }

}