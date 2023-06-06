package com.cupcake.jobsfinder.ui.jobs

import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentJobsBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment


class JobsFragment : BaseFragment<FragmentJobsBinding, JobsViewModel>(
    R.layout.fragment_jobs,
    JobsViewModel::class.java
) {

    override val LOG_TAG: String = this::class.java.name

}