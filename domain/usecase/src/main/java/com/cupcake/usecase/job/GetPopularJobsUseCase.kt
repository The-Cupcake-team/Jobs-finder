package com.cupcake.usecase.job

import com.cupcake.models.JobTitle
import repo.JobFinderRepository
import javax.inject.Inject

class GetPopularJobsUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {

    // TODO:  Should return list of popular job title
    //  depend on top of jobs that you have from getJobs()
    //  "Hassan Ayman"
    suspend operator fun invoke(limit: Int): List<JobTitle> {
        return listOf()
    }

}