package com.cupcake.remote

import com.cupcake.remote.response.authentication.register.UserDto
import com.cupcake.remote.response.base.BaseResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApiService {


    @GET("/login")
    suspend fun login(
        @Header("Authorization") credentials: String
    ): Response<BaseResponse<UserDto>>


    @FormUrlEncoded
    @POST("/register")
    suspend fun register(
        @Field("fullName") fullName: String,
        @Field("username") userName: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("jobTitleId") jobTitleId: Int,
    ): Response<BaseResponse<UserDto>>
}