package com.cupcake.ui.jobs


import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentAboutCompanyCategoryBinding
import com.cupcake.viewmodels.job_details.JobViewModel


class FragmentAboutCompanyCategory :
    BaseFragment<FragmentAboutCompanyCategoryBinding, JobViewModel>(
        R.layout.fragment_about_company_category,
        JobViewModel::class.java
    ) {
    override val LOG_TAG: String = this.javaClass.simpleName
}