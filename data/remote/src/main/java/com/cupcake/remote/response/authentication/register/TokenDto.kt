package com.cupcake.remote.response.authentication.register

import kotlinx.serialization.Serializable

@Serializable
data class TokenDto(
    val token: String,
    val expireTime: Long
)
