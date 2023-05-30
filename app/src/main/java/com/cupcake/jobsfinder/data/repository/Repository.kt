package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.ResultState
import com.cupcake.jobsfinder.data.remote.model.JobDto

interface Repository {

    suspend fun createJob(
         jobDto: JobDto
    ): ResultState<JobDto>
}