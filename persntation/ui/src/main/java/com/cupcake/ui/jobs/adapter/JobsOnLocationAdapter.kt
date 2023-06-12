package com.cupcake.ui.jobs.adapter

import com.cupcake.jobsfinder.R
import com.cupcake.viewmodels.base.BaseAdapter
import com.cupcake.viewmodels.job_details.JobDetailsUiState


class JobsOnLocationAdapter(items: List<JobDetailsUiState>, listener: JobsListener) :
    BaseAdapter<JobDetailsUiState>(items, listener) {
    override var layoutId: Int = R.layout.item_job_card
}

