package com.cupcake.viewmodels.comment

import com.cupcake.viewmodels.base.BaseErrorUiState


data class CommentUiState(
    val post:PostUiState = PostUiState(),
    val posts:List<PostUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error:BaseErrorUiState?=null,
    val isSuccess:Boolean=false,
){
    data class PostUiState(
        val id:String="",
        val content: String = "",
        val createAt:Long= 0L,
        val creatorName: String = "",
        val comments: Int = 0,
        val isLiked: Boolean = false,
        val likes: Int = 0,
    )
}