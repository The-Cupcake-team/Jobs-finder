package com.cupcake.jobsfinder.ui.posts

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentPostsBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostsFragment : BaseFragment<FragmentPostsBinding, PostsViewModel>(
    R.layout.fragment_posts,
    PostsViewModel::class.java
), PostListener {
    override val LOG_TAG: String = this.javaClass.name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSeriesRecyclerView()

    }


    private fun setupSeriesRecyclerView() {
        val adapter = PostsAdapter(this)
        binding?.recyclerViewPosts?.adapter = adapter
    }

}