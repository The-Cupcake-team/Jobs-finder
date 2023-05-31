package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.domain.model.Job
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getAllJobs(): Flow<List<Job>>
}