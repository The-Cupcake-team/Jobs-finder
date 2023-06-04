package com.cupcake.jobsfinder.ui.posts

import androidx.lifecycle.viewModelScope
import com.cupcake.jobsfinder.domain.model.Post
import com.cupcake.jobsfinder.domain.usecase.GetPostsUseCase
import com.cupcake.jobsfinder.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(PostsUIState())
    val uiState: StateFlow<PostsUIState> = _uiState

    private val _errors: MutableList<ErrorUIState> = mutableListOf()

    val handler = CoroutineExceptionHandler { _, exception ->
        onGetPostsFailure(exception)
    }

    init {
        onGetPosts()
    }

    private fun onGetPosts() {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch(handler) {
            getPostsUseCase().collect { posts ->
                onGetPostsSuccess(posts)
            }
        }

    }

    private fun onGetPostsSuccess(posts: List<Post>){
        _uiState.update {
            it.copy(
                isLoading = false,
                postsResult = posts.map { post -> post.toPostItemUIState() })
        }
    }

    private fun onGetPostsFailure(throwable: Throwable){
        _uiState.update {
            _errors.add(ErrorUIState(throwable.message.toString()))
            it.copy(errors = _errors)
        }
    }

    suspend fun onInternetDisconnected() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun Post.toPostItemUIState(): PostItemUIState {
        return PostItemUIState(
            id = this.id ?: "",
            createdAt = this.createdAt ?: 0,
            description = this.content ?: "",
        )
    }
}