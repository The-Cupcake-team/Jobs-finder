package com.cupcake.jobsfinder.ui.posts

import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.ui.base.BaseActionListener
import com.cupcake.jobsfinder.ui.base.BaseAdapter

class PostsAdapter(items: List<PostItemUIState>, listener: PostListener):
    BaseAdapter<PostItemUIState>(items, listener) {
    override var layoutId = R.layout.item_post
}

interface PostListener: BaseActionListener