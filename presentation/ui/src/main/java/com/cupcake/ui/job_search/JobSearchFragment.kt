package com.cupcake.ui.job_search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cupcake.ui.BR
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.BottomSheetJobsSearchFilterBinding
import com.cupcake.ui.databinding.FragmentJobSearchBinding
import com.cupcake.viewmodels.job_search.JobSearchViewModel
import com.cupcake.viewmodels.job_search.SearchJobEvent
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch


class JobSearchFragment : BaseFragment<FragmentJobSearchBinding, JobSearchViewModel>(
    R.layout.fragment_job_search,
    JobSearchViewModel::class.java
) {

    override val LOG_TAG: String = this::class.java.name

    private val args: JobSearchFragmentArgs by navArgs()

    private lateinit var filterDialog: BottomSheetDialog
    private lateinit var filterBottomSheetBinding: BottomSheetJobsSearchFilterBinding
    private lateinit var jobsEventJob: Job

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editTextSearchInput.setText(args.jobTitle)
        setUpAdapter()
        setUpFilterBottomSheet()
        onBackNavigationIconClicked()
        onSalaryChange()
        viewModel.initialSearchInput(args.jobTitle)
    }



    private fun setUpAdapter() {
        val jobsAdapter = JobSearchAdapter(emptyList(), viewModel)
        binding.recyclerJobSearch.adapter = jobsAdapter
    }

    private fun setUpFilterBottomSheet(){
        filterBottomSheetBinding = BottomSheetJobsSearchFilterBinding.inflate(layoutInflater)
        filterDialog = BottomSheetDialog(requireContext())
        filterDialog.setContentView(filterBottomSheetBinding.root)
        filterDialog.setCancelable(false)
        filterBottomSheetBinding.setVariable(BR.viewModel, viewModel)
    }

    private fun onBackNavigationIconClicked() {
        binding.toolBar.setNavigationOnClickListener { view ->
            view.findNavController().popBackStack()
        }
    }

    private fun onSalaryChange(){
        lifecycleScope.launch {
            viewModel.salaryState.debounce(200).collect{
                filterBottomSheetBinding.apply {
                    minSalary.text = it.minSalary.toInt().toString()
                    maxSalary.text = it.maxSalary.toInt().toString()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        handelJobsSearchEvent()
    }

    private fun handelJobsSearchEvent() {
        jobsEventJob = lifecycleScope.launch(Dispatchers.Main) {
            viewModel.event.collect { jobsEvent ->
                when (jobsEvent) {
                    is SearchJobEvent.JobCardClick -> handleJobCardClick(jobsEvent.id)
                    is SearchJobEvent.OnApplyButtonClicked -> dismissDialog(filterDialog)
                    is SearchJobEvent.OnFilterClicked -> showDialog(filterDialog)
                    is SearchJobEvent.OnClearButtonClicked -> onClearClicked()
                }
            }
        }
    }

    private fun handleJobCardClick(id: String) {
//        navigateToDirection(JobSearchFragmentDirections.actionJobSearchFragmentToJobDetailsFragment(id))
    }

    private fun navigateToDirection(directions: NavDirections) {
        findNavController().navigate(directions)
    }

    private fun onClearClicked(){
        filterBottomSheetBinding.apply {
            chipGroupJopType.clearCheck()
            chipGroupWorkType.clearCheck()
            chipGroupExperience.clearCheck()
            editTextLocation.setText("")
            minSalary.text = ""
            maxSalary.text = ""
        }
    }

    private fun showDialog(bottomSheetDialog: BottomSheetDialog){
        bottomSheetDialog.show()
    }

    private fun dismissDialog(bottomSheetDialog: BottomSheetDialog){
        bottomSheetDialog.dismiss()
    }

    override fun onStop() {
        super.onStop()
        jobsEventJob.cancel()
    }
}