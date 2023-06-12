package com.cupcake.remote.response.base

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val value: T?,
    val message: String,
    val isSuccess: Boolean
)

