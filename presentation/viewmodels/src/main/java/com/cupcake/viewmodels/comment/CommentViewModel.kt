package com.cupcake.viewmodels.comment

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.cupcake.models.Post
import com.cupcake.usecase.GetFollowingPostsUseCase
import com.cupcake.usecase.GetPostByIdUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val getPostById :GetPostByIdUseCase,
    private val getFollowingPostsUseCase: GetFollowingPostsUseCase,
    savedStateHandle: SavedStateHandle
): BaseViewModel<CommentUiState>(CommentUiState()) , CommentInteractionListener{
//    private val postId = savedStateHandle.get<String>("postId")
    //private val args= CommentFragmentArgs.fromSavedStateHandle(savedStateHandle)
    init {
        getFollowingPosts()
        getPost("27271a1d-3023-44e1-bfb4-130afa641438")
    }

    fun getPost(id:String){
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            callee = {getPostById(id)},
            onSuccess = ::onSuccessGetPost,
            onError = ::onErrorGetPost
        )
    }
    private fun onSuccessGetPost(post: Post){
        updateState { it.copy(isLoading = false,post = post.toUiPost()) }
    }
    private fun onErrorGetPost(errorMessage: BaseErrorUiState){
        updateState { it.copy(isLoading = false,error = errorMessage) }
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
                error = null,
                postsResult = posts.map { post -> post.toUiPost()})
        }
    }

    private fun onGetFollowingPostsFailure(error: BaseErrorUiState) {
        _state.update {
            it.copy(isLoading = false, error = error)
        }
    }
    private fun Post.toUiPost(): CommentUiState.PostUiState {
        return CommentUiState.PostUiState(id, content, createdAt)
    }

    override fun onLikeClick(id: String) {
        TODO("Not yet implemented")
    }
}