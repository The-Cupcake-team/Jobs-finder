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
import com.cupcake.ui.databinding.FragmentSavedPostBinding
import com.cupcake.ui.profile.posts.adapter.PostSeeAllRecentAdapter
import com.cupcake.ui.profile.posts.adapter.PostSeeAllSavedAdapter
import com.cupcake.viewmodels.profile.post.PostProfileEvent
import com.cupcake.viewmodels.profile.post.PostProfileViewModel
import kotlinx.coroutines.launch


class SavedPostFragment : BaseFragment<FragmentSavedPostBinding,PostProfileViewModel>(
    R.layout.fragment_saved_post,
    PostProfileViewModel::class.java
) {
    override val LOG_TAG: String =this.javaClass.name
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        observePostEvents()
    }

    private fun setUpAdapter() {
        val savedPostAdapter= PostSeeAllSavedAdapter(emptyList(),viewModel)
        binding.recyclerViewSavedPost.adapter=savedPostAdapter
        lifecycleScope.launch{
            viewModel.state.collect{
                savedPostAdapter.setData(it.savedPostsResult)
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
        navigateToDirection(SavedPostFragmentDirections.actionSavedPostFragmentToCommentsFragment(postId))
    }
}