package com.cupcake.ui.jobs

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentJobsBinding
import com.cupcake.ui.jobs.adapter.JobsAdapter
import com.cupcake.ui.jobs.adapter.JobsListener
import com.cupcake.viewmodels.jobs.JobsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class JobsFragment : BaseFragment<FragmentJobsBinding, JobsViewModel>(
    R.layout.fragment_jobs,
    JobsViewModel::class.java
) , JobsListener {

    override val LOG_TAG: String = this::class.java.name
    private lateinit var jobsAdapter: JobsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpAdapter()
    }

    private fun setUpAdapter() {
        jobsAdapter = JobsAdapter(emptyList(), this)
        binding?.jobsRecycler?.adapter = jobsAdapter
        loadRecommendedJobs()
    }

//    private fun loadJobsToAdapter() {
//        lifecycleScope.launch(Dispatchers.Main) {
//            viewModel.jobsUIState.collect {
//                val recommendedJobs = async { JobsItem.Recommended(it.recommendedJobs) }
//                val topSalaryJobs = async { JobsItem.TopSalary(it.topSalaryJobs) }
//                val onLocationJobs = async { JobsItem.LocationJobs(it.inLocationJobs) }
//                val jobs = mutableListOf(
//                    recommendedJobs.await(),
//                    topSalaryJobs.await(),
//                    onLocationJobs.await()
//                )
//                jobsAdapter.setJobsItems(jobs)
//            }
//        }
//    }

    private fun loadRecommendedJobs() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.state.collect {
                jobsAdapter.setJobsItems(listOf(JobsItem.Recommended(it.recommendedJobs)))
            }
        }
    }

    private fun loadTopSalaryJobs() {

    }

    private fun loadJobsOnLocation() {

    }

    override fun onItemClickListener() {
        TODO("Not yet implemented")
    }

    override fun onRecommendedJobClickListener() {
        TODO("Not yet implemented")
    }

    override fun onTopSalaryJobClickListener() {
        TODO("Not yet implemented")
    }
}