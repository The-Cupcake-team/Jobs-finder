package com.cupcake.ui.comment

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.comment.CommentInteractionListener
import com.cupcake.viewmodels.comment.CommentUiState

class CommentAdapter (items: List<CommentUiState.PostUiState>, listener: CommentInteractionListener):
    BaseAdapter<CommentUiState.PostUiState>(items, listener) {
    override var layoutId = R.layout.item_comment
}