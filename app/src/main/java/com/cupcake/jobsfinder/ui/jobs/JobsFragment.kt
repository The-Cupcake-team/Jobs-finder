package com.cupcake.jobsfinder.ui.jobs

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.viewModelScope
import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentJobsBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment
import com.cupcake.jobsfinder.ui.jobs.adapter.JobsAdapter
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.GlobalScope
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
        jobsAdapter = JobsAdapter(initialJobsItem(), viewModel)
        loadJobsToAdapter()
        binding?.jobsRecycler?.adapter = jobsAdapter
    }

    private fun loadJobsToAdapter() {
        var jobs = mutableListOf<JobsItem>()
        GlobalScope.launch {
            viewModel.jobsUIState.collect {
                val recommendedJobs = JobsItem.Recommended(it.recommendedJobs)
                val topSalaryJobs = JobsItem.TopSalary(it.topSalaryJobs)

                jobs = mutableListOf(recommendedJobs, topSalaryJobs, JobsItem.LocationJobs(JobUiState()))
                jobs.addAll(it.inLocationJobs.map { jobUiState -> JobsItem.LocationJobs(jobUiState) })
                Log.v("hassan", jobs.toString())
            }
        }
    }

    private fun initialJobsItem(): List<JobsItem> = listOf(
        JobsItem.Recommended(listOf(
            JobUiState("",
                "Android",
                companyName = "the chance",
                detailsChip = listOf("full-time", "on site"),
                location = "syria",
                salary = "1000"))),
        JobsItem.TopSalary(listOf(
            JobUiState("",
                "Android",
                companyName = "the chance",
                detailsChip = listOf("full-time", "on site"),
                location = "syria",
                salary = "1000"))),
        JobsItem.LocationJobs( JobUiState("",
            "Android",
            companyName = "the chance",
            detailsChip = listOf("full-time", "on site"),
            location = "syria",
            salary = "1000"))
    )
}