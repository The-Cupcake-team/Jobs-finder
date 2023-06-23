package com.cupcake.remote

import com.cupcake.remote.response.authentication.register.UserDto
import com.cupcake.remote.response.base.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthApiService {


    @GET("/login")
    suspend fun login(
        @Header("Authorization") credentials: String
    ): Response<BaseResponse<UserDto>>

}