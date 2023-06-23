package com.cupcake.ui.job_search

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.job_search.JobItemUiState
import com.cupcake.viewmodels.job_search.JobsSearchInteractionListener

class JobSearchAdapter(items: List<JobItemUiState>, listener: JobsSearchInteractionListener) :
    BaseAdapter<JobItemUiState>(items, listener) {
    override var layoutId: Int = R.layout.item_job_search_card
}
