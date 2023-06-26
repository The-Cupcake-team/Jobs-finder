package com.cupcake.remote

import com.cupcake.remote.response.CommentDto
import com.cupcake.remote.response.JobTitleDto
import com.cupcake.remote.response.PostDto
import com.cupcake.remote.response.PostsDto
import com.cupcake.remote.response.authentication.register.UserDto
import com.cupcake.remote.response.base.BaseResponse
import com.cupcake.remote.response.job.JobDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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
        @Field("minSalary") minSalary: Double?,
        @Field("maxSalary") maxSalary: Double?,
        @Field("experience") experience: String?,
        @Field("education") education: String?,
    ): Response<BaseResponse<Nothing>>


    @GET("/public/posts")
    suspend fun getPosts(): Response<BaseResponse<List<PostsDto>>>

    @POST("/jobs")
    suspend fun createJob(@Body job: JobDto): JobDto

    // region Job

    @GET("/public/jobs")
    suspend fun getJobs(): Response<BaseResponse<List<JobDto>>>

    @GET("public/job/{id}")
    suspend fun getJobById(
        @Path("id") jobId: String,
    ): Response<BaseResponse<JobDto>>

    //endregion


    // region Post
    @Multipart
    @POST("/user/post")
    suspend fun createPost(
        @Part("content") content: RequestBody,
        @Part image: MultipartBody.Part?
    ): Response<BaseResponse<PostsDto>>



    @GET("/public/post/{postId}")
    suspend fun getPostById(
        @Path("postId") postId: String,
    ): Response<BaseResponse<PostsDto>>

    @GET("/posts")
    suspend fun getAllPosts(): List<PostDto>

    //endregion

    // region JobTitle
    @GET("/jobTitles")
    suspend fun getAllJobTitle(): Response<BaseResponse<List<JobTitleDto>>>
    // endregion

    // region Authentication
    @FormUrlEncoded
    @POST("/register")
    suspend fun register(
        @Field("fullName") fullName: String,
        @Field("username") userName: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("jobTitleId") jobTitleId: Int,
    ): Response<BaseResponse<UserDto>>

    // endregion

    //region Comment

    @GET("/post/{postId}/comments")
    suspend fun getComments(
        @Path("postId") postId: String,
    ): Response<BaseResponse<List<CommentDto>>>

    @FormUrlEncoded
    @POST("/post/{postId}/comment")
    suspend fun createComment(
        @Path("postId") postId: String,
        @Field("content") content: String,
    ): Response<BaseResponse<*>>

//endregion

}