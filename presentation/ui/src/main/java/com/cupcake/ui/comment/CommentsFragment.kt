package com.cupcake.ui.comment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentCommentsBinding
import com.cupcake.viewmodels.comment.CommentViewModel


class CommentsFragment : BaseFragment<FragmentCommentsBinding, CommentViewModel>(
    R.layout.fragment_comments,
    CommentViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name
    private val args: CommentsFragmentArgs by lazy {
        CommentsFragmentArgs.fromBundle(requireArguments())
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupCommentRecyclerView()
        viewModel.getPost(args.postID)
        viewModel.getCommentsPost(args.postID)
        NavigationUI.setupWithNavController(binding.toolBar, findNavController())
    }

    private fun setupCommentRecyclerView(){
        val adapter = CommentAdapter(listOf(), viewModel)
        binding.RecyclerViewComments.adapter = adapter
        binding.RecyclerViewComments.itemAnimator = null
    }
}