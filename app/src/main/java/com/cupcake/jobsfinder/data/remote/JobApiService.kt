package com.cupcake.jobsfinder.data.remote

import com.cupcake.jobsfinder.data.remote.dtos.BaseResponse
import com.cupcake.jobsfinder.data.remote.dtos.JobDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface JobApiService {
    @GET("/job/{id}")
    suspend fun getJobById(
        @Path("id") jobId: Int
    ) : Response<BaseResponse<JobDto>>
}