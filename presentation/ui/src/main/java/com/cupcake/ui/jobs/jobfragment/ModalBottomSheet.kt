package com.cupcake.ui.jobs.jobfragment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.cupcake.ui.R
import com.cupcake.ui.databinding.EditJobPostBottomSheetsBinding
import com.cupcake.ui.job_search.JobSearchFragmentArgs
import com.cupcake.viewmodels.jobs.BottomSheetEvent
import com.cupcake.viewmodels.jobs.BottomSheetViewModel
import com.cupcake.viewmodels.jobs.JobUiState
import com.cupcake.viewmodels.jobs.toJobWithTitle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


class ModalBottomSheet : BottomSheetDialogFragment() {
    private val bottomSheetViewModel by lazy {
        ViewModelProvider(requireActivity())[BottomSheetViewModel::class.java]
    }
    private val args: ModalBottomSheetArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: EditJobPostBottomSheetsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.edit_job_post_bottom_sheets,
            container,
            false
        )
        bottomSheetViewModel.jobUiState = args.jobUiState
        bottomSheetViewModel.changeSavedState()

        binding.viewModel = bottomSheetViewModel
        handelBottomSheetEvent()
        return binding.root

    }

    private fun sharePost(id: String) {
        val intent = Intent(Intent.ACTION_SEND)
        val base = "https://cup-cake-media-dw2pb.ondigitalocean.app/"
        val shareBody =
            getString(
                R.string.sharePost,
                base.plus(id),

                )

        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(intent)
    }

    private fun handelBottomSheetEvent(){
        lifecycleScope.launch {
            bottomSheetViewModel.event.collect { bottomSheetsEvent ->
                when (bottomSheetsEvent) {
                    is BottomSheetEvent.OnSaveListener -> {
                        dismiss()
                    }

                    is BottomSheetEvent.OnShareClickListener -> {
                        sharePost(bottomSheetsEvent.id)
                        dismiss()
                    }
                }
            }
        }
    }


}