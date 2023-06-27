package com.cupcake.viewmodels.post

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cupcake.models.Post
import com.cupcake.usecase.CreatePostUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.posts.PostsEvent
import com.cupcake.viewmodels.utill.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val createPostUseCase: CreatePostUseCase,
) : BaseViewModel<CreatePostUiState>(CreatePostUiState()), CreatePostInteractionListener {

    private val _postEvent = MutableSharedFlow<Event<CreatePostEvent>>()
    val postEvent = _postEvent.asSharedFlow()

    fun createPost(content: String) {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            callee = { createPostUseCase(content,state.value.postImage) },
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

    override fun onCameraClick() {
        viewModelScope.launch {
            _postEvent.emit(Event(CreatePostEvent.OnCameraClick))
        }
    }

    override fun onPhotoClick() {
        viewModelScope.launch {
            _postEvent.emit(Event(CreatePostEvent.OnPhotoClick))
        }
    }

    override fun onRemoveImageClick() {
        updateState { it.copy(postImage = null, isImageSelectionCanceled = false) }
    }

    override fun onPostClick() {
        viewModelScope.launch {
            _postEvent.emit(Event(CreatePostEvent.OnPostClick))
        }
    }

    fun handleImageResult(imageData: File?) {
        updateState { it.copy(postImage = imageData, isImageSelectionCanceled = true) }
    }
}