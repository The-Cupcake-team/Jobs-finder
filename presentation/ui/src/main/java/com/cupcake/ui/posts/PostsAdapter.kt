package com.cupcake.ui.posts

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseActionListener
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.posts.PostItemUIState

class PostsAdapter(items: List<PostItemUIState>, listener: PostListener):
    BaseAdapter<PostItemUIState>(items, listener) {
    override var layoutId = R.layout.item_post
}

interface PostListener: BaseActionListener