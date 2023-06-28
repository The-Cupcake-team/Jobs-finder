package com.cupcake.ui.profile.posts

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentRecentPostBinding
import com.cupcake.ui.profile.posts.adapter.PostSeeAllRecentAdapter
import com.cupcake.viewmodels.profile.post.PostProfileEvent
import com.cupcake.viewmodels.profile.post.PostProfileViewModel
import kotlinx.coroutines.launch


class RecentPostFragment : BaseFragment<FragmentRecentPostBinding,PostProfileViewModel>(
    R.layout.fragment_recent_post,
    PostProfileViewModel::class.java
) {
    override val LOG_TAG: String =this.javaClass.name
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        observePostEvents()
        onClickBackNavigationIcon()
    }
    private fun setUpAdapter() {
        val recentPostAdapter= PostSeeAllRecentAdapter(emptyList(),viewModel)
        binding.recyclerViewRecentPost.adapter=recentPostAdapter
        binding.recyclerViewRecentPost.itemAnimator = null
        lifecycleScope.launch{
            viewModel.state.collect{
                recentPostAdapter.setData(it.recentPostsResult)
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
        navigateToDirection(RecentPostFragmentDirections.actionRecentPostFragmentToCommentsFragment(postId))
    }
    private fun onClickBackNavigationIcon() {
        binding.toolBar.setNavigationOnClickListener { view ->
            view.findNavController().popBackStack()
        }
    }
}