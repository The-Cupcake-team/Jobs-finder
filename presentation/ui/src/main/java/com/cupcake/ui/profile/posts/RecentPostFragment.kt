package com.cupcake.ui.profile.posts

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentRecentPostBinding
import com.cupcake.ui.profile.posts.adapter.PostAdapterVet
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
    }
    private fun setUpAdapter() {
        val recentPostAdapter= PostAdapterVet(emptyList(),viewModel)
        binding.recyclerViewRecentPost.adapter=recentPostAdapter
        lifecycleScope.launch{
            viewModel.state.collect{
                recentPostAdapter.setData(it.recentPostsResult)
            }
        }
    }
}