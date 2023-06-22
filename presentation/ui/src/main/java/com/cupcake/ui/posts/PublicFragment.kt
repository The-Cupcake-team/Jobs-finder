package com.cupcake.ui.posts

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentPublicBinding
import com.cupcake.viewmodels.posts.PostItemUIState
import com.cupcake.viewmodels.posts.PostsEvent
import com.cupcake.viewmodels.posts.PublicPostsViewModel
import kotlinx.coroutines.launch

class PublicFragment : BaseFragment<FragmentPublicBinding, PublicPostsViewModel>(
    R.layout.fragment_public,
    PublicPostsViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupPostsRecyclerView()
        observePostEvents()
    }

    private fun setupPostsRecyclerView() {
        val adapter = PostsAdapter(listOf(), viewModel)
        binding.recyclerViewPosts.adapter = adapter
        binding.recyclerViewPosts.itemAnimator = null
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

    private fun handlePostEvent(event: PostsEvent) {
        when (event) {
            is PostsEvent.PostCommentClick -> navigateToCommentsFragment(event.id)
            is PostsEvent.PostShareClick -> sharePost()
            is PostsEvent.PostOptionsClick -> showBottomSheetDialog(event.model)
        }
    }

    private fun navigateToCommentsFragment(postId: String) {
        val action = PostsFragmentDirections.actionPostsFragmentToCommentsFragment(postId)
        findNavController().navigate(action)
    }

    private fun sharePost() {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "This is my post to send.")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }

    private fun showBottomSheetDialog(model: PostItemUIState) {
        val action = PostsFragmentDirections.actionPostsFragmentToPostBottomSheetFragment(model)
        findNavController().navigate(action)
    }
}
