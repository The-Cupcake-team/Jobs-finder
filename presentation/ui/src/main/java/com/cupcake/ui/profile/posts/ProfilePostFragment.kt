package com.cupcake.ui.profile.posts

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentProfilePostBinding
import com.cupcake.ui.profile.posts.adapter.PostAdapter
import com.cupcake.ui.profile.resume.ProfileResumeFragment
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
    }
    private fun setUpAdapter() {
        val recentPostAdapter=PostAdapter(emptyList(),viewModel)
        val savedPostAdapter=PostAdapter(emptyList(),viewModel)
        binding.recyclerViewRecentPost.adapter=recentPostAdapter
        binding.recyclerViewSavedPost.adapter=savedPostAdapter
        lifecycleScope.launch{
            viewModel.state.collect{
                recentPostAdapter.setData(it.recentPostsResult)
                savedPostAdapter.setData(it.savedPostsResult.reversed())
            }
        }
    }
    private fun navigateToDirection(directions: NavDirections) {
        findNavController().navigate(directions)
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

    companion object {
        @JvmStatic
        fun newInstance() = ProfilePostFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
}