package com.cupcake.jobsfinder.ui.jobs.adapter

import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.ui.base.BaseAdapter
import com.cupcake.jobsfinder.ui.jobs.JobUiState


class JobsOnLocationAdapter(items: List<JobUiState>, listener: JobsListener) :
    BaseAdapter<JobUiState>(items, listener) {
    override var layoutId: Int = R.layout.item_job_card
}

