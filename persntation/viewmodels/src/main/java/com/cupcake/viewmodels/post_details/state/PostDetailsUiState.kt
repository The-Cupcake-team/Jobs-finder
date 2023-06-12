package com.cupcake.viewmodels.post_details.state


data class PostDetailsUiState(
    val post: PostUiState = PostUiState(),
    val isLoading: Boolean = false,
    val errors: List<String> = emptyList()
){
    data class PostUiState(
        val id: String = "",
        val content: String = "",
        val createdAt: String = "",
    )
}