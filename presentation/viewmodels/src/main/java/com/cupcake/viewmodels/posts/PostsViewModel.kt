package com.cupcake.viewmodels.posts

import androidx.lifecycle.viewModelScope
import com.cupcake.models.Post
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : BaseViewModel<PostsUIState>(PostsUIState()) {

    private val handler = CoroutineExceptionHandler { _, exception ->
        onGetPostsFailure(exception)
    }

    init {
        onGetPosts()
    }

    private fun onGetPosts() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            val posts = getPostsUseCase()
            onGetPostsSuccess(posts)
        }

    }

    private fun onGetPostsSuccess(posts: List<Post>){
        _state.update {
            it.copy(
                isLoading = false,
                postsResult = posts.map { post -> post.toPostItemUIState() })
        }
    }

    private fun onGetPostsFailure(throwable: Throwable){
        _state.update { it.copy(isLoading = false, errors = listOf(throwable.message.toString())) }
    }


    suspend fun onInternetDisconnected() {
        _state.update { it.copy(isLoading = true) }
    }

    private fun Post.toPostItemUIState(): PostItemUIState {
        return PostItemUIState(
            id = id,
            createdAt = createdAt,
            description = content
        )
    }
}