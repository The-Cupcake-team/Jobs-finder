package com.cupcake.viewmodels.comment

import android.util.Log
import com.cupcake.models.Comment
import com.cupcake.models.Post
import com.cupcake.usecase.CreateCommentUseCase
import com.cupcake.usecase.GetAllCommentsUseCase
import com.cupcake.usecase.GetPostByIdUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val getPostById: GetPostByIdUseCase,
    private val commentsUseCase: GetAllCommentsUseCase,
    private val createCommentUseCase: CreateCommentUseCase
) : BaseViewModel<CommentUiState>(CommentUiState()), CommentInteractionListener {


    //region getPost
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
    //endregion

    //region getComments
     fun getCommentsPost(id: String) {
        tryToExecute(
            { commentsUseCase(id) },
            ::onGetCommentsPostSuccess,
            ::onGetCommentPostFailure
        )
    }

    private fun onGetCommentsPostSuccess(comment: List<Comment>) {
        _state.update {
            it.copy(
                isLoading = false,
                error = null,
                comments = comment.map { comment -> comment.toCommentUiState() })
        }
    }

    private fun onGetCommentPostFailure(error: BaseErrorUiState) {
        _state.update {
            it.copy(isLoading = false, error = error)
        }
    }
    //endregion

    //region createComment
    fun createComment(content: String) {
        tryToExecute(
            { createCommentUseCase(state.value.post.id, content) },
            ::onCreateCommentSuccess,
            ::onCreateCommentFailure
        )
    }
    private fun onCreateCommentSuccess(comment: Boolean) {
        _state.update {
            it.copy(
                isLoading = false,
                error = null,
            )
        }
        getCommentsPost(state.value.post.id)
    }
    private fun onCreateCommentFailure(error: BaseErrorUiState) {
        _state.update {
            it.copy(isLoading = false, error = error)
        }
    }
    //endregion

    //region mapper
    private fun Post.toUiPost(): CommentUiState.PostUiState {
        return CommentUiState.PostUiState(
            id = id,
            content = content,
            createdAt = createdAt,
            creatorName = creatorName,
            profileImage = profileImage,
            jobTitle = jobTitle
            )
    }


    private fun Comment.toCommentUiState(): CommentUiState.CommentUiState{
        return CommentUiState.CommentUiState(
            id = id,
            postId = postId,
            totalLikes = totalLikes,
            content = content,
            createAt = createAt,
            commentAuthor = commentAuthor,
            jobTitle = jobTitle,
            profileImage = profileImage
        )
    }
    //endregion

    override fun onLikeClick(id: String) {
        _state.update { currentState ->
            val updateComments=currentState.comments.map { comment ->
                if (comment.id==id){
                    comment.copy(isLiked = !comment.isLiked, totalLikes = if (comment.isLiked) comment.totalLikes-1 else comment.totalLikes+1)
                }else{
                    comment
                }
            }
            currentState.copy(comments = updateComments)
        }
   }

}