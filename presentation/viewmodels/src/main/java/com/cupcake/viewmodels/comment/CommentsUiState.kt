package com.cupcake.viewmodels.comment

import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.profile.ProfileUISate


data class CommentsUiState(
    val post:PostUiState = PostUiState(),
    val profileResult: ProfileUISate = ProfileUISate(),
    val comments:List<CommentUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error:BaseErrorUiState?=null,
    val isSuccess:Boolean=false,
){
    data class PostUiState(
        val id: String = "",
        val content: String = "",
        val createdAt: String = "",
        val creatorName: String = "",
        val profileImage: String = "",
        val jobTitle: String = "",
        val image: String = "",
        val comments: Int = 0,
        val isLiked: Boolean = false,
        val likes: Int = 0,
    )

    data class CommentUiState(
        val id: String = "",
        val postId: String = "",
        val content: String = "",
        val totalLikes: Int = 0,
        val createAt: String = "",
        val commentAuthor: String = "",
        val isLiked: Boolean = false,
        val jobTitle: String = "",
        val profileImage: String = "",
        val commentLoading: Boolean = false,
        val commentError: Boolean = false,
        val commentSuccess: Boolean = true,
    )
}