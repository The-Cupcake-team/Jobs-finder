package com.cupcake.viewmodels.posts


data class PostsUIState(
    val postsResult: List<PostItemUIState> = emptyList(),
    val isLoading: Boolean = true,
    val errors: List<String> = emptyList(),
)

data class PostItemUIState(
    val id: String,
    val createdAt: Long,
    val description: String,
)



