package com.cupcake.viewmodels.posts

import com.cupcake.models.Post
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : BaseViewModel<PostsUIState>(PostsUIState()) {

    init {
        getPosts()
    }


    private fun getPosts() {
        tryToExecute(
            { getPostsUseCase() },
            ::onGetPostsSuccess,
            ::onGetPostsFailure
        )
    }

    private fun onGetPostsSuccess(posts: List<Post>) {
        _state.update {
            it.copy(isLoading = false, isError = false, errors = emptyList(), postsResult = posts.map { post -> post.toPostItemUIState()})
        }
    }

    private fun onGetPostsFailure(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, isError = true, errors = listOf(throwable.message.toString())) }
    }


    suspend fun onInternetDisconnected() {
        _state.update { it.copy(isLoading = true) }
    }

    fun onRetryClicked(){
        _state.update { it.copy(errors = emptyList(), isError = false, isLoading = true) }
        getPosts()
    }

    private fun Post.toPostItemUIState(): PostItemUIState {
        return PostItemUIState(
            id = id,
            createdAt = createdAt,
            description = content
        )
    }
}