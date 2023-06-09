package com.cupcake.viewmodels.posts

import androidx.lifecycle.viewModelScope
import com.cupcake.models.JobTitle
import com.cupcake.models.Post
import com.cupcake.models.UserProfile
import com.cupcake.usecase.GetPostsUseCase
import com.cupcake.usecase.ProfileUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.profile.ProfileUISate
import com.cupcake.viewmodels.utill.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublicPostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val profileUseCase: ProfileUseCase,
) : BaseViewModel<PostsUIState>(PostsUIState()), SpecialPostInteractionListener {

    private val _postEvent = MutableSharedFlow<Event<SpecialPostsEvent>>()
    val postEvent = _postEvent.asSharedFlow()

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
            it.copy(
                isLoading = false,
                isError = false,
                error = null,
                isSuccess = true,
                isRefresh = false,
                postsResult = posts.map { post -> post.toPostItemUIState() })
        }
    }

    private fun onGetPostsFailure(error: BaseErrorUiState) {
        _state.update {
            it.copy(
                isLoading = false,
                isError = true,
                error = error,
                isSuccess = false,
                isRefresh = false
            )
        }
    }


    fun onRetryClicked() {
        _state.update { it.copy(error = null, isLoading = true, isSuccess = false) }
        getPosts()
    }

    fun onRefreshClicked() {
        _state.update { it.copy(error = null, isRefresh = true, isSuccess = false) }
        getPosts()
    }

    private fun Post.toPostItemUIState(): PostItemUIState {
        return PostItemUIState(
            id = id,
            createdAt = createdAt,
            description = content,
            creatorName = creatorName,
            image = postImage,
            jobTitle = jobTitle,
            profileImage = profileImage
        )
    }



    override fun onCommentClick(id: String) {
        viewModelScope.launch {
            _postEvent.emit(Event(SpecialPostsEvent.PostCommentClick(id)))
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
            _postEvent.emit(Event(SpecialPostsEvent.PostShareClick(id)))
        }
    }

    override fun onOptionsClick(model: PostItemUIState) {
        viewModelScope.launch {
            _postEvent.emit(Event(SpecialPostsEvent.PostOptionsClick(model)))
        }
    }


}