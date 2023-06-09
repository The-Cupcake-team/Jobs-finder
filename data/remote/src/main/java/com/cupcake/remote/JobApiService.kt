package com.cupcake.remote

import com.cupcake.remote.response.CommentDto
import com.cupcake.remote.response.EducationDto
import com.cupcake.remote.response.JobTitleDto
import com.cupcake.remote.response.PostDto
import com.cupcake.remote.response.PostsDto
import com.cupcake.remote.response.SkillsDto
import com.cupcake.remote.response.authentication.register.UserDto
import com.cupcake.remote.response.base.BaseResponse
import com.cupcake.remote.response.job.JobDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface JobApiService {

    // region Job

    @FormUrlEncoded
    @POST("/user/job")
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
        @Field("skills") skills: String?,
    ): Response<BaseResponse<Unit>>

    @GET("/public/jobs")
    suspend fun getJobs(): Response<BaseResponse<List<JobDto>>>

    @GET("public/job/{id}")
    suspend fun getJobById(
        @Path("id") jobId: String,
    ): Response<BaseResponse<JobDto>>

    @GET("/user/Jobs")
    suspend fun getRecentJobs(): Response<BaseResponse<List<JobDto>>>


    //endregion


    // region Post
    @Multipart
    @POST("/user/post")
    suspend fun createPost(
        @Part("content") content: RequestBody,
        @Part image: MultipartBody.Part?
    ): Response<BaseResponse<PostsDto>>

    @GET("/public/posts")
    suspend fun getPosts(): Response<BaseResponse<List<PostsDto>>>


    @GET("/public/post/{postId}")
    suspend fun getPostById(
        @Path("postId") postId: String,
    ): Response<BaseResponse<PostsDto>>

    @GET("/user/posts")
    suspend fun getAllUserPost(): Response<BaseResponse<List<PostsDto>>>


    //endregion


    // region JobTitle
    @GET("/jobTitles")
    suspend fun getAllJobTitle(): Response<BaseResponse<List<JobTitleDto>>>
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
    ): Response<BaseResponse<Unit>>

    //endregion


    // region Profile

    @GET("/profile/educations")
    suspend fun getAllEducation(): Response<BaseResponse<List<EducationDto>>>

    @GET("/profile/skills")
    suspend fun getAllSkills(): Response<BaseResponse<List<SkillsDto>>>

    @DELETE("/profile/skill/{id}")
    suspend fun deleteSkill(@Path("id") skillId: String): Response<BaseResponse<Unit>>


    @FormUrlEncoded
    @POST("/profile/skill")
    suspend  fun createSkill(@Field("skill") skill: String) :Response<BaseResponse<Unit>>


    @FormUrlEncoded
    @POST("/profile/education")
    suspend fun addEducation(
        @Field("degree") degree: String,
        @Field("school") school: String,
        @Field("city") city: String,
        @Field("startDate") startDate: String,
        @Field("endDate") endDate: String
    ): Response<BaseResponse<Nothing>>

    @FormUrlEncoded
    @PUT("/profile/education/{educationId}")
    suspend fun updateEducation(
        @Path("educationId") educationId: String,
        @Field("degree") degree: String,
        @Field("school") school: String,
        @Field("city") city: String,
        @Field("startDate") startDate: String,
        @Field("endDate") endDate: String
    ):Response<BaseResponse<Unit>>

    // endregion

}