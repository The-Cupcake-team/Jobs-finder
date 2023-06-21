package com.cupcake.ui.job_details


import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.ItemAboutCompanyCategoryBinding
import com.cupcake.viewmodels.job_details.JobViewModel


class AboutCompanyCategory :
    BaseFragment<ItemAboutCompanyCategoryBinding, JobViewModel>(
        R.layout.item_about_company_category,
        JobViewModel::class.java
    ) {
    override val LOG_TAG: String = this.javaClass.simpleName
}