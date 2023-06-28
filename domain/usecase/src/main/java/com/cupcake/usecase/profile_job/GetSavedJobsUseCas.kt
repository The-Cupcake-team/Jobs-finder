package com.cupcake.usecase.profile_job

import com.cupcake.models.Job
import com.cupcake.models.JobSalary
import com.cupcake.models.JobTitle
import com.cupcake.models.Post
import repo.JobFinderRepository
import javax.inject.Inject

class GetSavedJobsUseCas @Inject constructor(
    private val repository: JobFinderRepository
){
    suspend operator fun invoke():List<Job>{
        return repository.getSavedJobs()
    }
}