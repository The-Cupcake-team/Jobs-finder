package com.cupcake.ui.jobs

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentJobsBinding
import com.cupcake.ui.jobs.adapter.JobsAdapter
import com.cupcake.viewmodels.jobs.JobsUiState
import com.cupcake.viewmodels.jobs.JobsViewModel
import com.cupcake.viewmodels.utill.observeEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class JobsFragment : BaseFragment<FragmentJobsBinding, JobsViewModel>(
    R.layout.fragment_jobs,
    JobsViewModel::class.java
) {

    override val LOG_TAG: String = this::class.java.name
    private lateinit var jobsAdapter: JobsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpAdapter()
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

    private fun loadTopSalaryJobs(state: JobsUiState): JobsItem.TopSalary {
        return JobsItem.TopSalary(state.topSalaryJobs)
    }

    private fun loadJobsOnLocation(state: JobsUiState): JobsItem.LocationJobs {
        return JobsItem.LocationJobs(state.onLocationJobs)
    }
}