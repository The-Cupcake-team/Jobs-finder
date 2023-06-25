package com.cupcake.repository

import com.cupcake.jobsfinder.local.datastore.AuthDataStore
import com.cupcake.models.ErrorType
import com.cupcake.models.Token
import com.cupcake.models.User
import com.cupcake.remote.AuthApiService
import com.cupcake.remote.JobApiService
import com.cupcake.remote.response.base.BaseResponse
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
        password: String,
        jobTitleId: Int
    ): User {
        return wrapResponseWithErrorHandler {
            api.register(
                fullName,
                userName,
                email,
                password,
                jobTitleId
            )
        }.toUser()
    }

    override suspend fun login(username: String, password: String): User {
        return wrapResponseWithErrorHandler {
            Credentials.basic(username, password).let { credentials ->
                authService.login(credentials)
            }
        }.toUser()
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
            val baseResponse = response.body()
            if (baseResponse != null && baseResponse.isSuccess) {
                return baseResponse.value!!
            } else {
                throw ErrorType.Server(baseResponse?.message!!)
            }
        } else {
            throw ErrorType.UnAuthorized("username or password wrong")
        }
    }
}