package com.cupcake.viewmodels.post



data class CreatePostUiState(
    val post: PostUiState = PostUiState(),
    val isPostCreated: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = "",
){
    data class PostUiState(
        val id:String="",
        val content: String = "",
        val createAt:Long= 0L
    )

}