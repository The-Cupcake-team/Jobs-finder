package com.cupcake.jobsfinder.ui.post

import androidx.lifecycle.viewModelScope
import com.cupcake.jobsfinder.domain.usecase.CreatePostUseCase
import com.cupcake.jobsfinder.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreatePostViewModel @Inject constructor(
    private val createPostUseCase: CreatePostUseCase,
) : BaseViewModel() {

    private val _postUiState = MutableStateFlow(CreatePostUiState())
    val postUiState = _postUiState.asStateFlow()

    fun createPost() {
        viewModelScope.launch {
            _postUiState.update { it.copy(isLoading = true) }
            try {
                val post = createPostUseCase(
                    content = _postUiState.value.content
                )
                if (post) {
                    onSuccessCreatePost()
                }
            } catch (e: Exception) {
                onCreatePostError(e.message.toString())
            }
            _postUiState.update { it.copy(isLoading = false) }
        }
    }


    private fun onSuccessCreatePost() {
        // Add logic for successful post creation
    }

    private fun onCreatePostError(errorMessage: String) {
        _postUiState.update {
            it.copy(
                isLoading = false,
                error = errorMessage
            )
        }
    }
}
