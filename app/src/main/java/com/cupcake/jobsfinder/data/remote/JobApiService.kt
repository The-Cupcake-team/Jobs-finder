package com.cupcake.jobsfinder.data.remote


import com.cupcake.jobsfinder.data.remote.response.PostDto
import com.cupcake.jobsfinder.data.remote.response.base.BaseResponse
import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface JobApiService {

    // region Job

    @POST("/job")
    @FormUrlEncoded
    suspend fun createJob(
        @Field("jobTitleId") jobTitleId: Int?,
        @Field("company") company: String?,
        @Field("workType") workType: String?,
        @Field("jobLocation") jobLocation: String?,
        @Field("jobType") jobType: String?,
        @Field("jobDescription") jobDescription: String?,
        @Field("jobSalary") jobSalary: Double?
    ): Response<BaseResponse<JobDto>>

    @GET("/job")
    suspend fun getAllJobs(): Response<BaseResponse<List<JobDto>>>

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

    @GET("/posts")
    suspend fun getAllPosts(): List<PostDto>

    //endregion


}