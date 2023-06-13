package com.cupcake.remote

import com.cupcake.remote.response.JobTitleDto
import com.cupcake.remote.response.PostDto
import com.cupcake.remote.response.base.BaseResponse
import com.cupcake.remote.response.job.JobDto
import com.cupcake.remote.response.job.JobWithTitleDto
import retrofit2.Response
import retrofit2.http.Body
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


    @GET("/posts")
    suspend fun getPosts(): Response<BaseResponse<List<PostDto>>>

    @POST("/jobs")
    suspend fun createJob(@Body job: JobDto): JobDto

    // region Job

    @GET("/job")
    suspend fun getJobs(): Response<BaseResponse<List<JobWithTitleDto>>>

    @GET("/job/{id}")
    suspend fun getJobById(
        @Path("id") jobId: String
    ): Response<BaseResponse<JobDto>>

    //endregion


    // region Post
    @FormUrlEncoded
    @POST("/post")
    suspend fun createPost(
        @Field("content") content: String
    ): Response<BaseResponse<PostDto>>

    @GET("post/{postId}")
    suspend fun getPostById(
        @Path("postId") postId: String
    ): Response<BaseResponse<PostDto>>

    @GET("/posts")
    suspend fun getAllPosts(): List<PostDto>

    //endregion

    // region JobTitle
    @GET("/jobTitles")
    suspend fun getAllJobTitle(): Response<BaseResponse<List<JobTitleDto>>>
    // endregion

}