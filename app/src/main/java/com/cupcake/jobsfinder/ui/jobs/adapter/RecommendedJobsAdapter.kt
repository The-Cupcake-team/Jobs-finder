package com.cupcake.jobsfinder.ui.jobs.adapter

import android.view.ViewGroup
import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.ui.base.BaseAdapter
import com.cupcake.jobsfinder.ui.jobs.JobUiState


class RecommendedJobsAdapter(items: List<JobUiState>, listener: JobsListener) :
    BaseAdapter<JobUiState>(items, listener) {
    override var layoutId: Int = R.layout.item_job_card

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        val itemView = holder.itemView
        (itemView.layoutParams as ViewGroup.LayoutParams).width = itemView
            .resources.getDimensionPixelSize(R.dimen.card_vertical_width)
    }
}

