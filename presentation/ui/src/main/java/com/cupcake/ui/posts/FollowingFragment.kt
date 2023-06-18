package com.cupcake.ui.posts

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentFollowingBinding
import com.cupcake.viewmodels.posts.PostsViewModel

class FollowingFragment : BaseFragment<FragmentFollowingBinding, PostsViewModel>(
    R.layout.fragment_following,
    PostsViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.simpleName
}