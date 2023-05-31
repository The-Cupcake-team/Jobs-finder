package com.cupcake.jobsfinder.ui.posts

import androidx.lifecycle.viewModelScope
import com.cupcake.jobsfinder.domain.model.Post
import com.cupcake.jobsfinder.domain.usecase.GetPostsUseCase
import com.cupcake.jobsfinder.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
): BaseViewModel() {

    private val _uiState = MutableStateFlow(PostsUIState())
    val uiState: StateFlow<PostsUIState> = _uiState
    init {
        onGetPosts()
    }

    private fun onGetPosts(){
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getPostsUseCase()
                _uiState.update {
                    it.copy(isLoading = false, postsResult = result.map { post ->
                        post.toUIState()
                    })
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errors = listOf(ErrorUIState(message = e.message.toString())))
                }
            }
        }

        }

    suspend fun onInternetDisconnected(){
        _uiState.update { it.copy(isLoading = true) }
    }

    fun Post.toUIState(): PostItemUIState{
        return PostItemUIState(
            id = this.id,
            createdAt = this.createdAt,
            description = this.content,
            postInteraction = PostInteraction()
        )
    }
}