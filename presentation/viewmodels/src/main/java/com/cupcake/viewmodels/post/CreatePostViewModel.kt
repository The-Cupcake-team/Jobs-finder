package com.cupcake.viewmodels.post

import com.cupcake.models.Post
import com.cupcake.usecase.CreatePostUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val createPostUseCase: CreatePostUseCase,
) : BaseViewModel<CreatePostUiState>(CreatePostUiState()) {

    fun createPost(content: String) {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            callee = { createPostUseCase(content) },
            onSuccess = ::onSuccessCreatePost,
            onError = ::onCreatePostError
        )
    }

    private fun onSuccessCreatePost(post: Post) {
        updateState { it.copy(isLoading = false, post = post.toUiPost(), isPostCreated = true) }
    }

    private fun onCreatePostError(errorMessage: BaseErrorUiState) {
        updateState { it.copy(isLoading = false, error = errorMessage) }
    }

    private fun Post.toUiPost(): CreatePostUiState.PostUiState {
        return CreatePostUiState.PostUiState(id, content, createdAt)
    }

}

