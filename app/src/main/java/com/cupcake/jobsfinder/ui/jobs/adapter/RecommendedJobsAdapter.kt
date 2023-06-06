package com.cupcake.jobsfinder.ui.jobs.adapter

import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.ui.base.BaseActionListener
import com.cupcake.jobsfinder.ui.base.BaseAdapter
import com.cupcake.jobsfinder.ui.jobs.JobUiState


class RecommendedJobsAdapter(items: List<JobUiState>, listener: RecommendedJobsListener) :
    BaseAdapter<JobUiState>(items, listener) {
    override var layoutId: Int = R.layout.item_job_card
}

interface RecommendedJobsListener : BaseActionListener {
    fun onItemClickListener()
}

