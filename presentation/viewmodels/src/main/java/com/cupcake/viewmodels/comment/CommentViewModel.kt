package com.cupcake.viewmodels.comment


import androidx.lifecycle.viewModelScope
import com.cupcake.models.Comment
import com.cupcake.models.Post
import com.cupcake.models.UserProfile
import com.cupcake.usecase.CreateCommentUseCase
import com.cupcake.usecase.GetAllCommentsUseCase
import com.cupcake.usecase.GetPostByIdUseCase
import com.cupcake.usecase.ProfileUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.profile.ProfileUISate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val getPostById: GetPostByIdUseCase,
    private val commentsUseCase: GetAllCommentsUseCase,
    private val createCommentUseCase: CreateCommentUseCase,
    private val profileUseCase: ProfileUseCase
) : BaseViewModel<CommentsUiState>(CommentsUiState()), CommentInteractionListener {

    init {
        viewModelScope.launch {
            getProfileData()
        }
    }

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
            content = content,
            createAt = setTime(),
            commentAuthor = state.value.profileResult.fullName,
            jobTitle = state.value.profileResult.JobTitle,
            profileImage = state.value.profileResult.avatar,
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
                    existingComment.copy(commentLoading = false, commentSuccess = true, commentError = false)
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

    private suspend fun getProfileData() {
        withContext(Dispatchers.IO) {
            val profile = profileUseCase().toProfileUISate()
            updateState {
                it.copy(
                    profileResult = profile,
                )
            }
        }
    }
    private fun UserProfile.toProfileUISate(): ProfileUISate {
        return ProfileUISate(
            avatar = avatar,
            linkWebsite = linkWebsite,
            location = location,
            fullName = fullName,
            JobTitle = jobTitles,
        )
    }

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

    private fun setTime(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault())
        val currentDate = Date()
        return dateFormat.format(currentDate)
    }


}