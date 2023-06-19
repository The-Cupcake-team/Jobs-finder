package com.cupcake.viewmodels.posts

import androidx.lifecycle.viewModelScope
import com.cupcake.models.Post
import com.cupcake.usecase.GetFollowingPostsUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.utill.Event
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

    private val _postEvent = MutableSharedFlow<Event<PostsEvent>>()
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
    fun onRetryClicked(){
        _state.update {it.copy(error = null, isLoading = true) }
        getFollowingPosts()
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
            _postEvent.emit(Event(PostsEvent.PostCommentClick(id)))
        }
    }

    override fun onLikeClick(id: String) {
        _state.update { currentState ->
            val updatedPosts = currentState.postsResult.map { post ->
                if (post.id == id) {
                    val newLikes = if (post.isLiked) post.likes - 1 else post.likes + 1
                    post.copy(likes = newLikes, isLiked = !post.isLiked)
                } else {
                    post
                }
            }
            currentState.copy(postsResult = updatedPosts)
        }
    }

    override fun onClickShare(id: String) {
        viewModelScope.launch {
            _postEvent.emit(Event(PostsEvent.PostShareClick(id)))
        }
    }

    override fun onOptionsClick() {
        viewModelScope.launch {
            _postEvent.emit(Event(PostsEvent.PostOptionsClick))
        }
    }




}