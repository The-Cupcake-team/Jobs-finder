package com.cupcake.ui.comment

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.comment.CommentInteractionListener
import com.cupcake.viewmodels.comment.CommentUiState

class CommentAdapter(
    items: List<CommentUiState.CommentUiState>,
    listener: CommentInteractionListener,
) :
    BaseAdapter<CommentUiState.CommentUiState>(items, listener) {
    override var layoutId = R.layout.item_comment

    override fun areItemsEqual(
        oldItem: CommentUiState.CommentUiState,
        newItem: CommentUiState.CommentUiState,
    ): Boolean {
        return oldItem.id == newItem.id
    }
}