package com.cupcake.jobsfinder.data.remote


import com.cupcake.jobsfinder.data.remote.response.JobTitleDto
import com.cupcake.jobsfinder.data.remote.response.PostDto
import com.cupcake.jobsfinder.data.remote.response.base.BaseResponse
import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import com.cupcake.jobsfinder.data.remote.response.job.JobWithTitleDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface JobApiService {



    @GET("/posts")
    suspend fun getPosts(): Response<BaseResponse<List<PostDto>>>

    @POST("/jobs")
    suspend fun createJob(@Body job: JobDto): JobDto

    // region Job

    @GET("/job")
    suspend fun getJobs(): Response<BaseResponse<List<JobWithTitleDto>>>

    @GET("/job/{id}")
    suspend fun getJobById(
        @Path("id") jobId: Int
    ): Response<BaseResponse<JobDto>>

    //endregion


    // region Post

    @GET("post/{postId}")
    suspend fun getPostById(
        @Path("postId") postId: String
    ): Response<BaseResponse<PostDto>>


    //endregion

    // region JobTitle
    @GET("/jobTitles")
    suspend fun getAllJobTitle(): Response<BaseResponse<List<JobTitleDto>>>
    // endregion

}