package com.cupcake.viewmodels.posts

import com.cupcake.viewmodels.base.BaseErrorUiState


data class PostsUIState(
    val postsResult: List<PostItemUIState> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val error: BaseErrorUiState? = null,
    val isRefresh:Boolean=false,
    val isSuccess:Boolean=false
)

data class PostItemUIState(
    val id: String,
    val createdAt: String,
    val description: String,
    val creatorName: String,
    val likes: Int = 0,
    val isLiked: Boolean = false,
    val comments: Int = 0,
    val image: String = "https://images.unsplash.com/photo-1661956602153-23384936a1d3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1770&q=80",
)




