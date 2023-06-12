package com.cupcake.ui.post

import android.os.Bundle
import android.view.View
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentCreatePostBinding
import com.cupcake.viewmodels.post.CreatePostViewModel

class CreatePostFragment : BaseFragment<FragmentCreatePostBinding, CreatePostViewModel>(
    R.layout.fragment_create_post,
    CreatePostViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}


