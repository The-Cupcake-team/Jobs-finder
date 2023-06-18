package com.cupcake.ui.posts

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.base.BaseInteractionListener
import com.cupcake.viewmodels.posts.PostItemUIState

class FollowingPostAdapter (items: List<PostItemUIState>, listener: BaseInteractionListener):
    BaseAdapter<PostItemUIState>(items, listener) {
    override var layoutId = R.layout.item_post
}