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
        handleEvent()
    }

    private fun setupPostsRecyclerView(){
        val adapter = PostsAdapter(listOf(), viewModel)
        binding.recyclerViewFollowingPosts.adapter = adapter
        binding.recyclerViewFollowingPosts.itemAnimator = null

    }

    private fun handleEvent(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.postEvent.collect{postEvent ->
                    postEvent.getContentIfNotHandled()?.let { event ->
                        when(event){
                            is PostsEvent.PostCommentClick -> {
                                val action = PostsFragmentDirections.actionPostsFragmentToCommentsFragment("27271a1d-3023-44e1-bfb4-130afa641438")
                                findNavController().navigate(action)
                            }

                            is PostsEvent.PostShareClick -> {
                                val shareIntent= Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                                    type = "text/plain"
                                }
                                startActivity(Intent.createChooser(shareIntent, "Share via"))

                            }
                            is PostsEvent.PostOptionsClick -> {
                                bottomSheetFragment.show(requireActivity().supportFragmentManager, "BottomSheetDialog")
                            }
                        }
                    }

                }
            }
        }
    }

}