package com.cupcake.viewmodels.comment

import com.cupcake.models.Comment
import com.cupcake.viewmodels.base.BaseInteractionListener

interface CommentInteractionListener: BaseInteractionListener {
    fun onLikeClick(id: String)
    fun onTryAgainClick(comment: CommentsUiState.CommentUiState)
}