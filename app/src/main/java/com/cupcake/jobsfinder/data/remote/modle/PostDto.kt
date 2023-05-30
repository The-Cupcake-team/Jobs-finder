package com.cupcake.jobsfinder.data.remote.modle

data class PostDto(
    val username: String,
    val jobTitle: String,
    val postingTime: String,
    val description: String,
    val imageUrl: String,
    val likesCount: Int,
    val commentCount: Int
)
