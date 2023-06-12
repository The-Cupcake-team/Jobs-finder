package com.cupcake.ui.jobs.adapter

import android.view.ViewGroup
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.job_details.JobDetailsUiState


class RecommendedJobsAdapter(items: List<JobDetailsUiState>, listener: JobsListener) :
    BaseAdapter<JobDetailsUiState>(items, listener) {
    override var layoutId: Int = R.layout.item_job_card

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        val itemView = holder.itemView
        (itemView.layoutParams as ViewGroup.LayoutParams).width = itemView
            .resources.getDimensionPixelSize(R.dimen.card_vertical_width)
    }
}

