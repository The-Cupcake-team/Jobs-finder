package com.cupcake.ui.job_search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.cupcake.ui.BR
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.BottomSheetJobsSearchFilterBinding
import com.cupcake.ui.databinding.FragmentJobSearchBinding
import com.cupcake.ui.jobs.adapter.JobsAdapter
import com.cupcake.viewmodels.job_search.JobSearchViewModel
import com.cupcake.viewmodels.job_search.SearchJobEvent
import com.cupcake.viewmodels.jobs.JobsEvent
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class JobSearchFragment : BaseFragment<FragmentJobSearchBinding, JobSearchViewModel>(
    R.layout.fragment_job_search,
    JobSearchViewModel::class.java
) {

    override val LOG_TAG: String = this::class.java.name

    private val args: JobSearchFragmentArgs by navArgs()

    private lateinit var dialog: BottomSheetDialog
    private lateinit var bottomSheetBinding: BottomSheetJobsSearchFilterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()
        onBackButtonClicked()
        setUpBottomSheet()
        handelJobsSearchEvent()
        binding.editTextSearchInput.setText(args.jobTitle)

    }

    private fun onBackButtonClicked(){
        binding.buttonBack.setOnClickListener {view ->
            Navigation.findNavController(view).popBackStack()
        }
    }

    private fun setUpAdapter() {
        val jobsAdapter = JobSearchAdapter(emptyList(), viewModel)
        binding.recyclerJobSearch.adapter = jobsAdapter
    }

    private fun setUpBottomSheet(){
        bottomSheetBinding = BottomSheetJobsSearchFilterBinding.inflate(layoutInflater)
        dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(bottomSheetBinding.root)
        bottomSheetBinding.setVariable(BR.viewModel, viewModel)
    }

    private fun handelJobsSearchEvent() {
         lifecycleScope.launch(Dispatchers.Main) {
            viewModel.event.collect { jobsEvent ->
                when (jobsEvent) {
                    is SearchJobEvent.JobCardClick -> dialog.dismiss()
                    is SearchJobEvent.OnApplyButtonClicked -> dialog.dismiss()
                    is SearchJobEvent.OnFilterClicked -> dialog.show()
                    is SearchJobEvent.OnMoreOptionClickListener -> dialog.dismiss()
                }
            }
        }
    }

}