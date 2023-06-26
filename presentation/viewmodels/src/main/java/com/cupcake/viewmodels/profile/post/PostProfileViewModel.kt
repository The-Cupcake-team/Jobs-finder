package com.cupcake.viewmodels.profile.post

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cupcake.models.Post
import com.cupcake.usecase.GetAllUserPostUseCase
import com.cupcake.usecase.GetPostSavedByIdUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.posts.SpecialPostsEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import com.cupcake.viewmodels.utill.Event
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PostProfileViewModel @Inject constructor(
    private val getPostsSavedUseCase: GetPostSavedByIdUseCase,
    private val getAllUserPost:GetAllUserPostUseCase
) : BaseViewModel<PostsSavedUIState>(PostsSavedUIState()), PostProfileInterAction {
    private val _postEvent = MutableSharedFlow<Event<PostProfileEvent>>()
    val postEvent = _postEvent.asSharedFlow()
    init {
        getSavedPost()
        getUserPost()
    }

   private fun getSavedPost() {
        tryToExecute(
            { getPostsSavedUseCase() },
            ::onGetSavedPostSuccess,
            ::onGetPostsFailure
        )
    }
   private fun getUserPost(){
        tryToExecute(
            {getAllUserPost()},
            ::onGetUserPostsSuccess,
            ::onGetPostsFailure
        )
    }

    private fun onGetUserPostsSuccess(posts: List<Post>) {
        _state.update {
            it.copy(
                isLoading = false,
                isError = false,
                error = null,
                isSuccess = true,
                isRefresh = false,
                recentPostsResult = posts.map { post -> post.toPostItemUIState() },
            )
        }
    }
    private fun onGetSavedPostSuccess(users: List<Post>){
        _state.update {
            it.copy(
                isLoading = false,
                isError = false,
                error = null,
                isSuccess = true,
                isRefresh = false,
                savedPostsResult = users.map { users -> users.toPostItemUIState() }.reversed(),
            )
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

    private fun Post.toPostItemUIState(): ProfilePostItemUIState {
        return ProfilePostItemUIState(
            id = id ?: "",
            createdAt = createdAt ?: "",
            content = content ?: "",
            image = postImage
        )
    }


    fun onRetryClicked(){
        _state.update {it.copy(error = null, isLoading = true, isSuccess = false) }
        getSavedPost()
        getUserPost()
    }

    fun onRefreshClicked(){
        _state.update {it.copy(error = null, isRefresh = true,isSuccess = false) }
        getSavedPost()
        getUserPost()
    }


    override fun onLikeRecentPostClick(id: String) {
        _state.update { currentState ->
            val updatedPosts = currentState.recentPostsResult.map { post ->
                if (post.id == id) {
                    val newLikes = if (post.isLiked) post.likes - 1 else post.likes + 1
                    post.copy(likes = newLikes, isLiked = !post.isLiked)
                } else {
                    post
                }
            }
            currentState.copy(recentPostsResult = updatedPosts)
        }
    }

    override fun onLikeSavedPostClick(id: String) {
            _state.update { currentState ->
                val updatedPosts = currentState.savedPostsResult.map { post ->
                    if (post.id == id) {
                        val newLikes = if (post.isLiked) post.likes - 1 else post.likes + 1
                        post.copy(likes = newLikes, isLiked = !post.isLiked)
                    } else {
                        post
                    }
                }
                currentState.copy(savedPostsResult = updatedPosts)
        }

    }

    override fun onCardClick(id: String) {
        viewModelScope.launch {
            _postEvent.emit(Event(PostProfileEvent.PostCardClick(id)))
        }
    }

}