package com.cupcake.repository

import com.cupcake.local.datastore.AuthDataStore
import com.cupcake.models.ErrorType
import com.cupcake.models.User
import com.cupcake.remote.JobApiService
import com.cupcake.remote.response.base.BaseResponse
import com.cupcake.repository.mapper.toUser
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
                return baseResponse.value!!
            } else {
                throw ErrorType.Server(baseResponse?.message!!)
            }
        } else {
            val errorResponse = response.errorBody()?.toString()
            throw ErrorType.Server(errorResponse ?: "Error Network")
        }
    }
}