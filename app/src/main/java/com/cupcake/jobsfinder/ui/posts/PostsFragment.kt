package com.cupcake.jobsfinder.ui.posts

import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentPostsBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment

class PostsFragment : BaseFragment<FragmentPostsBinding, PostsViewModel>(
    R.layout.fragment_posts,
    PostsViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name
}