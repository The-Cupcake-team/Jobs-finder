package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getAllJobs(): Flow<List<JobDto>>
}