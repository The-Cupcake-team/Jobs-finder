package com.cupcake.jobsfinder.ui.post_details.state

import com.cupcake.jobsfinder.domain.model.Post
import com.cupcake.jobsfinder.ui.base.ErrorUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class PostDetailsUiState(
    val post: PostUiState = PostUiState(),
    val isLoading: Boolean = false,
    val errors: List<String> = emptyList()
)