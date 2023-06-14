package com.cupcake.ui.jobs.adapter

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.jobs.JobUiState
import com.cupcake.viewmodels.jobs.JobsListener


class JobsOnLocationAdapter(items: List<JobUiState>, listener: JobsListener) :
    BaseAdapter<JobUiState>(items, listener) {
    override var layoutId: Int = R.layout.item_job_card
}

