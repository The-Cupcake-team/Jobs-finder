package com.cupcake.ui.profile.jobs

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentProfileJobsBinding
import com.cupcake.ui.jobs.jobfragment.JobsFragmentDirections
import com.cupcake.ui.profile.jobs.adapter.ProfileJobsAdapter
import com.cupcake.ui.profile.posts.ProfilePostFragment
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
    }

    override fun onResume() {
        super.onResume()
//        handelJobsEvent()
    }

//    override fun onPause() {
//        super.onPause()
//        profileJobEvent.cancel()
//    }

    private fun setUpAdapter() {
        jobsAdapter = ProfileJobsAdapter(emptyList(), viewModel)
        binding.recyclerViewRecentPost.adapter = jobsAdapter
        loadProfileJobsAdapter()
    }


    private fun loadProfileJobsAdapter() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.state.collect { state ->
                val items = listOf(
                    loadRecentJobs(state),
                    loadSavedJobs(state)
                )
                jobsAdapter.setProfileJobsItems(items)
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

    private fun handleJobCardClick(id: String) {
        navigateToDirection(JobsFragmentDirections.actionJobsFragmentToJobDetailsFragment(id))
    }

    private fun handleJobChipClick(title: String) {
        val action = JobsFragmentDirections.actionJobsFragmentToJobSearchFragment(title)
        navigateToDirection(action)
    }

    private fun handleImageViewMoreClick(model: com.cupcake.viewmodels.profile.jobs.ProfileJobUiState) {
//        navigateToDirection(JobsFragmentDirections.actionJobsFragmentToModalBottomSheet(model))
    }

    private fun navigateToDirection(directions: NavDirections) {
        findNavController().navigate(directions)
    }
    companion object {
        @JvmStatic
        fun newInstance() = ProfileJobsFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
}