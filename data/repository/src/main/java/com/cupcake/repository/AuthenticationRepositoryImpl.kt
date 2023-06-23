package com.cupcake.repository

import android.util.Log
import com.cupcake.local.datastore.AuthDataStore
import com.cupcake.models.ErrorType
import com.cupcake.models.Token
import com.cupcake.models.User
import com.cupcake.remote.AuthApiService
import com.cupcake.remote.JobApiService
import com.cupcake.remote.response.base.BaseResponse
import com.cupcake.repository.mapper.toToken
import com.cupcake.repository.mapper.toUser
import okhttp3.Credentials
import repo.AuthenticationRepository
import retrofit2.Response
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val api: JobApiService,
    private val authService: AuthApiService,
    private val authDataStore: AuthDataStore
) : AuthenticationRepository {

    override suspend fun register(
        fullName: String,
        userName: String,
        email: String,
        password: String
    ): User {
        return wrapResponseWithErrorHandler {
            api.register(
                fullName,
                userName,
                email,
                password
            )
        }.toUser()
    }

    override suspend fun login(username: String, password: String): Token {
        return wrapResponseWithErrorHandler {
            Credentials.basic(username, password).let { credentials ->
                authService.login(credentials)
            }
        }.token.toToken()
    }

    override suspend fun saveAuthData(token: Token) {
        authDataStore.saveAuthData(token.token, token.expireTime)
    }

    override suspend fun getAuthToken(): String? {
        return authDataStore.getAuthToken()
    }

    override suspend fun getAuthTokenExpireTime(): Long? {
        return authDataStore.getAuthTokenExpireTime()
    }

    override suspend fun clearAuthData() {
        authDataStore.clearAuthData()
    }

    private suspend fun <T> wrapResponseWithErrorHandler(
        function: suspend () -> Response<BaseResponse<T>>
    ): T {
        val response = function()
        if (response.isSuccessful) {
            Log.v("ameerxy","isSuccessful")
            val baseResponse = response.body()
            if (baseResponse != null && baseResponse.isSuccess) {
                Log.v("ameerxy","baseResponse.isSuccess")
                return baseResponse.value!!
            } else {
                Log.v("ameerxy","ErrorType.isSuccess")

                throw ErrorType.Server(baseResponse?.message!!)
            }
        } else {
            Log.v("ameerxy"," Error Network")
            val errorResponse = response.errorBody()?.toString()
            throw ErrorType.Server(errorResponse ?: "Error Network")
        }
    }
}