package com.cupcake.usecase.profile

import com.cupcake.models.Job
import com.cupcake.usecase.ValidateJobFormUseCase
import repo.JobFinderRepository
import javax.inject.Inject

class ProfileUseCase  @Inject constructor(
    private val repository: JobFinderRepository,
    private val validateJobFormUseCase: ValidateJobFormUseCase
) {
    suspend operator fun invoke(jobInfo: Job): Boolean {
        validateJobFormUseCase(jobInfo)
        return repository.createJob(jobInfo)
    }

}