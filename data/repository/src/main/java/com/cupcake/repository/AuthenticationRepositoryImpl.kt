package com.cupcake.repository

import android.util.Log
import com.cupcake.models.ErrorType
import com.cupcake.models.User
import com.cupcake.remote.JobApiService
import com.cupcake.remote.response.base.BaseResponse
import com.cupcake.repository.mapper.toUser
import com.example.local.AuthDataStore
import repo.AuthenticationRepository
import retrofit2.Response
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val api: JobApiService,
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

    override suspend fun saveAuthToken(token: String) {
        authDataStore.saveAuthToken(token)
    }

    override suspend fun getAuthToken(): String? {
        return authDataStore.getAuthToken()
    }

    override suspend fun clearAuthToken() {
        authDataStore.clearAuthToken()
    }

    private suspend fun <T> wrapResponseWithErrorHandler(
        function: suspend () -> Response<BaseResponse<T>>
    ): T {
        val response = function()
        if (response.isSuccessful) {
            val baseResponse = response.body()
            if (baseResponse != null && baseResponse.isSuccess) {
                Log.i("TAG", "base successful : ${baseResponse.value}")
                return baseResponse.value!!
            } else {
                Log.i("TAG", "base error : ${baseResponse?.message!!}")
                throw ErrorType.Server(baseResponse.message)
            }
        } else {
            val errorResponse = response.errorBody()?.toString()
            Log.i("TAG", "base error : ${errorResponse ?: "Error Network"}")
            throw ErrorType.Server(errorResponse ?: "Error Network")
        }

    }
}