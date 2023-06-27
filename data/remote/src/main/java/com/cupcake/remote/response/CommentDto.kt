package com.cupcake.remote.response


import kotlinx.serialization.Serializable

@Serializable
data class CommentDto(
    val author: Author?,
    val content: String?,
    val createAt: String?,
    val id: String?,
    val postId: String?,
    val totalLike: Int?
)