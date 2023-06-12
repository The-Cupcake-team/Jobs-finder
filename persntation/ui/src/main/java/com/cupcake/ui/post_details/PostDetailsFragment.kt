package com.cupcake.ui.post_details

import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentDetailsPostBinding
import com.cupcake.viewmodels.post_details.PostDetailsViewModel

class PostDetailsFragment : BaseFragment<FragmentDetailsPostBinding, PostDetailsViewModel>(
    R.layout.fragment_details_post,
    PostDetailsViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name
}