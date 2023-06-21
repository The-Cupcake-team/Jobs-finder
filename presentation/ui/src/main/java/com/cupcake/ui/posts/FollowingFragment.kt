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
import com.cupcake.ui.databinding.FragmentFollowingBinding
import com.cupcake.viewmodels.posts.FollowingPostsViewModel
import com.cupcake.viewmodels.posts.PostsEvent
import kotlinx.coroutines.launch

class FollowingFragment : BaseFragment<FragmentFollowingBinding, FollowingPostsViewModel>(
    R.layout.fragment_following,
    FollowingPostsViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.simpleName
    private val bottomSheetFragment = BottomSheetFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupPostsRecyclerView()
        observePostEvents()
    }

    private fun setupPostsRecyclerView() {
        val adapter = PostsAdapter(listOf(), viewModel)
        binding.recyclerViewFollowingPosts.adapter = adapter
        binding.recyclerViewFollowingPosts.itemAnimator = null
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
            is PostsEvent.PostCommentClick -> navigateToCommentsFragment("190d6e52-3ea5-4f1a-ad25-4487585b2ae5")
            is PostsEvent.PostShareClick -> sharePost()
            is PostsEvent.PostOptionsClick -> showBottomSheetDialog()
        }
    }

    private fun navigateToCommentsFragment(postId: String) {
        val action = PostsFragmentDirections.actionPostsFragmentToCommentsFragment(postId)
        findNavController().navigate(action)
    }

    private fun sharePost() {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
    private fun showBottomSheetDialog() {
        bottomSheetFragment.show(requireActivity().supportFragmentManager, "BottomSheetDialog")
    }
}
