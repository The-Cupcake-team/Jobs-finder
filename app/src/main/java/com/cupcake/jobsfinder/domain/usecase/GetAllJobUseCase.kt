package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.domain.mapper.toJobWithJobTitle
import com.cupcake.jobsfinder.domain.model.JobWithTitle
import com.cupcake.jobsfinder.domain.reposirory.Repository
import javax.inject.Inject

class GetAllJobUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(limit: Int): List<JobWithTitle> {
        return repository.getJobs().take(limit).map { it.toJobWithJobTitle() }
    }
}