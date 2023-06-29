package com.cupcake.viewmodels.post

import androidx.lifecycle.viewModelScope
import com.cupcake.models.Post
import com.cupcake.models.UserProfile
import com.cupcake.usecase.CreatePostUseCase
import com.cupcake.usecase.ProfileUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.profile.ProfileUISate
import com.cupcake.viewmodels.utill.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val createPostUseCase: CreatePostUseCase,
    private val profileUseCase: ProfileUseCase
) : BaseViewModel<CreatePostUiState>(CreatePostUiState()), CreatePostInteractionListener {

    private val _postEvent = MutableSharedFlow<Event<CreatePostEvent>>()
    val postEvent = _postEvent.asSharedFlow()

    fun createPost(content: String) {
        updateState { it.copy(isLoading = true) }
        tryToExecuteDebounced(
            callee = { createPostUseCase(content,state.value.postImage) },
            onSuccess = ::onSuccessCreatePost,
            onError = ::onCreatePostError
        )
    }

    private fun onSuccessCreatePost(post: Post) {
        updateState { it.copy(isLoading = false, post = post.toUiPost(), isPostCreated = true) }
        viewModelScope.launch {
            _postEvent.emit(Event(CreatePostEvent.OnPostClick))
        }
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
    fun handleImageResult(imageData: File?) {
        updateState { it.copy(postImage = imageData, isImageSelectionCanceled = true) }
    }


    init {
        viewModelScope.launch {
            getProfileData()
        }
    }
    private suspend fun getProfileData() {
        withContext(Dispatchers.IO) {
            val profile = profileUseCase().toProfileUISate()
            updateState {
                it.copy(
                    profileResult = profile,
                )
            }
        }
    }
    private fun UserProfile.toProfileUISate(): ProfileUISate {
        return ProfileUISate(
            avatar = avatar,
            linkWebsite = linkWebsite,
            location = location,
            fullName = fullName,
            JobTitle = jobTitles,
        )

    }


    fun onRetryClicked(content: String){
        _state.update {it.copy(error = null, isLoading = false) }
        createPost(content)
    }

    fun onPostContentChange(){
        _state.update {it.copy(error = null, isLoading = false) }
    }
}