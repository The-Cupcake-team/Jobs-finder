package com.cupcake.usecase


import com.cupcake.models.Job
import repo.JobFinderRepository
import javax.inject.Inject

class CreateJobUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {
    suspend operator fun invoke(jobInfo: Job): Boolean {
        return repository.createJob(jobInfo)
    }

}