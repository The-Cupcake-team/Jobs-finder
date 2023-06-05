package com.cupcake.jobsfinder.ui.post_details

import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentDetailsPostBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment

class PostDetailsFragment : BaseFragment<FragmentDetailsPostBinding, PostDetailsViewModel>(
    R.layout.fragment_details_post,
    PostDetailsViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name
}