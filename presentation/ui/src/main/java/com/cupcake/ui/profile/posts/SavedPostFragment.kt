package com.cupcake.ui.profile.posts

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentSavedPostBinding
import com.cupcake.ui.profile.posts.adapter.PostAdapterVet
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
    }
    private fun setUpAdapter() {
        val savedPostAdapter= PostAdapterVet(emptyList(),viewModel)
        binding.recyclerViewSavedPost.adapter=savedPostAdapter
        lifecycleScope.launch{
            viewModel.state.collect{
                savedPostAdapter.setData(it.savedPostsResult)
            }
        }
    }
}