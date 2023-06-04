package com.cupcake.jobsfinder.ui.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentCreatePostBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment

class CreatePostFragment : BaseFragment<FragmentCreatePostBinding, CreatePostViewModel>(
    R.layout.fragment_create_post,
    CreatePostViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name
}