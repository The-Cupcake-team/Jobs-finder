package com.cupcake.viewmodels.comment

import com.cupcake.viewmodels.base.BaseErrorUiState


data class CommentUiState(
    val post:PostUiState = PostUiState(),
    val postsResult: List<PostUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error:BaseErrorUiState?=null,
){
    data class PostUiState(
        val id:String="",
        val content: String = "",
        val createAt:Long= 0L,
    )
}