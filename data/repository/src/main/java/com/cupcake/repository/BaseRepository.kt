package com.cupcake.repository

import android.util.Log
import com.cupcake.models.ErrorType
import com.cupcake.remote.response.base.BaseResponse
import org.json.JSONObject
import retrofit2.Response
import java.net.UnknownHostException

abstract class BaseRepository {
    suspend fun <T> wrapResponseWithErrorHandler(
        function: suspend () -> Response<BaseResponse<T>>
    ): T {
        try {
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
                val errorResponse = JSONObject(response.errorBody()?.string()!!)
                throw ErrorType.Server(errorResponse.getString("message") ?: "Network Error")
            }
        } catch (e: UnknownHostException) {
            throw ErrorType.Network("Network Error")
        }
    }
}