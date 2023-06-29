package com.cupcake.ui.profile.jobs

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentProfileJobsBinding
import com.cupcake.ui.profile.ProfileFragmentDirections
import com.cupcake.ui.profile.jobs.adapter.ProfileJobsAdapter
import com.cupcake.ui.profile.jobs.adapter.JobsProfileHorizontalAdapter
import com.cupcake.viewmodels.profile.jobs.ProfileJobsUIState
import com.cupcake.viewmodels.profile.jobs.ProfileJobsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProfileJobsFragment : BaseFragment<FragmentProfileJobsBinding, ProfileJobsViewModel>(
    R.layout.fragment_profile_jobs, ProfileJobsViewModel::class.java
) {
    override val LOG_TAG: String="ProfileJobsFragment"
    private lateinit var jobsAdapter: ProfileJobsAdapter
    private lateinit var profileJobEvent: Job

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpAdapter()
        handleSeeAllJobRecentClick()
        handleSeeAllJobSavedClick()
    }



private fun setUpAdapter() {
    val recentJobAdapter = JobsProfileHorizontalAdapter(emptyList(), viewModel)
    val savedJobAdapter = JobsProfileHorizontalAdapter(emptyList(), viewModel)
    binding.recyclerViewRecentJpbs.adapter = recentJobAdapter
    binding.recyclerViewSavedJobs.adapter = savedJobAdapter

    lifecycleScope.launch {
        viewModel.state.collect {
            recentJobAdapter.setData(it.RecentJobs)
            savedJobAdapter.setData(it.SavedJobs.reversed())
        }
    }
}


    private fun loadRecentJobs(state: ProfileJobsUIState): ProfileJobsItem.RecentJobs {
        return ProfileJobsItem.RecentJobs(state.RecentJobs)
    }

    private fun loadSavedJobs(state: ProfileJobsUIState): ProfileJobsItem.RecentJobs {
        return ProfileJobsItem.RecentJobs(state.SavedJobs)
    }

//    private fun handelJobsEvent() {
//        profileJobEvent = lifecycleScope.launch(Dispatchers.Main) {
//            viewModel.event.collect { profileJobEvent ->
//                when (profileJobEvent) {
//                    is ProfileJobsEvent.JobCardClick -> handleJobCardClick(profileJobEvent.id)
//                    is ProfileJobsEvent.JobChipClick -> handleJobChipClick(profileJobEvent.title)
//                    is ProfileJobsEvent.OnMoreOptionClickListener -> handleImageViewMoreClick(profileJobEvent.model)
//                }
//            }
//        }
//    }



    private fun navigateToDirection(directions: NavDirections) {
        findNavController().navigate(directions)
    }
    private fun handleSeeAllJobRecentClick() {
        binding.textViewTitleRecentSeeAllJob.setOnClickListener {
            navigateToDirection(
                ProfileFragmentDirections.actionProfileFragmentToJobProfileSeeAllFragment())
        }
    }

    private fun handleSeeAllJobSavedClick() {
        binding.textViewTitleSavedSeeAllJob.setOnClickListener {
            navigateToDirection(
                ProfileFragmentDirections.actionProfileFragmentToJobProfileSeeAllSavedFragment())
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = ProfileJobsFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
}