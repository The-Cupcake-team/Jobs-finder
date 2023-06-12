package com.cupcake.ui.jobs

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentJobsBinding
import com.cupcake.ui.jobs.adapter.JobsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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
        loadJobsToAdapter()
        binding?.jobsRecycler?.adapter = jobsAdapter
    }

    private fun loadJobsToAdapter() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.jobsUIState.collect {
                val recommendedJobs = async { JobsItem.Recommended(it.recommendedJobs) }
                val topSalaryJobs = async { JobsItem.TopSalary(it.topSalaryJobs) }
                val onLocationJobs = async { JobsItem.LocationJobs(it.inLocationJobs) }
                val jobs = mutableListOf(
                    recommendedJobs.await(),
                    topSalaryJobs.await(),
                    onLocationJobs.await()
                )
                jobsAdapter.setJobsItems(jobs)
            }
        }
    }
}