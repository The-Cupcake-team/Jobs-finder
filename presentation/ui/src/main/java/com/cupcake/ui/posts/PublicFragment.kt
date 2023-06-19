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
import com.cupcake.viewmodels.posts.PostsEvent
import com.cupcake.viewmodels.posts.PublicPostsViewModel
import kotlinx.coroutines.launch

class PublicFragment : BaseFragment<FragmentPublicBinding, PublicPostsViewModel>(
    R.layout.fragment_public,
    PublicPostsViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.simpleName
    private val bottomSheetFragment = BottomSheetFragment()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupPostsRecyclerView()
        handleEvent()
    }

    private fun setupPostsRecyclerView() {
        val adapter = PostsAdapter(listOf(), viewModel)
        binding.recyclerViewPosts.adapter = adapter
        binding.recyclerViewPosts.itemAnimator = null
    }
    private fun handleEvent(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.postEvent.collect{postEvent ->
                    postEvent.getContentIfNotHandled()?.let { event ->
                        when(event){
                            is PostsEvent.PostCommentClick -> {
                                val action = PostsFragmentDirections.actionPostsFragmentToCommentsFragment(event.id)
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