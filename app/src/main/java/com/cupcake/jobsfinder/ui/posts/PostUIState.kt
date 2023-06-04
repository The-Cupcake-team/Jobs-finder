package com.cupcake.jobsfinder.ui.posts

import com.cupcake.jobsfinder.domain.model.Post

data class PostsUIState(
    val postsResult: List<PostItemUIState> = emptyList(),
    val isLoading: Boolean = false,
    val errors: List<String> = emptyList(),
)

data class PostItemUIState(
    val id: String,
    val createdAt: Long,
    val description: String,
)


fun Post.toPostItemUIState(): PostItemUIState {
    return PostItemUIState(
        id = id ?: "",
        createdAt = createdAt ?: 0,
        description = content ?: "",
    )
}