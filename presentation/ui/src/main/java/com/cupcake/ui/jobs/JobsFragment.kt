package com.cupcake.ui.jobs

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentJobsBinding
import com.cupcake.ui.jobs.adapter.JobsAdapter
import com.cupcake.viewmodels.jobs.JobsEvent
import com.cupcake.viewmodels.jobs.JobsUiState
import com.cupcake.viewmodels.jobs.JobsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class JobsFragment : BaseFragment<FragmentJobsBinding, JobsViewModel>(
    R.layout.fragment_jobs,
    JobsViewModel::class.java
) {

    override val LOG_TAG: String = this::class.java.name
    private lateinit var jobsAdapter: JobsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpAdapter()
        handelJobsEvent()
    }

    private fun setUpAdapter() {
        jobsAdapter = JobsAdapter(emptyList(), viewModel)
        binding?.jobsRecycler?.adapter = jobsAdapter
        loadJobsAdapter()
    }

    private fun loadJobsAdapter() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.state.collect { state ->
                val items = listOf(
                    loadPopularJobs(state),
                    loadJobsOnLocation(state),
                    loadRecommendedJobs(state),
                    loadTopSalaryJobs(state)
                )
                jobsAdapter.setJobsItems(items)
            }
        }
    }

    private fun loadRecommendedJobs(state: JobsUiState): JobsItem.Recommended {
        return JobsItem.Recommended(state.recommendedJobs)
    }

    private fun loadPopularJobs(state: JobsUiState): JobsItem.PopularJobs {
        return JobsItem.PopularJobs(state.popularJobs)
    }

    private fun loadTopSalaryJobs(state: JobsUiState): JobsItem.TopSalary {
        return JobsItem.TopSalary(state.topSalaryJobs)
    }

    private fun loadJobsOnLocation(state: JobsUiState): JobsItem.LocationJobs {
        return JobsItem.LocationJobs(state.onLocationJobs)
    }

    private fun handelJobsEvent() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.event.collect { jobsEvent ->
                when (jobsEvent) {
                    is JobsEvent.JobCardClick -> {
                        findNavController().navigate(JobsFragmentDirections.actionJobsFragmentToJobDetailsFragment())
                    }

                    is JobsEvent.JobChipClick -> showToast(jobsEvent.id)
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}