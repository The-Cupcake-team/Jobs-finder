package com.cupcake.ui.profile.posts

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentProfilePostBinding
import com.cupcake.ui.profile.posts.adapter.PostRecentAdapter
import com.cupcake.ui.profile.posts.adapter.PostSavedAdapter
import com.cupcake.viewmodels.profile.post.PostProfileEvent
import com.cupcake.viewmodels.profile.post.PostProfileViewModel
import kotlinx.coroutines.launch


class ProfilePostFragment : BaseFragment<FragmentProfilePostBinding,PostProfileViewModel>(
    R.layout.fragment_profile_post,
    PostProfileViewModel::class.java
) {
    override val LOG_TAG: String =this.javaClass.name
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        handleSeeAllPostRecentClick()
        handleSeeAllSavedPostClick()
        observePostEvents()
    }
    private fun setUpAdapter() {
        val recentPostAdapter=PostRecentAdapter(emptyList(),viewModel)
        val savedPostAdapter=PostSavedAdapter(emptyList(),viewModel)
        binding.recyclerViewRecentPost.adapter=recentPostAdapter
        binding.recyclerViewSavedPost.adapter=savedPostAdapter
        lifecycleScope.launch{
            viewModel.state.collect{
                recentPostAdapter.setData(it.recentPostsResult)
                savedPostAdapter.setData(it.savedPostsResult.reversed())
            }
        }
    }
    private fun observePostEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.postEvent.collect { postEvent ->
                    postEvent.getContentIfNotHandled()?.let { event ->
                        handlePostEvent(event)
                    }
                }
            }
        }
    }
    private fun handlePostEvent(event: PostProfileEvent) {
        when (event) {
            PostProfileEvent.OnProfileClick -> TODO()
            is PostProfileEvent.PostCardClick -> navigateToDetailsPostFragment(event.id)
        }
    }
    private fun navigateToDirection(directions: NavDirections) {
        findNavController().navigate(directions)
    }
    private fun navigateToDetailsPostFragment(postId: String) {
        navigateToDirection(ProfilePostFragmentDirections.actionProfilePostFragmentToCommentsFragment(postId))
    }
    private fun handleSeeAllPostRecentClick() {
        binding.textViewTitleRecentPostSeeAllPost.setOnClickListener {
navigateToDirection(ProfilePostFragmentDirections.actionProfilePostFragmentToRecentPostFragment())
    }}

    private fun handleSeeAllSavedPostClick() {
        binding.textViewTitleSavedPostSeeAllPost.setOnClickListener {
            navigateToDirection(ProfilePostFragmentDirections.actionProfilePostFragmentToSavedPostFragment())
        }
    }
}