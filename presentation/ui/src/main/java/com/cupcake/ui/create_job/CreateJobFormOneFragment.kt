package com.cupcake.ui.create_job

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.ItemCreateJobFormOneBinding
import com.cupcake.viewmodels.create_job.CreateJobViewModel

class CreateJobFormOneFragment : BaseFragment<ItemCreateJobFormOneBinding, CreateJobViewModel>(
    R.layout.item_create_job_form_one, CreateJobViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name

}