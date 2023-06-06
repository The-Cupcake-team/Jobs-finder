package com.cupcake.jobsfinder.ui.jobs

import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentJobDetailsBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment


class JobDetails : BaseFragment<FragmentJobDetailsBinding, JobViewModel>(
    R.layout.fragment_job_details,
    JobViewModel::class.java
) {
    override val LOG_TAG: String =this.javaClass.simpleName

}