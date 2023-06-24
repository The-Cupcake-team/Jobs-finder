package com.cupcake.ui.profile.jobs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentJobsBinding
import com.cupcake.ui.databinding.FragmentProfileJobsBinding
import com.cupcake.viewmodels.jobs.JobsViewModel

class ProfileJobsFragment() : BaseFragment<FragmentProfileJobsBinding, JobsViewModel>(
    R.layout.fragment_profile_jobs, JobsViewModel::class.java
) {
    override val LOG_TAG: String="ProfileJobsFragment"
}