package com.cupcake.jobsfinder.ui.posts

data class PostsUIState(
    val postsResult: List<PostItemUIState> = emptyList(),
    val isLoading: Boolean = false,
    val errors: List<ErrorUIState> = emptyList(),
)

data class PostItemUIState(
    val id: String,
    val createdAt: Long,
    val description: String,
)


data class ErrorUIState(
    val message: String = "UNKNOWN ERROR"
)