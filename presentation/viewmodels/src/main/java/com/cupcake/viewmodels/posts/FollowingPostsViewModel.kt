package com.cupcake.viewmodels.posts

import androidx.lifecycle.viewModelScope
import com.cupcake.models.Post
import com.cupcake.usecase.GetFollowingPostsUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowingPostsViewModel @Inject constructor(
    private val getFollowingPostsUseCase: GetFollowingPostsUseCase
) : BaseViewModel<PostsUIState>(PostsUIState()), PostInteractionListener {

    private val _postEvent = MutableSharedFlow<PostsEvent>()
    val postEvent = _postEvent.asSharedFlow()

    init {
        getFollowingPosts()
    }

    private fun getFollowingPosts(){
        tryToExecute(
            { getFollowingPostsUseCase() },
            ::onGetFollowingPostsSuccess,
            ::onGetFollowingPostsFailure
        )
    }

    private fun onGetFollowingPostsSuccess(posts: List<Post>) {
        _state.update {
            it.copy(
                isLoading = false,
                isError = false,
                error = null,
                postsResult = posts.map { post -> post.toPostItemUIState() })
        }
    }

    private fun onGetFollowingPostsFailure(error: BaseErrorUiState) {
        _state.update {
            it.copy(isLoading = false, isError = true, error = error)
        }
    }

    private fun Post.toPostItemUIState(): PostItemUIState {
        return PostItemUIState(
            id = id,
            createdAt = createdAt,
            description = content
        )
    }

    override fun onCommentClick(id: String) {
        viewModelScope.launch {
            _postEvent.emit(PostsEvent.PostCommentClick(id))
        }
    }

}