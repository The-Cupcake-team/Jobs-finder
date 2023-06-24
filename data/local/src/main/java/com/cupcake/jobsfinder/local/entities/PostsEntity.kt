package com.cupcake.jobsfinder.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "posts_table")
data class PostsEntity (
    @PrimaryKey
    val id: String,
    val createdAt: String,
    val content: String,
    val creatorName: String,
    val postImage: String,
    val jobTitle: String,
    val profileImage: String
)
