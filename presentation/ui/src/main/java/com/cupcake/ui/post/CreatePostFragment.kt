package com.cupcake.ui.post

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentCreatePostBinding
import com.cupcake.viewmodels.post.CreatePostViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class CreatePostFragment : BaseFragment<FragmentCreatePostBinding, CreatePostViewModel>(
	R.layout.fragment_create_post,
	CreatePostViewModel::class.java
) {
	override val LOG_TAG: String = this.javaClass.name

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		lifecycleScope.launch {
			viewModel.state.collect { state ->
				if (state.isPostCreated) {
					Snackbar.make(requireView(), "Post Created Successfully", Snackbar.LENGTH_LONG)
						.show()
				}
			}
		}
	}
}


