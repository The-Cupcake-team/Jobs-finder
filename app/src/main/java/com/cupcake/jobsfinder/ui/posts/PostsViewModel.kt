package com.cupcake.jobsfinder.ui.posts

import com.cupcake.jobsfinder.domain.usecase.GetPostsUseCase
import com.cupcake.jobsfinder.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
): BaseViewModel() {

    private val _uiState = MutableStateFlow(PostsUIState())
    val uiState: StateFlow<PostsUIState> = _uiState
}