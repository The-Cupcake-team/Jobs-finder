package com.cupcake.ui.profile.jobs

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentJobProfileSeeAllRecentBinding
import com.cupcake.ui.profile.jobs.adapter.JobsProfileVerticalAdapter
import com.cupcake.ui.profile.posts.adapter.PostSeeAllRecentAdapter
import com.cupcake.viewmodels.profile.jobs.ProfileJobsViewModel
import kotlinx.coroutines.launch


class JobProfileSeeAllFragment :
    BaseFragment<FragmentJobProfileSeeAllRecentBinding, ProfileJobsViewModel>(
        R.layout.fragment_job_profile_see_all_recent,
        ProfileJobsViewModel::class.java
    ) {
    override val LOG_TAG: String = this.javaClass.name
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        onClickBackNavigationIcon()
    }

    private fun setUpAdapter() {
        val recentAdapter= JobsProfileVerticalAdapter(emptyList(),viewModel)
        binding.recyclerViewRecentJob.adapter=recentAdapter
        lifecycleScope.launch{
            viewModel.state.collect{
                recentAdapter.setData(it.RecentJobs)
            }
        }
    }
    private fun onClickBackNavigationIcon() {
        binding.toolBar.setNavigationOnClickListener { view ->
            view.findNavController().popBackStack()
        }
    }
}