package com.cupcake.ui.post_details.state



data class PostDetailsUiState(
    val post: PostUiState = PostUiState(),
    val isLoading: Boolean = false,
    val errors: List<String> = emptyList()
)