package com.cupcake.jobsfinder.domain

import com.cupcake.jobsfinder.data.remote.ResultState
import com.cupcake.jobsfinder.data.remote.model.JobDto
import com.cupcake.jobsfinder.data.repository.RepositoryImpl
import javax.inject.Inject

class CreateJobUseCase @Inject constructor(
    private val repository: RepositoryImpl
) {
    suspend fun createJob(
        jobDto: JobDto
    ): ResultState<JobDto> {
        return repository.createJob(jobDto)
    }
}