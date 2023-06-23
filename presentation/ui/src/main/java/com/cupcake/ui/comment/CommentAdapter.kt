package com.cupcake.ui.comment

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.comment.CommentInteractionListener
import com.cupcake.viewmodels.comment.CommentUiState
import com.cupcake.viewmodels.posts.PostItemUIState

class CommentAdapter(
    items: List<CommentUiState.PostUiState>,
    listener: CommentInteractionListener,
) :
    BaseAdapter<CommentUiState.PostUiState>(items, listener) {
    override var layoutId = R.layout.item_comment

    override fun areItemsEqual(
        oldItem: CommentUiState.PostUiState,
        newItem: CommentUiState.PostUiState,
    ): Boolean {
        return oldItem.id == newItem.id
    }
}