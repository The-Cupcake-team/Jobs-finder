package com.cupcake.repository

import android.util.Log
import com.cupcake.models.ErrorType
import com.cupcake.remote.response.base.BaseResponse
import retrofit2.Response

abstract class BaseRepository {
    suspend fun <T> wrapResponseWithErrorHandler(
        function: suspend () -> Response<BaseResponse<T>>
    ): T {
        val response = function()
        if (response.isSuccessful) {
            Log.v("ameerxy", "isSuccessful")
            val baseResponse = response.body()
            if (baseResponse != null && baseResponse.isSuccess) {
                Log.v("ameerxy", "baseResponse.isSuccess")
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