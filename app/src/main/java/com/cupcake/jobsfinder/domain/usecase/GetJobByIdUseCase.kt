package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.domain.reposirory.Repository
import javax.inject.Inject

class GetJobByIdUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(jobId: String) = repository.getJobById(jobId = jobId)
}