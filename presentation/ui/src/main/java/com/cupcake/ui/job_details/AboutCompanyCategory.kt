package com.cupcake.ui.job_details


import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.ItemAboutCompanyCategoryBinding
import com.cupcake.viewmodels.job_details.JobDetailsViewModel


class AboutCompanyCategory :
    BaseFragment<ItemAboutCompanyCategoryBinding, JobDetailsViewModel>(
        R.layout.item_about_company_category,
        JobDetailsViewModel::class.java
    ) {
    override val LOG_TAG: String = this.javaClass.simpleName
}