package com.cupcake.ui.jobs.adapter

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.jobs.JobTitleUiState
import com.cupcake.ui.jobs.adapter.JobsAdapter.JobsListener

class PopularJobsAdapter(items: List<JobTitleUiState>, listener: JobsListener) :
    BaseAdapter<JobTitleUiState>(items, listener) {

    override var layoutId: Int = R.layout.item_popular_job

}