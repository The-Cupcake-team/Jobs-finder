package com.cupcake.ui.jobs.jobfragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.cupcake.ui.R
import com.cupcake.ui.databinding.EditJobPostBottomSheetsBinding
import com.cupcake.viewmodels.jobs.BottomSheetEvent
import com.cupcake.viewmodels.jobs.BottomSheetViewModel
import com.cupcake.viewmodels.jobs.JobUiState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch

private const val BOTTOM_SHEET = "ModalBottomSheet"

class ModalBottomSheet : BottomSheetDialogFragment() {
    private val bottomSheetViewModel: BottomSheetViewModel by viewModels()
    private lateinit var jobUiState: JobUiState

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
        arguments?.let {
            jobUiState = it.getParcelable(BOTTOM_SHEET)!!
        }

        binding.apply {
            item = jobUiState
            viewModel = bottomSheetViewModel
        }

        lifecycleScope.launch {
            bottomSheetViewModel.event.collect { bottomSheetsEvent ->
                when (bottomSheetsEvent) {
                    is BottomSheetEvent.OnSaveListener -> { savePost(jobUiState)}
                    is BottomSheetEvent.OnShareClickListener -> {
                        sharePost(bottomSheetsEvent.id)
                    }
                }
            }
        }

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

    private fun savePost(jobUiState: JobUiState) {
    }


    companion object {
        fun newInstance(model: JobUiState) = ModalBottomSheet().apply {
            arguments = Bundle().apply {
                putParcelable(BOTTOM_SHEET, model)
            }
        }
    }


}