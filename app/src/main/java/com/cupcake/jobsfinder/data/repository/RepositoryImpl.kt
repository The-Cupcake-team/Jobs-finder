package com.cupcake.jobsfinder.data.repository

import android.util.Log
import com.cupcake.jobsfinder.data.remote.JobApiService
import com.cupcake.jobsfinder.domain.mapper.toJob
import com.cupcake.jobsfinder.domain.model.Job
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: JobApiService) : Repository {
    override suspend fun getAllJobs(): Flow<List<Job>> {
        return flow {
            val response = api.getAllJobs()
            if (response.isSuccessful) {
                response.body()?.value?.map { it.toJob() }?.let { emit(it) }
            } else {
                Log.v("repositoryImp", response.message())
            }
        }.flowOn(Dispatchers.IO)
    }

}