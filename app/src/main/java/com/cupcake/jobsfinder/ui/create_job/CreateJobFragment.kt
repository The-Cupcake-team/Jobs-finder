package com.cupcake.jobsfinder.ui.create_job

import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentCreateJobBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment

class CreateJobFragment : BaseFragment<FragmentCreateJobBinding, CreateJobViewModel>(
    R.layout.fragment_create_job, CreateJobViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name

}