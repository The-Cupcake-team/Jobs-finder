package com.cupcake.viewmodels.post_details.state

import com.cupcake.viewmodels.base.BaseErrorUiState


data class PostDetailsUiState(
    val post: PostUiState = PostUiState(),
    val isLoading: Boolean = false,
    val error: BaseErrorUiState? = null,
){
    data class PostUiState(
        val id: String = "",
        val content: String = "",
        val createdAt: String = "",
    )
}