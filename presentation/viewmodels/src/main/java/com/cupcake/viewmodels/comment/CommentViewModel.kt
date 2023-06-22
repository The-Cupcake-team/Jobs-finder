package com.cupcake.viewmodels.comment

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
    private val getPostById: GetPostByIdUseCase,
    private val postsUseCase: GetFollowingPostsUseCase
) : BaseViewModel<CommentUiState>(CommentUiState()), CommentInteractionListener {

    init {
        getCommentsPost()
    }

    fun getPost(id: String) {
        updateState { it.copy(isLoading = true, isSuccess = false, error = null) }
        tryToExecute(
            callee = { getPostById(id) },
            onSuccess = ::onSuccessGetPost,
            onError = ::onErrorGetPost
        )
    }

    private fun onSuccessGetPost(post: Post) {
        updateState { it.copy(isLoading = false, post = post.toUiPost(), isSuccess = true, error = null) }
    }

    private fun onErrorGetPost(error: BaseErrorUiState) {
        updateState { it.copy(isLoading = false, error = error, isSuccess = false) }
    }

    private fun getCommentsPost() {
        tryToExecute(
            { postsUseCase() },
            ::onGetCommentsPostSuccess,
            ::onGetCommentPostFailure
        )
    }

    private fun onGetCommentsPostSuccess(posts: List<Post>) {
        _state.update {
            it.copy(
                isLoading = false,
                error = null,
                posts = posts.map { post -> post.toUiPost() })
        }
    }

    private fun onGetCommentPostFailure(error: BaseErrorUiState) {
        _state.update {
            it.copy(isLoading = false, error = error)
        }
    }

    private fun Post.toUiPost(): CommentUiState.PostUiState {
        return CommentUiState.PostUiState(id, content, createdAt, creatorName)
    }

    override fun onLikeClick(id: String) {
        _state.update { currentState ->
            val updateComments=currentState.posts.map { post ->
                if (post.id==id){
                    post.copy(isLiked = !post.isLiked, likes = if (post.isLiked) post.likes-1 else post.likes+1)
                }else{
                    post
                }
            }
            currentState.copy(posts = updateComments)
        }
    }

}