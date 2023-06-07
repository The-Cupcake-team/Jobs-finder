package com.cupcake.jobsfinder.ui.jobs


import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.FragmentAboutCompanyCategoryBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment


class FragmentAboutCompanyCategory :
    BaseFragment<FragmentAboutCompanyCategoryBinding, JobViewModel>(
        R.layout.fragment_about_company_category,
        JobViewModel::class.java
    ) {
    override val LOG_TAG: String = this.javaClass.simpleName
}