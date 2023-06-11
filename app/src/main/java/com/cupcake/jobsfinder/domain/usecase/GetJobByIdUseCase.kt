package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.data.repository.JobFinderRepository
import javax.inject.Inject

class GetJobByIdUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {
    suspend operator fun invoke(jobId: String) = repository.getJobById(jobId = jobId)
}