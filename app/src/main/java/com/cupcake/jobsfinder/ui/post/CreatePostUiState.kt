package com.cupcake.jobsfinder.ui.post


data class CreatePostUiState(
    val post: PostUiState = PostUiState(),
    val isPostCreated: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = "",
)