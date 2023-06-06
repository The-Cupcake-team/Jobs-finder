package com.cupcake.jobsfinder.ui.create_job

import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.databinding.ItemCreateJobFormOneBinding
import com.cupcake.jobsfinder.ui.base.BaseFragment

class CreateJobFormOneFragment : BaseFragment<ItemCreateJobFormOneBinding, CreateJobViewModel>(
    R.layout.item_create_job_form_one, CreateJobViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name

}