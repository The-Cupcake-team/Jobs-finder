package com.cupcake.remote.response.authentication.register

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    val token: String,
    val expireTime: Long
)
