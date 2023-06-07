package com.cupcake.jobsfinder.ui.jobs.adapter

import android.view.ViewGroup
import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.ui.base.BaseActionListener
import com.cupcake.jobsfinder.ui.base.BaseAdapter
import com.cupcake.jobsfinder.ui.jobs.JobUiState


class TopSalaryJobsAdapter(items: List<JobUiState>, listener: JobsListener) :
    BaseAdapter<JobUiState>(items, listener) {
    override var layoutId: Int = R.layout.item_job_card

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        ((holder as ItemViewHolder).binding as ViewGroup.LayoutParams).width =
           holder.itemView.resources.getDimensionPixelOffset(R.dimen.card_vertical_width)
        super.onBindViewHolder(holder, position)
    }
}

interface TopSalaryJobsListener : BaseActionListener {
    fun onItemClickListener()
}

