package com.cupcake.ui.posts

import android.os.Bundle
import android.view.View
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentFollowingBinding
import com.cupcake.viewmodels.posts.FollowingPostsViewModel

class FollowingFragment : BaseFragment<FragmentFollowingBinding, FollowingPostsViewModel>(
    R.layout.fragment_following,
    FollowingPostsViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupPostsRecyclerView()
    }

    private fun setupPostsRecyclerView(){
        val adapter = PostsAdapter(listOf(), viewModel)
        binding.recyclerViewFollowingPosts.adapter = adapter
    }

}