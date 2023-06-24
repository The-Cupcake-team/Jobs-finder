package com.cupcake.ui.job_search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
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
import kotlinx.coroutines.flow.debounce
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

        binding.editTextSearchInput.setText(args.jobTitle)
        setUpAdapter()
        setUpBottomSheet()
        onBackNavigationIconClicked()
        handelJobsSearchEvent()
        onSalaryChange()
        viewModel.initialSearchInput(args.jobTitle)
    }

    private fun setUpAdapter() {
        val jobsAdapter = JobSearchAdapter(emptyList(), viewModel)
        binding.recyclerJobSearch.adapter = jobsAdapter
    }

    private fun onBackNavigationIconClicked() {
        binding.toolBar.setNavigationOnClickListener { view ->
            view.findNavController().popBackStack()
        }
    }

    private fun setUpBottomSheet(){
        bottomSheetBinding = BottomSheetJobsSearchFilterBinding.inflate(layoutInflater)
        dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(bottomSheetBinding.root)
        dialog.setCancelable(false)
        bottomSheetBinding.setVariable(BR.viewModel, viewModel)
    }

    private fun handelJobsSearchEvent() {
         lifecycleScope.launch(Dispatchers.Main) {
            viewModel.event.collect { jobsEvent ->
                when (jobsEvent) {
                    is SearchJobEvent.JobCardClick -> {
                        //todo: hande navigation to job details
                    }
                    is SearchJobEvent.OnApplyButtonClicked -> dialog.dismiss()
                    is SearchJobEvent.OnFilterClicked -> dialog.show()
                    is SearchJobEvent.OnMoreOptionClickListener -> {
                        //todo: handel show save job dialog
                    }

                    SearchJobEvent.OnClearButtonClicked -> onClearClicked()
                }
            }
        }
    }

    private fun onClearClicked(){
        bottomSheetBinding.apply {
            chipGroupJopType.clearCheck()
            chipGroupWorkType.clearCheck()
            chipGroupExperience.clearCheck()
            editTextLocation.setText("")
            minSalary.text = ""
            maxSalary.text = ""
        }
    }

    private fun onSalaryChange(){
        lifecycleScope.launch {
            viewModel.salaryState.debounce(200).collect{
                bottomSheetBinding.apply {
                    minSalary.text = it.minSalary.toInt().toString()
                    maxSalary.text = it.maxSalary.toInt().toString()
                }
            }
        }
    }

}