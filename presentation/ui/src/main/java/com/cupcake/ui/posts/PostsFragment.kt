package com.cupcake.ui.posts

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentPostsBinding
import com.cupcake.viewmodels.posts.PostsEvent
import com.cupcake.viewmodels.posts.PostsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostsFragment : BaseFragment<FragmentPostsBinding, PostsViewModel>(
    R.layout.fragment_posts,
    PostsViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPostsRecyclerView()
        handleEvent()
    }


    private fun setupPostsRecyclerView() {
        val adapter = PostsAdapter(listOf(), viewModel)
        binding.recyclerViewPosts.adapter = adapter
    }

    private fun handleEvent(){
        lifecycleScope.launch(Dispatchers.Main){
            viewModel.postEvent.collect{postEvent ->
                when(postEvent){
                    is PostsEvent.PostCommentClick ->
                        Toast.makeText(requireContext(), postEvent.id, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}