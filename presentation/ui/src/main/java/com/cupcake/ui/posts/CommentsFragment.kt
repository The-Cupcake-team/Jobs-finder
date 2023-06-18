package com.cupcake.ui.posts

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentCommentsBinding
import com.cupcake.viewmodels.posts.PostsViewModel


class CommentsFragment : BaseFragment<FragmentCommentsBinding, PostsViewModel>(
    R.layout.fragment_comments,
    PostsViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name


}