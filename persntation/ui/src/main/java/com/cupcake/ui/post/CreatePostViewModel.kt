package com.cupcake.ui.post

import androidx.lifecycle.viewModelScope
import com.cupcake.jobsfinder.domain.model.Post
import com.cupcake.jobsfinder.domain.usecase.CreatePostUseCase
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.post.CreatePostUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val createPostUseCase: CreatePostUseCase,
) : BaseViewModel<CreatePostUiState>(CreatePostUiState()) {

    private val _postUiState = MutableStateFlow(CreatePostUiState())
    val postUiState = _postUiState.asStateFlow()

    fun createPost(content: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _postUiState.update { it.copy(isLoading = true, error = "") }
                val post = createPostUseCase(content)
                onSuccessCreatePost(post)
            } catch (e: Exception) {
                onCreatePostError(e.message ?: "Unknown error")
            }
        }
    }


    private fun onSuccessCreatePost(post: Post) {
        _postUiState.update {
            it.copy(
                isLoading = false,
                error = "",
                post = post.toUiPost(),
                isPostCreated = true
            )
        }
    }

    private fun onCreatePostError(errorMessage: String) {
        _postUiState.update {
            it.copy(
                isLoading = false,
                error = errorMessage
            )
        }
    }

    private fun Post.toUiPost(): PostUiState {
        return PostUiState(id, content, createdAt)
    }

}

