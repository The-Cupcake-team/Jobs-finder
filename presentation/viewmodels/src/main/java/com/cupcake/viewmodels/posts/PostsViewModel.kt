package com.cupcake.viewmodels.posts

import androidx.lifecycle.viewModelScope
import com.cupcake.models.Post
import com.cupcake.usecase.GetPostsUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : BaseViewModel<PostsUIState>(PostsUIState()), PostInteractionListener {

    private val _postEvent = MutableSharedFlow<PostsEvent>()
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
            it.copy(isLoading = false, isError = false, errors = "", postsResult = posts.map { post -> post.toPostItemUIState()})
        }
    }

    private fun onGetPostsFailure(error: BaseErrorUiState) {
        _state.update {
            it.copy(isLoading = false, isError = true, errors = handelReadableError(error) )
        }
    }

    fun onRetryClicked(){
        _state.update {it.copy(isError = false, isLoading = true) }
        getPosts()
    }

    private fun handelReadableError(error: BaseErrorUiState): String{
        return when(error){
            is BaseErrorUiState.Disconnected ->
                "Disconnected from the server. Please check your internet connection."
            is BaseErrorUiState.UnAuthorized ->
                "Unauthorized access. Please login again."
            is BaseErrorUiState.NoFoundError ->
                "The requested resource was not found."
            is BaseErrorUiState.ServerError ->
                "An unexpected server error occurred. Please try again later."
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