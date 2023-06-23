package com.cupcake.remote.response


import kotlinx.serialization.Serializable

@Serializable
data class PostsDto(
    val author: Author?,
    val content: String?,
    val createdAt: String?,
    val id: String?,
    val image: Image?
)