package com.cupcake.ui.comment

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.comment.CommentInteractionListener
import com.cupcake.viewmodels.comment.CommentsUiState

class CommentAdapter(
    items: List<CommentsUiState.CommentUiState>,
    listener: CommentInteractionListener,
) :
    BaseAdapter<CommentsUiState.CommentUiState>(items, listener) {
    override var layoutId = R.layout.item_comment

    override fun areItemsEqual(
        oldItem: CommentsUiState.CommentUiState,
        newItem: CommentsUiState.CommentUiState,
    ): Boolean {
        return oldItem.id == newItem.id
    }
}