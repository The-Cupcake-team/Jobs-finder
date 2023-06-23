package com.cupcake.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.cupcake.ui.R
import com.cupcake.ui.databinding.PostBottomSheetBinding
import com.cupcake.viewmodels.posts.bottom_sheet.PostBottomSheetEvent
import com.cupcake.viewmodels.posts.bottom_sheet.PostBottomSheetViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch

class PostBottomSheetFragment : BottomSheetDialogFragment() {
    private val postBottomSheetViewModel by lazy {
        ViewModelProvider(requireActivity())[PostBottomSheetViewModel::class.java]
    }
    private val args: PostBottomSheetFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       val binding: PostBottomSheetBinding = DataBindingUtil.inflate(
           inflater,
           R.layout.post_bottom_sheet,
           container,
           false
       )
        postBottomSheetViewModel.postUiState = args.postUiState
        postBottomSheetViewModel.changeSavedState()

        handlePostBottomSheetEvent()
        binding.viewModel = postBottomSheetViewModel
        return binding.root
    }

    private fun followClick(id: String){
        showToast("Follow clicked")
    }


    private fun handlePostBottomSheetEvent(){
        lifecycleScope.launch {
            postBottomSheetViewModel.event.collect{ postBottomSheetsEvent ->
                when(postBottomSheetsEvent){
                    is PostBottomSheetEvent.OnSaveListener -> {
                        val isUnSaved = postBottomSheetViewModel.state.value.isSaved
                        if(!isUnSaved){
                            showToast(getString(R.string.saved_successfully))
                        }else{
                            showToast(getString(R.string.saved_canceled))
                        }
                        dismiss()
                    }

                    is  PostBottomSheetEvent.OnFollowClickListener -> {
                        followClick(postBottomSheetsEvent.id)
                        dismiss()
                    }
                }

            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

}
