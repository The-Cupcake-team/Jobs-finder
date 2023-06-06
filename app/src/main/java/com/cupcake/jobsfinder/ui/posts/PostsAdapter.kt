package com.cupcake.jobsfinder.ui.posts

import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.ui.base.BaseActionListener
import com.cupcake.jobsfinder.ui.base.BaseAdapter

class PostsAdapter(listener: PostListener):
    BaseAdapter<PostItemUIState>(listOf(), listener) {
    override var layoutId = R.layout.item_post
}

interface PostListener: BaseActionListener