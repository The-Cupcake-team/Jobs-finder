package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.JobApiService
import com.cupcake.jobsfinder.data.remote.ResultState
import com.cupcake.jobsfinder.data.remote.model.JobDto
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: JobApiService
) : Repository {
    override suspend fun createJob(
        jobDto: JobDto
    ): ResultState<JobDto> {
        return try {
            val job = api.createJob(jobDto)
            ResultState.Success(job)
        } catch (e: Throwable) {
            ResultState.Error(e)
        }
    }
}