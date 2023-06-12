package com.cupcake.ui.jobs


import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentAboutCompanyCategoryBinding
import com.cupcake.viewmodels.job_details.JobViewModel


class FragmentAboutCompanyCategory :
    BaseFragment<FragmentAboutCompanyCategoryBinding, JobViewModel>(
        R.layout.fragment_about_company_category,
        JobViewModel::class.java
    ) {
    override val LOG_TAG: String = this.javaClass.simpleName
}