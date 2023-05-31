package com.cupcake.jobsfinder.data.remote

import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import retrofit2.Response
import retrofit2.http.GET

interface JobApiService {
    @GET("/job")
    suspend fun getAllJobs(): Response<BaseResponse<JobDto>>
}