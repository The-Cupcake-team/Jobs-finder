package com.cupcake.models

data class Post(
    val id: String,
    val createdAt: String,
    val content: String,
    val creatorName: String,
    val postImage: String,
    val jobTitle: String,
    val profileImage: String
)