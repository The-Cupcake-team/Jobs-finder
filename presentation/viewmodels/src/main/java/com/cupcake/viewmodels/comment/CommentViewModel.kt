package com.cupcake.viewmodels.comment


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
) : BaseViewModel<CommentsUiState>(CommentsUiState()), CommentInteractionListener {


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
        val newComment = CommentsUiState.CommentUiState(
            postId = state.value.post.id,
            content = content,
            createAt = state.value.post.createdAt,
            commentAuthor = state.value.post.creatorName,
            jobTitle = state.value.post.jobTitle,
            profileImage = state.value.post.profileImage,
            commentLoading = true,
            commentSuccess = false
        )

        _state.update { currentState ->
            currentState.copy(comments = currentState.comments + newComment)
        }

        tryToExecute(
            callee = { createCommentUseCase(state.value.post.id, content) },
            onSuccess = ::onCreateCommentSuccess,
            onError = ::onCreateCommentFailure
        )
    }

    private fun onCreateCommentSuccess(success: Boolean) {
        _state.update { currentState ->
            val updatedComments = currentState.comments.map { existingComment ->
                if (existingComment.commentLoading) {
                    existingComment.copy(commentLoading = false, commentSuccess = success, commentError = false)
                } else {
                    existingComment
                }
            }
            currentState.copy(comments = updatedComments)
        }
        getCommentsPost(state.value.post.id)
    }

    private fun onCreateCommentFailure(error: BaseErrorUiState) {
        _state.update { currentState ->
            val updatedComments = currentState.comments.map { existingComment ->
                if (existingComment.commentLoading) {
                    existingComment.copy(commentLoading = false, commentError = true, commentSuccess = false)
                } else {
                    existingComment
                }
            }
            currentState.copy(comments = updatedComments)
        }
    }

    override fun onTryAgainClick(comment: CommentsUiState.CommentUiState) {
        val index = state.value.comments.indexOf(comment)
        if (index != -1) {
            val updatedComment = comment.copy(commentLoading = true, commentError = false)
            _state.update { currentState ->
                val updatedComments = currentState.comments.toMutableList()
                updatedComments[index] = updatedComment
                currentState.copy(comments = updatedComments)
            }
            tryToExecute(
                callee = { createCommentUseCase(comment.postId, comment.content) },
                onSuccess = ::onCreateCommentSuccess,
                onError = ::onCreateCommentFailure
            )
        }
    }
//endregion


    private fun Post.toUiPost(): CommentsUiState.PostUiState {
        return CommentsUiState.PostUiState(
            id = id,
            content = content,
            createdAt = createdAt,
            creatorName = creatorName,
            profileImage = profileImage,
            jobTitle = jobTitle,
            image = postImage
        )
    }


    private fun Comment.toCommentUiState(): CommentsUiState.CommentUiState{
        return CommentsUiState.CommentUiState(
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