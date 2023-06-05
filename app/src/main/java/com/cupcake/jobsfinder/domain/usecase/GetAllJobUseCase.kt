package com.cupcake.jobsfinder.domain.useCase

import com.cupcake.jobsfinder.domain.mapper.toJobWithJobTitle
import com.cupcake.jobsfinder.domain.repository.Repository
import com.cupcake.jobsfinder.domain.model.JobWithTitle
import javax.inject.Inject

class GetAllJobUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(limit: Int): List<JobWithTitle> {
        return repository.getJobs().take(limit).map { it.toJobWithJobTitle() }
    }
}