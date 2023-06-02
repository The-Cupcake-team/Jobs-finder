package com.cupcake.jobsfinder.ui.post

data class CreatePostUiState(
    val content: String = "",
    val isLoading: Boolean = false,
    val error: String = ""
)