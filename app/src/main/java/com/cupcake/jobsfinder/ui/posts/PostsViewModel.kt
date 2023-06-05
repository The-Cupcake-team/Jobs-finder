package com.cupcake.jobsfinder.ui.posts

import androidx.lifecycle.viewModelScope
import com.cupcake.jobsfinder.domain.model.Post
import com.cupcake.jobsfinder.domain.useCase.GetPostsUseCase
import com.cupcake.jobsfinder.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(PostsUIState())
    val uiState: StateFlow<PostsUIState> = _uiState

    private val _errors: MutableList<ErrorUIState> = mutableListOf()

    init {
        onGetPosts()
    }

    private fun onGetPosts() {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            getPostsUseCase().catch { throwable ->
                _uiState.update {
                    _errors.add(ErrorUIState(throwable.message.toString()))
                    it.copy(errors = _errors)
                }
            }.collect { posts ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        postsResult = posts.map { post -> post.toPostItemUIState() })
                }
            }


        }

    }


    suspend fun onInternetDisconnected() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun Post.toPostItemUIState(): PostItemUIState {
        return PostItemUIState(
            id = this.id,
            createdAt = this.createdAt,
            description = this.content,
        )
    }
}