package com.cupcake.viewmodels.posts

import com.cupcake.viewmodels.base.BaseErrorUiState


data class PostsUIState(
    val postsResult: List<PostItemUIState> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val error: BaseErrorUiState? = null,
)

data class PostItemUIState(
    val id: String,
    val createdAt: Long,
    val description: String,
)



