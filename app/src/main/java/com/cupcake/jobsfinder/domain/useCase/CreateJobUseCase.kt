package com.cupcake.jobsfinder.domain.useCase

import com.cupcake.jobsfinder.domain.reposirory.Repository
import com.cupcake.jobsfinder.domain.mapper.toJobDto
import com.cupcake.jobsfinder.domain.model.Job
import javax.inject.Inject

class CreateJobUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(job: Job): Boolean {
        return repository.createJob(job.toJobDto())
    }
}