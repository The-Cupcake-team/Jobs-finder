package com.cupcake.ui.post_details.state

import com.cupcake.viewmodels.post_details.state.PostUiState


data class PostDetailsUiState(
    val post: PostUiState = PostUiState(),
    val isLoading: Boolean = false,
    val errors: List<String> = emptyList()
)