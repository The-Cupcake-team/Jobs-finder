package com.cupcake.viewmodels.profile.post

import com.cupcake.viewmodels.base.BaseErrorUiState

data class PostsSavedUIState(
    val recentPostsResult: List<ProfilePostItemUIState> = emptyList(),
    val savedPostsResult: List<ProfilePostItemUIState> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val error: BaseErrorUiState? = null,
    val isRefresh:Boolean=false,
    val isSuccess:Boolean=false
)
data class ProfilePostItemUIState(
    val id: String = "",
    val createdAt: String = "",
    val content: String = "",
    val likes: Int = 0,
    val isLiked: Boolean = false,
    val image: String = "",
)
