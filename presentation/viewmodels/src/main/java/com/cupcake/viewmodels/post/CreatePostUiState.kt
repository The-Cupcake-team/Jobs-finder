package com.cupcake.viewmodels.post

import com.cupcake.viewmodels.base.BaseErrorUiState

data class CreatePostUiState(
    val post: PostUiState = PostUiState(),
    val isPostCreated: Boolean = false,
    val isLoading: Boolean = false,
    val errorFiled: String = "",
    val error: BaseErrorUiState? = null,
) {
    data class PostUiState(
        val id:String="",
        val content: String = "",
        val createAt:String= "",
    )

}