package com.cupcake.jobsfinder.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    val id: String,
    val createdAt: Long,
    val content: String,
)
