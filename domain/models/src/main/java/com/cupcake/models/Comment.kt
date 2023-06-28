package com.cupcake.models

data class Comment(
    val id: String,
    val postId: String,
    val totalLikes: Int,
    val content: String,
    val createAt: String,
    val commentAuthor: String,
    val jobTitle: String,
    val profileImage: String
)
