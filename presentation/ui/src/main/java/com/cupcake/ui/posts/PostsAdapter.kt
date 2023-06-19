package com.cupcake.ui.posts

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.posts.PostInteractionListener
import com.cupcake.viewmodels.posts.PostItemUIState


class PostsAdapter(items: List<PostItemUIState>, listener: PostInteractionListener):
    BaseAdapter<PostItemUIState>(items, listener) {
    override var layoutId = R.layout.item_post
    override fun areItemsEqual(oldItem: PostItemUIState, newItem: PostItemUIState): Boolean {
        return oldItem.id == newItem.id
    }
}

