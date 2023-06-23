package com.cupcake.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    val id: String?,
    val createdAt: String?,
    val content: String?,
    val author: AuthorDto?,
    val creatorName: String?,
    val image: ImageDto?
)
