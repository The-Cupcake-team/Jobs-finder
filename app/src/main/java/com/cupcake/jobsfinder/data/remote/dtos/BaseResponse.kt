package com.cupcake.jobsfinder.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("value")val value : List<T>,
    @SerializedName("message")val message: String,
    @SerializedName("isSuccess")val isSuccess: Boolean
)