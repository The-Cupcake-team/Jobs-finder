package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.data.repository.Repository
import javax.inject.Inject

class GetJobByIdUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(jobId: Int) = repository.getJobById(jobId = jobId)
}