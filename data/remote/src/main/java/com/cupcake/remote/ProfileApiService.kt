package com.cupcake.remote

import com.cupcake.remote.response.EducationDto
import com.cupcake.remote.response.base.BaseResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProfileApiService {

    @GET("/profile/educations")
    fun getAllEducation() : Response<BaseResponse<List<EducationDto>>>



    @FormUrlEncoded
    @POST("/profile/education")
    suspend fun addEducation(
        @Field("degree") degree: String,
        @Field("school") school: String,
        @Field("city") city: String,
        @Field("startDate") startDate: String,
        @Field("endDate") endDate: String
    ):Response<BaseResponse<Nothing>>

    @FormUrlEncoded
    @PUT("/profile/education/{educationId}")
    suspend fun updateEducation(
        @Path("educationId") educationId: String,
        @Field("degree") degree: String,
        @Field("school") school: String,
        @Field("city") city: String,
        @Field("startDate") startDate: String,
        @Field("endDate") endDate: String
    ):Response<BaseResponse<Nothing>>

    @GET("/profile/educations/{educationId}")
    suspend fun getEducation(
        @Path("educationId") educationId: String,
    ):Response<BaseResponse<EducationDto>>
}