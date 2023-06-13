package com.cupcake.ui.posts

import android.os.Bundle
import android.view.View
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentPostsBinding
import com.cupcake.viewmodels.posts.PostsViewModel

class PostsFragment : BaseFragment<FragmentPostsBinding, PostsViewModel>(
    R.layout.fragment_posts,
    PostsViewModel::class.java
), PostListener {
    override val LOG_TAG: String = this.javaClass.name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPostsRecyclerView()
    }


    private fun setupPostsRecyclerView() {
        val adapter = PostsAdapter(listOf(), this)
        binding.recyclerViewPosts.adapter = adapter
    }

}