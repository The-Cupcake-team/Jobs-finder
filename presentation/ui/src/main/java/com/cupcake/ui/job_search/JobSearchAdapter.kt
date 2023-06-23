package com.cupcake.ui.job_search

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.job_search.JobsSearchInteractionListener
import com.cupcake.viewmodels.jobs.JobUiState
import com.cupcake.viewmodels.jobs.JobsListener

class JobSearchAdapter(items: List<JobUiState>, listener: JobsSearchInteractionListener) :
    BaseAdapter<JobUiState>(items, listener) {
    override var layoutId: Int = R.layout.item_job_search_card
}
