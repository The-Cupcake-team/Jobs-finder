package com.cupcake.jobsfinder.ui.jobs


import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentAboutJobCategoryBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment

class AboutJobCategory : BaseFragment<FragmentAboutJobCategoryBinding, JobViewModel>(
    R.layout.fragment_about_job_category,
    JobViewModel::class.java
) {
    override val LOG_TAG: String =this.javaClass.simpleName

}