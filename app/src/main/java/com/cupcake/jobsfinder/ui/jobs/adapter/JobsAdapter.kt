package com.cupcake.jobsfinder.ui.jobs.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import com.cupcake.jobsfinder.BR
import com.cupcake.jobsfinder.R
import com.cupcake.jobsfinder.ui.base.BaseActionListener
import com.cupcake.jobsfinder.ui.base.BaseAdapter
import com.cupcake.jobsfinder.ui.jobs.JobUiState
import com.cupcake.jobsfinder.ui.jobs.JobsItem


class JobsAdapter(private val items: List<JobsItem>, private val listener: JobsListener) :
    BaseAdapter<JobsItem>(items, listener) {
    override var layoutId: Int = 0

    @SuppressLint("NotifyDataSetChanged")
    fun setJobsItems(newItems: List<JobsItem>) {
        setData(newItems.sortedBy { it.rank })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        layoutId = viewType
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (val current = items[position]) {
            is JobsItem.Recommended -> bindRecommendedJobs(current.items, holder as ItemViewHolder)
            is JobsItem.TopSalary -> bindTopSalaryJobs(current.items, holder as ItemViewHolder)
            is JobsItem.LocationJobs -> bindInLocationJobs(current.item, holder as ItemViewHolder)
        }
    }

    private fun bindRecommendedJobs(
        items: List<JobUiState>,
        holder: ItemViewHolder
    ) {
        holder.binding.setVariable(BR.adapter, RecommendedJobsAdapter(items, listener))
    }

    private fun bindTopSalaryJobs(
        items: List<JobUiState>,
        holder: ItemViewHolder
    ) {
        holder.binding.setVariable(BR.adapter, TopSalaryJobsAdapter(items, listener))
    }

    private fun bindInLocationJobs(
        item: JobUiState,
        holder: ItemViewHolder
    ) {
        holder.binding.setVariable(BR.item, item)
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is JobsItem.Recommended -> RECOMMENDED_JOBS
            is JobsItem.TopSalary -> TOP_SALARY_JOBS
            is JobsItem.LocationJobs -> IN_LOCATION_JOBS
        }
    }

    companion object {
        private const val RECOMMENDED_JOBS = R.layout.recommended_jobs_recycler
        private const val TOP_SALARY_JOBS = R.layout.top_salary_recycler
        private const val IN_LOCATION_JOBS = R.layout.item_job_card
    }
}


interface JobsListener : BaseActionListener {
    fun onItemClickListener()
    fun onRecommendedJobClickListener()
    fun onTopSalaryJobClickListener()
}