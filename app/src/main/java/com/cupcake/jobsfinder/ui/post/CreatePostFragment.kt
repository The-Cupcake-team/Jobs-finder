package com.cupcake.jobsfinder.ui.post

import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentCreatePostBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment

class CreatePostFragment : BaseFragment<FragmentCreatePostBinding, CreatePostViewModel>(
    R.layout.fragment_create_post,
    CreatePostViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name
}