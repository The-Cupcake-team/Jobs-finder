package com.cupcake.jobsfinder.data.remote.modle

data class PostDto(
    val id: Long,
    val username: String,
    val jobTitle: String,
    val createdAt: Long,
    val description: String,
    val imageUrl: String,
    val likesCount: Int,
    val commentCount: Int
)
