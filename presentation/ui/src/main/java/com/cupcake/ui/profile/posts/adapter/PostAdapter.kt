package com.cupcake.ui.profile.posts.adapter

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.profile.post.PostProfileInterAction
import com.cupcake.viewmodels.profile.post.ProfilePostItemUIState

class PostAdapter(
    items: List<ProfilePostItemUIState>, listener: PostProfileInterAction,
) :
    BaseAdapter<ProfilePostItemUIState>(items, listener) {
    override var layoutId: Int = R.layout.item_profile_horizontal_post
}